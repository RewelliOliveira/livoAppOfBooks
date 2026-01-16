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

// Estado da UI
data class RegisterReadingUiState(
    val book: Book? = null,
    val userRating: Int = 0,
    val isLoadingBook: Boolean = true,

    // Campos do formulário
    val title: String = "",
    val pages: String = "",
    val review: String = "",

    // Estado do envio
    val isSubmitting: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)

class RegisterReadingViewModel(
    private val bookId: String, // ID do Google (String)
    private val repository: LibraryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterReadingUiState())
    val uiState: StateFlow<RegisterReadingUiState> = _uiState.asStateFlow()

    init {
        fetchBookDetails()
    }

    private fun fetchBookDetails() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingBook = true, error = null) }


            launch { fetchUserRating() }

            val detailResult = repository.getBookById(bookId)

            if (detailResult.isSuccess) {
                var bookDetails = detailResult.getOrThrow()

                try {
                    val userLibraryResult = repository.getUserBooks()
                    if (userLibraryResult.isSuccess) {
                        val userBooks = userLibraryResult.getOrThrow()
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
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                _uiState.update { it.copy(book = bookDetails, isLoadingBook = false) }
            } else {
                _uiState.update {
                    it.copy(
                        isLoadingBook = false,
                        error = detailResult.exceptionOrNull()?.message ?: "Erro ao carregar livro"
                    )
                }
            }
        }
    }

    private suspend fun fetchUserRating() {
        try {
            val rating = repository.getUserRating(bookId)
            _uiState.update { it.copy(userRating = rating) }
        } catch (e: Exception) {
        }
    }


    fun onTitleChange(newTitle: String) {
        _uiState.update { it.copy(title = newTitle) }
    }

    fun onPagesChange(newPages: String) {
        if (newPages.all { it.isDigit() }) {
            _uiState.update { it.copy(pages = newPages) }
        }
    }

    fun onReviewChange(newReview: String) {
        _uiState.update { it.copy(review = newReview) }
    }


    fun submitLog() {
        val currentState = _uiState.value
        val pagesInt = currentState.pages.toIntOrNull()
        val book = currentState.book

        if (pagesInt == null || pagesInt <= 0) {
            _uiState.update { it.copy(error = "Informe a quantidade de páginas lidas.") }
            return
        }

        if (book?.pageCount != null && pagesInt > book.pageCount) {
            _uiState.update { it.copy(error = "Você não pode ler mais páginas que o total do livro.") }
            return
        }


        val libraryRegIdStr = book?.libraryRegistration?.id
        val libraryRegId = libraryRegIdStr?.toIntOrNull()

        if (libraryRegId == null) {
            _uiState.update { it.copy(error = "Erro: Este livro não parece estar na sua biblioteca.") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isSubmitting = true, error = null) }

            val result = repository.createReadingLog(
                libraryBookId = libraryRegId,
                title = currentState.title,
                text = currentState.review,
                pagesRead = pagesInt
            )

            if (result.isSuccess) {
                _uiState.update { it.copy(isSubmitting = false, isSuccess = true) }
            } else {
                val errorMsg = result.exceptionOrNull()?.message ?: "Erro desconhecido ao salvar"
                _uiState.update { it.copy(isSubmitting = false, error = errorMsg) }
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}

class RegisterReadingViewModelFactory(
    private val bookId: String,
    private val repository: LibraryRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterReadingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegisterReadingViewModel(bookId, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}