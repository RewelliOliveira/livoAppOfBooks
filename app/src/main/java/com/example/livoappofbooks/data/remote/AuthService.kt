package com.example.livoappofbooks.data.remote

import com.example.livoappofbooks.data.remote.dto.LoginRequest
import com.example.livoappofbooks.data.remote.dto.LoginResponse
import com.example.livoappofbooks.data.remote.dto.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("/auth")
    suspend fun login(
        @Body body: LoginRequest
    ): LoginResponse

    @POST("/user")
    suspend fun register(
        @Body body: RegisterRequest
    ): Unit
}
