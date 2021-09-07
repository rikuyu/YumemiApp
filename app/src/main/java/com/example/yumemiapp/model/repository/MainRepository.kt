package com.example.yumemiapp.model.repository

import com.example.yumemiapp.db.YumemiDao
import com.example.yumemiapp.model.api.YumemiApi
import com.example.yumemiapp.model.data.ContributorsItem
import com.example.yumemiapp.model.data.Profile
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api: YumemiApi,
    private val dao: YumemiDao
) {
    suspend fun fetchContributors(): Response<List<ContributorsItem>> {
        return api.fetchContributors()
    }

    suspend fun getFollowings(userName: String): Response<List<ContributorsItem>> {
        return api.getFollowings(userName)
    }

    suspend fun insertContributor(contributer: Profile) {
        dao.insertContributor(contributer)
    }

    suspend fun deleteContributor(contributer: Profile) {
        dao.deleteContributor(contributer)
    }

    fun getContributors(): List<Profile> {
        return dao.getContributors()
    }
}