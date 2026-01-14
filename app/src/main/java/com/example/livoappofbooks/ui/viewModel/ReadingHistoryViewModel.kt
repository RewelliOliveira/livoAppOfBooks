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

data class ReadingHistoryHeaderUiState(
    val book: Book? = null,
    val userRating: Int = 0,
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
        fetchBookDetails()
    }

    private fun fetchBookDetails() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            launch { fetchUserRating() }

            try {
                val result = repository.getBookById(bookId)
                if (result.isSuccess) {
                    var bookDetails = result.getOrThrow()

                    try {
                        val userBooksResult = repository.getUserBooks()
                        if (userBooksResult.isSuccess) {
                            val userBooks = userBooksResult.getOrThrow()
                            val bookInLibrary = userBooks.find { it.id == bookId }

                            if (bookInLibrary != null) {
                                val regFromList = bookInLibrary.libraryRegistration
                                val currentReg = bookDetails.libraryRegistration
                                val finalReg = currentReg?.copy(
                                    status = regFromList?.status,
                                    shelf = regFromList?.shelf,
                                    readingProgress = regFromList?.readingProgress,
                                    id = regFromList?.id
                                ) ?: regFromList

                                bookDetails = bookDetails.copy(
                                    libraryRegistration = finalReg,
                                    personalLibrary = true
                                )
                            }
                        }
                    } catch (e: Exception) { e.printStackTrace() }

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

    private suspend fun fetchUserRating() {
        try {
            val rating = repository.getUserRating(bookId)
            _uiState.update { it.copy(userRating = rating) }
        } catch (_: Exception) { }
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
