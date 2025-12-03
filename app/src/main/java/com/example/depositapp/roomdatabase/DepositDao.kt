package com.example.depositapp.roomdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DepositDao{
    @Query("SELECT * FROM DepositEntry")
    fun getAll(): Flow<List<DepositEntry>>

    @Query("SELECT * FROM DepositEntry WHERE id IN (:depIds)")
    suspend fun loadAllByIds(depIds: IntArray): List<DepositEntry>

    @Insert
    suspend fun insert(deposits: DepositEntry)

    @Delete
    suspend fun delete(deposit: DepositEntry)
}