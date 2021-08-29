package com.example.yumemiapp.db

import androidx.room.*
import com.example.yumemiapp.model.data.Profile

@Dao
interface YumemiDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContributer(contributer: Profile)

    @Delete
    suspend fun deleteContributer(contributer: Profile)

    @Query("SELECT * FROM contributers")
    fun getContributers(): List<Profile>
}