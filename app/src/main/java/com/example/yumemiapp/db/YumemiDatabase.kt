package com.example.yumemiapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.yumemiapp.model.data.Profile

@Database(entities = [Profile::class], version = 2, exportSchema = false)
abstract class YumemiDatabase : RoomDatabase() {
    abstract fun yumemiDao(): YumemiDao
}