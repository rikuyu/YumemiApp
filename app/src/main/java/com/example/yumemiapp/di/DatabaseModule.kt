package com.example.yumemiapp.di

import android.content.Context
import androidx.room.Room
import com.example.yumemiapp.db.YumemiDao
import com.example.yumemiapp.db.YumemiDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideTwitchDao(yumemiDatabase: YumemiDatabase): YumemiDao {
        return yumemiDatabase.yumemiDao()
    }

    @Provides
    @Singleton
    fun provideTwitchDatabase(@ApplicationContext appContext: Context): YumemiDatabase {
        return Room.databaseBuilder(
            appContext,
            YumemiDatabase::class.java,
            "yumemi_database"
        ).allowMainThreadQueries().build()
    }
}