package com.example.livoappofbooks.data.remote.dto

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val profilePictureUrl: String? = null
)
