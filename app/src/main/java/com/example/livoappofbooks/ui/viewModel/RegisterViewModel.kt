package com.example.livoappofbooks.ui.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.livoappofbooks.data.remote.AuthRepository
import com.example.livoappofbooks.data.remote.AuthService
import com.example.livoappofbooks.data.remote.RetrofitInstance
import com.example.livoappofbooks.data.remote.dto.RegisterRequest
import com.example.livoappofbooks.data.remote.dto.RegisterResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class RegisterUiState {
    object Idle : RegisterUiState()
    object Loading : RegisterUiState()
    data class Success(val user: RegisterResponse) : RegisterUiState()
    data class Error(val message: String) : RegisterUiState()
}

class RegisterViewModel(context: Context) : ViewModel() {

    private val authService: AuthService = RetrofitInstance.getApi(context)
    private val repository: AuthRepository = AuthRepository(authService)

    private val _uiState = MutableStateFlow<RegisterUiState>(RegisterUiState.Idle)
    val uiState: StateFlow<RegisterUiState> = _uiState

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = RegisterUiState.Loading
            try {
                val response = repository.register(
                    RegisterRequest(name = name, email = email, password = password)
                )
                _uiState.value = RegisterUiState.Success(response)
            } catch (e: Exception) {
                _uiState.value = RegisterUiState.Error(e.message ?: "Erro desconhecido")
            }
        }
    }

    fun resetState() {
        _uiState.value = RegisterUiState.Idle
    }
}
