package com.example.livoappofbooks.data.remote.dto

data class RegisterResponse(
    val id: String,
    val name: String,
    val email: String,
    val profilePictureUrl: String?
)
