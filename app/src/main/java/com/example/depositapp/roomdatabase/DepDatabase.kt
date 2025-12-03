package com.example.depositapp.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DepositEntry::class], version = 2)
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