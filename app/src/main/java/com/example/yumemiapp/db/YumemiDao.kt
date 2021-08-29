package com.example.yumemiapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.yumemiapp.model.data.Profile

@Dao
interface YumemiDao {

    @Insert
    suspend fun insertContributer(contributer: Profile)

    @Delete
    suspend fun deleteContributer(contributer: Profile)

    @Query("SELECT * FROM contributers")
    fun getContributers(): List<Profile>
}