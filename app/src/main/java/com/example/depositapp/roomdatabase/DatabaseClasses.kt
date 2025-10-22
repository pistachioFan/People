package com.example.depositapp.roomdatabase

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.DatabaseView
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Entity(tableName = "deposits")
data class DepositEntry(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name="total_savings") val totalSavings: Double,
    @ColumnInfo(name="future_value") val futureValue: Double,
    @ColumnInfo(name="total_income") val totalIncome: Double,
    @ColumnInfo(name="income_rate") val incomeRate: Double
)

@Dao
interface DepositDao{
    @Query("SELECT * FROM deposits")
    fun getAll(): Flow<List<DepositEntry>>

    @Query("SELECT * FROM deposits WHERE id IN (:depIds)")
    fun loadAllByIds(depIds: IntArray): List<DepositEntry>

    @Insert
    fun insert(deposits: DepositEntry)

    @Delete
    fun delete(deposit: DepositEntry)
}

class DepositRepository(private val depositDao: DepositDao) {
    val allDeposits: Flow<List<DepositEntry>> = depositDao.getAll()

    suspend fun insertDeposit(deposit: DepositEntry) {
        depositDao.insert(deposit)
    }

    suspend fun deleteDeposit(deposit: DepositEntry) {
        depositDao.delete(deposit)
    }
}

@Database(entities = [DepositEntry::class], version = 1)
abstract class DepDatabase: RoomDatabase(){
    abstract fun depositDao(): DepositDao

    companion object {
        @Volatile
        private var Instance: DepDatabase? = null

        fun getDatabase(context: Context): DepDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    DepDatabase::class.java,
                    "deposit_database"
                )
                    .fallbackToDestructiveMigration() // Добавляем для стабильности
                    .build()
                    .also { Instance = it }
            }
        }
    }
}