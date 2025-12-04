package com.example.livoappofbooks.data.remote

import com.example.livoappofbooks.data.remote.dto.LoginRequest
import com.example.livoappofbooks.data.remote.dto.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("/user")
    suspend fun login(
        @Body body: LoginRequest
    ): LoginResponse
}
