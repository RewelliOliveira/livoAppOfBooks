package com.example.livoappofbooks.ui.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.livoappofbooks.data.remote.AuthRepository
import com.example.livoappofbooks.data.remote.AuthService
import com.example.livoappofbooks.data.remote.RetrofitInstance
import com.example.livoappofbooks.data.remote.dto.LoginRequest
import com.example.livoappofbooks.data.remote.dto.LoginResponse
import com.example.livoappofbooks.data.remote.local.TokenManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class LoginUiState {
    object Idle : LoginUiState()
    object Loading : LoginUiState()
    data class Success(val user: LoginResponse) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}

class LoginViewModel(
    context: Context
) : ViewModel() {

    private val authService: AuthService = RetrofitInstance.getApi(context)
    private val repository: AuthRepository = AuthRepository(authService)
    private val tokenManager = TokenManager(context)

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading
            try {
                val response = repository.login(
                    LoginRequest(email = email, password = password)
                )

                response.acessToken?.let { token ->
                    tokenManager.saveToken(token)
                }

                _uiState.value = LoginUiState.Success(response)
            } catch (e: Exception) {
                _uiState.value = LoginUiState.Error(e.message ?: "Erro desconhecido")
            }
        }
    }

    fun resetState() {
        _uiState.value = LoginUiState.Idle
    }
}