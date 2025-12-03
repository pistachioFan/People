package com.example.depositapp.roomdatabase

import kotlinx.coroutines.flow.Flow

class DepositRepository(private val depositDao: DepositDao) {
    val allDeposits: Flow<List<DepositEntry>> = depositDao.getAll()

    fun getAll(): Flow<List<DepositEntry>> {
        return depositDao.getAll()
    }
    suspend fun insertDeposit(deposit: DepositEntry) {
        depositDao.insert(deposit)
    }

    suspend fun deleteDeposit(deposit: DepositEntry) {
        depositDao.delete(deposit)
    }
}