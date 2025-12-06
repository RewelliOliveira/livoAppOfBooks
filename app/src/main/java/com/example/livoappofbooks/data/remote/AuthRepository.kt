package com.example.livoappofbooks.data.remote

import com.example.livoappofbooks.data.remote.dto.LoginRequest
import com.example.livoappofbooks.data.remote.dto.LoginResponse
import com.example.livoappofbooks.data.remote.dto.RegisterRequest
import com.example.livoappofbooks.data.remote.dto.RegisterResponse

class AuthRepository(
    private val api: AuthService
) {
    suspend fun login(request: LoginRequest): LoginResponse {
        return api.login(request)
    }

    suspend fun register(request: RegisterRequest): RegisterResponse {
        return api.register(request)
    }
}
