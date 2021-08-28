package com.example.yumemiapp.model.api

import com.example.yumemiapp.model.data.ContributersItem
import retrofit2.Response
import retrofit2.http.GET

interface YumemiApi {
    @GET("90792131/contributors")
    suspend fun fetchContributers(): Response<List<ContributersItem>>
}