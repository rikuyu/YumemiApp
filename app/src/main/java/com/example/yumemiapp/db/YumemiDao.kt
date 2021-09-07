package com.example.yumemiapp.db

import androidx.room.*
import com.example.yumemiapp.model.data.Profile

@Dao
interface YumemiDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContributor(contributor: Profile)

    @Delete
    suspend fun deleteContributor(contributor: Profile)

    @Query("SELECT * FROM contributors")
    fun getContributors(): List<Profile>
}