package com.example.gitview

import retrofit2.http.GET
import retrofit2.http.Header

// GitHubApi.kt
interface GitHubApi {
    @GET("user/repos")
    suspend fun getRepositories(
        @Header("Authorization") token: String
    ): List<Repository>
}
