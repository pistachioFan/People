package com.example.depositapp.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()//tableName = "deposit")
data class DepositEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name="total_savings") val totalSavings: Double,
    @ColumnInfo(name="future_value") val futureValue: Double,
    @ColumnInfo(name="total_income") val totalIncome: Double,
    @ColumnInfo(name="income_rate") val incomeRate: Double
)
