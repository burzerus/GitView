package com.example.gitview

data class Repository(
    val name: String,
    val html_url: String,
    val description: String?,
    val language: String?,
    val stargazers_count: Int,
    val forks_count: Int,
    val watchers_count: Int,
    val readmeContent: String?  // Добавлено поле для содержимого README.md
)


data class Owner(
    val login: String  // Имя владельца репозитория
)
