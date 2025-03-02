package com.example.gitview


data class Repository(
    val name: String,
    val html_url: String,
    val description: String?,
    val language: String? // Добавляем поле language
)
