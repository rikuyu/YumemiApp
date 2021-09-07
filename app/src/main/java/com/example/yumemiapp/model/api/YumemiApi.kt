package com.example.yumemiapp.model.api

import com.example.yumemiapp.model.data.ContributorsItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface YumemiApi {
    @GET("repositories/90792131/contributors")
    suspend fun fetchContributors(): Response<List<ContributorsItem>>

    @GET("users/{user_name}/followers")
    suspend fun getFollowings(
        @Path("user_name") userName: String
    ): Response<List<ContributorsItem>>
}