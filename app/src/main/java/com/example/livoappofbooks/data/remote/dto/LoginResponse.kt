package com.example.livoappofbooks.data.remote.dto

data class LoginResponse(
    val id: Long? = null,
    val name: String? = null,
    val email: String? = null,
    val profilePictureUrl: String? = null,
    val token: String? = null,
)
