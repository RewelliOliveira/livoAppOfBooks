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

    fun fetchBook(bookId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

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
                                readingProgress = regFromList?.readingProgress
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

    fun updateBookStatus(bookId: String, newStatusId: String) {
        viewModelScope.launch {
            val result = repository.updateBookStatus(bookId, newStatusId)

            if (result.isSuccess) {
                _book.value?.let { currentBook ->
                    val currentReg = currentBook.libraryRegistration
                    val updatedReg = currentReg?.copy(status = newStatusId)

                    if (updatedReg != null) {
                        _book.value = currentBook.copy(libraryRegistration = updatedReg)
                    } else {
                        fetchBook(bookId)
                    }
                }
            }
        }
    }
}