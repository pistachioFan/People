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
import com.example.depositapp.model.ScreenFields
//import com.example.depositapp.model.MenuItem.AccompanimentItem
//import com.example.depositapp.model.MenuItem.EntreeItem
//import com.example.depositapp.model.MenuItem.SideDishItem
import com.example.depositapp.model.DepositUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DepositViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DepositUiState())
    val uiState: StateFlow<DepositUiState> = _uiState.asStateFlow()

    fun updateFirstScreen(firstScreen: ScreenFields) {
        val previousFirstScreen = _uiState.value.firstScreen
        updateItem(firstScreen, 1)
        Log.d("updateFirstScreen", _uiState.value.firstScreen?.secondField ?: "")
    }

    fun updateSecondScreen(secondScreen: ScreenFields) {
        val previousSecondScreen = _uiState.value.secondScreen
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
            Log.d("uiStateUpdate", firstScreen?.secondField ?: "")

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
}

//fun Double.formatPrice(): String {
//    return NumberFormat.getCurrencyInstance().format(this)
//}
