package com.example.yumemiapp.model.repository

import com.example.yumemiapp.db.YumemiDao
import com.example.yumemiapp.model.api.YumemiApi
import com.example.yumemiapp.model.data.ContributersItem
import com.example.yumemiapp.model.data.Profile
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api: YumemiApi,
    private val dao: YumemiDao
) {
    suspend fun fetchContributers(): Response<List<ContributersItem>> {
        return api.fetchContributers()
    }

    suspend fun getFollowings(userName: String): Response<List<ContributersItem>> {
        return api.getFollowings(userName)
    }

    suspend fun insertContributer(contributer: Profile){
        dao.insertContributer(contributer)
    }

    suspend fun deleteContributer(contributer: Profile){
        dao.deleteContributer(contributer)
    }

    fun getContributers(): List<Profile>{
        return dao.getContributers()
    }
}