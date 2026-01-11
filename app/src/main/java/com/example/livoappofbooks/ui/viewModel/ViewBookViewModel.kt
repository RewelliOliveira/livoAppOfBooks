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

    private val _userRating = MutableStateFlow(0)
    val userRating: StateFlow<Int> = _userRating

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchBook(bookId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            launch { fetchUserRating(bookId) }

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

                _book.value = bookDetails
            } else {
                _error.value = detailResult.exceptionOrNull()?.message ?: "Erro desconhecido"
            }
            _isLoading.value = false
        }
    }

    fun fetchUserRating(bookId: String) {
        viewModelScope.launch {
            try {
                val rating = repository.getUserRating(bookId)
                _userRating.value = rating
            } catch (e: Exception) {
                _userRating.value = 0
            }
        }
    }

    fun updateBookStatus(bookId: String, newStatusId: String) {
        viewModelScope.launch {
            val currentBook = _book.value
            val registrationId = currentBook?.libraryRegistration?.id

            if (registrationId != null) {
                val result = repository.updateBookStatus(registrationId.toString(), newStatusId)

                if (result.isSuccess) {
                    _book.value?.let { bookState ->
                        val currentReg = bookState.libraryRegistration
                        val updatedReg = currentReg?.copy(status = newStatusId)

                        if (updatedReg != null) {
                            _book.value = bookState.copy(libraryRegistration = updatedReg)
                        } else {
                            fetchBook(bookId)
                        }
                    }
                } else {
                    fetchBook(bookId)
                }
            } else {
                val addResult = repository.updateBookStatus(bookId, newStatusId)
                if (addResult.isSuccess) fetchBook(bookId)
            }
        }
    }

    fun removeBook(onSuccess: () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            val currentBook = _book.value
            val registrationId = currentBook?.libraryRegistration?.id

            if (registrationId != null) {
                val result = repository.removeBook(registrationId.toString())

                if (result.isSuccess) {
                    _book.value = null
                    onSuccess()
                } else {
                    _error.value = "Erro ao remover livro: ${result.exceptionOrNull()?.message}"
                }
            } else {
                _error.value = "Erro: Livro não está na biblioteca."
            }

            _isLoading.value = false
        }
    }

    fun rateBook(bookId: String, rating: Int) {
        viewModelScope.launch {
            val currentBook = _book.value
            val libReg = currentBook?.libraryRegistration

            if (libReg?.status != "LIDO") {
                _error.value = "NOT_READ"
                return@launch
            }

            val result = repository.saveBookRating(bookId, rating)

            if (result.isSuccess) {
                _userRating.value = rating
                fetchBook(bookId)
            } else {
                val errorMsg = result.exceptionOrNull()?.message ?: "UNKNOWN"
                _error.value = errorMsg
            }
        }
    }

    fun resetError() {
        _error.value = null
    }
}