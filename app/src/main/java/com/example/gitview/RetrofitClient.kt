package com.example.gitview

import retrofit2.Retrofit // Import Retrofit class
import retrofit2.converter.gson.GsonConverterFactory

// RetrofitClient.kt
object RetrofitClient {
    private const val BASE_URL = "https://api.github.com/"

    val instance: GitHubApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(GitHubApi::class.java)
    }
}
