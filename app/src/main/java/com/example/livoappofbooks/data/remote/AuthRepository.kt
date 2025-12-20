package com.example.livoappofbooks.data.remote

import com.example.livoappofbooks.data.remote.dto.LoginRequest
import com.example.livoappofbooks.data.remote.dto.LoginResponse
import com.example.livoappofbooks.data.remote.dto.RegisterRequest

class AuthRepository(
    private val api: AuthService
) {
    suspend fun login(request: LoginRequest): LoginResponse {
        return api.login(request)
    }

    suspend fun register(request: RegisterRequest): Unit {
        return api.register(request)
    }
}
