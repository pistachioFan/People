/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.depositapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.depositapp.MyApp
import com.example.depositapp.auth.AuthApiInterface
import com.example.depositapp.auth.AuthRepository
import com.example.depositapp.auth.GroupDC
import com.example.depositapp.auth.LoginReqDC
import com.example.depositapp.auth.PersonInputDC
import com.example.depositapp.auth.RetrofitInstance
import com.example.depositapp.auth.TokenManager
import com.example.depositapp.auth.UserDC
import com.example.depositapp.auth.UserinputDC
import com.example.depositapp.model.ScreenFields
//import com.example.depositapp.model.MenuItem.AccompanimentItem
//import com.example.depositapp.model.MenuItem.EntreeItem
//import com.example.depositapp.model.MenuItem.SideDishItem
import com.example.depositapp.model.DepositUiState
import com.example.depositapp.roomdatabase.DepDatabase
import com.example.depositapp.roomdatabase.DepositEntry
import com.example.depositapp.roomdatabase.DepositRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DepositViewModel(val repository: DepositRepository) : ViewModel() {
    val authRepository = AuthRepository(RetrofitInstance.apiInterface)

    val deposits: StateFlow<List<DepositEntry>> = repository.allDeposits
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
    )
    private val _uiState = MutableStateFlow(DepositUiState())
    val uiState: StateFlow<DepositUiState> = _uiState.asStateFlow()

    private fun updateToken(){
        _uiState.value = _uiState.value.copy(token = TokenManager.jwttToken)
    }

    fun registerUser(user: UserinputDC){
        viewModelScope.launch {
            val response = authRepository.registerUser(user)
            updateToken()
        }
    }

    fun logUserIn(loginCreds: LoginReqDC){
        viewModelScope.launch {
            val response = authRepository.loginUser(loginCreds)
            updateToken()
        }
    }

    fun getUsers(){
        viewModelScope.launch {
            val users = authRepository.getAllUsers()
            _uiState.value = _uiState.value.copy(userList = users as List<UserDC>)
        }
    }

    fun getGroups(){
        viewModelScope.launch {
            val groups = authRepository.getGroups()
            _uiState.value = _uiState.value.copy(groupList = groups as List<GroupDC>)
        }
    }

    fun updateFirstScreen(firstScreen: ScreenFields) {
        //val previousFirstScreen = _uiState.value.firstScreen
        updateItem(firstScreen, 1)
        Log.d("updateFirstScreen", _uiState.value.firstScreen?.secondField ?: "")
    }

    fun updateSecondScreen(secondScreen: ScreenFields) {
        //val previousSecondScreen = _uiState.value.secondScreen
        updateItem(secondScreen, 2)
    }

    fun resetValues() {
        _uiState.value = DepositUiState()
    }

    private fun updateItem(newItem: ScreenFields, screenIndex: Int) {
        _uiState.update { currentState ->
            // Extracting values from previous and new items
            val firstScreen = when {
                screenIndex == 1 -> newItem
                else -> currentState.firstScreen
            }

            val secondScreen = when  {
                screenIndex == 2 -> newItem
                else -> currentState.secondScreen
            }

            // Calculate total savings, contributions, future value, and income
            val initialDeposit = firstScreen?.firstField?.toDoubleOrNull() ?: 0.0
            val interestRate = firstScreen?.secondField?.toDoubleOrNull() ?: 0.0
            val monthlyDeposit = secondScreen?.firstField?.toDoubleOrNull() ?: 0.0
            val totalMonths = secondScreen?.secondField?.toIntOrNull() ?: 0

            // Simple calculation for total savings and future value
            val currentSavings = initialDeposit + (monthlyDeposit * totalMonths)
            var futureValue = initialDeposit
            for(i in 1..totalMonths){
                futureValue = futureValue * (1 + (interestRate / 100)) + monthlyDeposit
            }
            //val interest = (currentSavings * (interestRate / 100)) * (totalMonths)
            Log.d("uiStateUpdate", currentState?.firstScreen?.firstField ?: "")

            currentState.copy(
                firstScreen = firstScreen,
                secondScreen = secondScreen,
                totalSavings = currentSavings,
                totalContribution = monthlyDeposit * totalMonths,
                incomeRate = (futureValue / currentSavings) * 100 - 100,
                futureValue = futureValue,
                totalIncome = futureValue - currentSavings
            )
        }
    }
    fun saveCurrentDeposit(){
        viewModelScope.launch {
            try {
                val newEntry = DepositEntry(
                    totalSavings = _uiState.value.totalSavings,
                    futureValue = _uiState.value.futureValue,
                    totalIncome = _uiState.value.totalIncome,
                    incomeRate = _uiState.value.incomeRate
                )
                repository.insertDeposit(newEntry)
            }
            catch(e: Exception){
                Log.d("DB_insertion", "nope $e")
            }
        }
    }
    fun removeDeposit(depositEntry: DepositEntry){
        viewModelScope.launch {
            repository.deleteDeposit(depositEntry)
        }
    }

}

class DepositViewModelFactory(private val database: DepDatabase): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        if(modelClass.isAssignableFrom(DepositViewModel::class.java)){
            return DepositViewModel(
                DepositRepository(
                    database.depositDao())) as T
        }
        throw IllegalArgumentException("Unrecognized ViewModel class")
    }
}