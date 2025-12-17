package com.example.livoappofbooks.ui.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.livoappofbooks.data.remote.RetrofitInstance
import com.example.livoappofbooks.data.repository.LibraryRepository
import com.example.livoappofbooks.data.service.LibraryService
import com.example.livoappofbooks.domain.model.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface LibraryUiState {
    object Idle : LibraryUiState
    object Loading : LibraryUiState
    object Success : LibraryUiState
    data class Error(val message: String) : LibraryUiState
}

class LibraryViewModel(
    context: Context
) : ViewModel() {

    private val service = RetrofitInstance
        .createService(context, LibraryService::class.java)

    private val repository = LibraryRepository(service)

    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books

    private val _uiState = MutableStateFlow<LibraryUiState>(LibraryUiState.Idle)
    val uiState: StateFlow<LibraryUiState> = _uiState

    fun loadBooks() {
        viewModelScope.launch {
            _uiState.value = LibraryUiState.Loading

            repository.getUserBooks()
                .onSuccess {
                    _books.value = it
                    _uiState.value = LibraryUiState.Success
                }
                .onFailure {
                    _uiState.value = LibraryUiState.Error(
                        it.message ?: "Erro ao carregar biblioteca"
                    )
                }
        }
    }
}
