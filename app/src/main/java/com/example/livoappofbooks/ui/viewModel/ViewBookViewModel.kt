package com.example.livoappofbooks.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.livoappofbooks.data.model.Book
import com.example.livoappofbooks.data.repository.LibraryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewBookViewModel(private val repository: LibraryRepository) : ViewModel() {

    private val _book = MutableStateFlow<Book?>(null)
    val book: StateFlow<Book?> = _book

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchBook(bookId: String) = viewModelScope.launch {
        _isLoading.value = true
        _error.value = null
        try {
            val result = repository.getBookById(bookId)

            result.onSuccess { fetchedBook ->
                _book.value = fetchedBook
            }.onFailure { exception ->
                _error.value = "Erro ao carregar livro: ${exception.message}"
            }
        } catch (e: Exception) {
            _error.value = "Erro inesperado: ${e.message}"
        } finally {
            _isLoading.value = false
        }
    }
}