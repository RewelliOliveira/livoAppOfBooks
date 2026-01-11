package com.example.livoappofbooks.data.service

import com.example.livoappofbooks.data.remote.dto.LoginRequest
import com.example.livoappofbooks.data.remote.dto.LoginResponse
import com.example.livoappofbooks.data.remote.dto.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("/auth/login")
    suspend fun login(
        @Body body: LoginRequest
    ): LoginResponse

    @POST("/user/register")
    suspend fun register(
        @Body body: RegisterRequest
    ): Unit
}