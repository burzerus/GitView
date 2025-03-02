package com.example.gitview


data class Repository(
    val name: String,
    val html_url: String,
    val description: String?,
    val language: String?,
    val stargazers_count: Int, // Добавляем количество звезд
    val forks_count: Int,      // Количество форков
    val watchers_count: Int    // Количество наблюдателей
)
