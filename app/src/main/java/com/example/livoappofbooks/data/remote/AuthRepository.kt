package com.example.livoappofbooks.data.remote

import com.example.livoappofbooks.data.remote.dto.LoginRequest
import com.example.livoappofbooks.data.remote.dto.LoginResponse

class AuthRepository(
    private val api: AuthService
) {
    suspend fun login(request: LoginRequest): LoginResponse {
        return api.login(request)
    }
}
