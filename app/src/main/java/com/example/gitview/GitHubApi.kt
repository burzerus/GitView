package com.example.gitview

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

// GitHubApi.kt
interface GitHubApi {
    @GET("user/repos")
    suspend fun getRepositories(
        @Header("Authorization") token: String
    ): List<Repository>

    // Получаем содержимое README.md для определенного репозитория
    @GET("repos/{owner}/{repo}/contents/README.md")
    suspend fun getReadme(
        @Header("Authorization") token: String,
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): ReadmeResponse
}

data class ReadmeResponse(
    val content: String,
    val encoding: String
)
