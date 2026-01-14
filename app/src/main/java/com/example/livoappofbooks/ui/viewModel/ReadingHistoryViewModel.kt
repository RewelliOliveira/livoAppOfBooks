package com.example.livoappofbooks.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.livoappofbooks.data.model.Book
import com.example.livoappofbooks.data.repository.LibraryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// Estado da UI do header
data class ReadingHistoryHeaderUiState(
    val book: Book? = null,
    val isLoading: Boolean = true,
    val error: String? = null
)

class ReadingHistoryHeaderViewModel(
    private val bookId: String,
    private val repository: LibraryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReadingHistoryHeaderUiState())
    val uiState: StateFlow<ReadingHistoryHeaderUiState> = _uiState.asStateFlow()

    init {
        fetchBook()
    }

    private fun fetchBook() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                val result = repository.getBookById(bookId)
                if (result.isSuccess) {
                    val bookDetails = result.getOrThrow()
                    _uiState.update { it.copy(book = bookDetails, isLoading = false) }
                } else {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.exceptionOrNull()?.message ?: "Erro ao carregar livro"
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message ?: "Erro desconhecido") }
            }
        }
    }
}

class ReadingHistoryHeaderViewModelFactory(
    private val bookId: String,
    private val repository: LibraryRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReadingHistoryHeaderViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ReadingHistoryHeaderViewModel(bookId, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
