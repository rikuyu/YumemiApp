package com.example.yumemiapp.model.repository

import com.example.yumemiapp.model.api.YumemiApi
import com.example.yumemiapp.model.data.ContributersItem
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api: YumemiApi
) {
    suspend fun fetchContributers(): Response<List<ContributersItem>> {
        return api.fetchContributers()
    }

    suspend fun getFollowings(userName: String): Response<List<ContributersItem>> {
        return api.getFollowings(userName)
    }

//    todo
}