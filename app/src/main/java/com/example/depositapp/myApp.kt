package com.example.depositapp

import android.app.Application
import androidx.room.Room
import com.example.depositapp.roomdatabase.DepDatabase

class MyApp: Application(){

    lateinit var db: DepDatabase
    override fun onCreate() {
        super.onCreate()
        db = DepDatabase.getDatabase(this)
    }
}