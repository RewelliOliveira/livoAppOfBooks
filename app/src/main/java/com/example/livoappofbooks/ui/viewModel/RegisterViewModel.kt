package com.example.livoappofbooks.ui.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.livoappofbooks.data.remote.AuthRepository
import com.example.livoappofbooks.data.remote.AuthService
import com.example.livoappofbooks.data.remote.RetrofitInstance
import com.example.livoappofbooks.data.remote.dto.RegisterRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class RegisterUiState {
    object Idle : RegisterUiState()
    object Loading : RegisterUiState()
    object Success : RegisterUiState()
    data class Error(val message: String) : RegisterUiState()
}

class RegisterViewModel(context: Context) : ViewModel() {

    private val authService = RetrofitInstance
        .createService(context, AuthService::class.java)
    private val repository: AuthRepository = AuthRepository(authService)

    private val _uiState = MutableStateFlow<RegisterUiState>(RegisterUiState.Idle)
    val uiState: StateFlow<RegisterUiState> = _uiState

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = RegisterUiState.Loading
            try {
                repository.register(
                    RegisterRequest(name = name, email = email, password = password)
                )
                _uiState.value = RegisterUiState.Success
            } catch (e: Exception) {
                val errorMessage = when (e) {
                    is HttpException -> {
                        when (e.code()) {
                            409 -> "Este e-mail já está em uso."
                            400 -> "Dados inválidos. Verifique os campos e tente novamente."
                            500 -> "Ocorreu um erro no servidor. Tente novamente mais tarde."
                            else -> "Ocorreu um erro inesperado."
                        }
                    }
                    is IOException -> "Sem conexão com a internet."
                    else -> "Ocorreu um erro desconhecido."
                }
                _uiState.value = RegisterUiState.Error(errorMessage)
            }
        }
    }

    fun resetState() {
        _uiState.value = RegisterUiState.Idle
    }
}
