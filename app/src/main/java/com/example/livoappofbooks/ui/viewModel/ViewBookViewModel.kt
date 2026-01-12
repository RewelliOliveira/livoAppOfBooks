package com.example.livoappofbooks.ui.viewModel

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.livoappofbooks.data.model.Book
import com.example.livoappofbooks.data.remote.shelves.ShelvesRepository
import com.example.livoappofbooks.data.remote.shelves.dto.ShelfResponse
import com.example.livoappofbooks.data.repository.LibraryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ViewBookViewModel(
    private val repository: LibraryRepository,
    private val shelvesRepository: ShelvesRepository? = null
) : ViewModel() {

    private val _book = MutableStateFlow<Book?>(null)
    val book: StateFlow<Book?> = _book

    private val _userRating = MutableStateFlow(0)
    val userRating: StateFlow<Int> = _userRating

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _bookAddedEvent = MutableStateFlow(false)
    val bookAddedEvent = _bookAddedEvent.asStateFlow()

    private val _shelves = MutableStateFlow<List<ShelfResponse>>(emptyList())
    val shelves: StateFlow<List<ShelfResponse>> = _shelves.asStateFlow()

    private val _shelfAddedEvent = MutableStateFlow<String?>(null)
    val shelfAddedEvent: StateFlow<String?> = _shelfAddedEvent.asStateFlow()

    fun fetchUserShelves() {
        shelvesRepository ?: return
        viewModelScope.launch {
            try {
                _shelves.value = shelvesRepository.getAllShelves()
            } catch (e: Exception) {
                // Silently fail, shelves just won't be available
            }
        }
    }

    fun addBookToShelf(shelfId: String) {
        val currentBook = _book.value ?: return
        val libReg = currentBook.libraryRegistration ?: return
        val registrationId = libReg.id?.toLongOrNull() ?: return
        val status = libReg.status ?: "QUERO_LER"
        
        shelvesRepository ?: return
        viewModelScope.launch {
            val result = shelvesRepository.addBookToShelf(
                shelfId = shelfId,
                registrationId = registrationId,
                bookId = currentBook.id,
                status = status
            )
            if (result.isSuccess) {
                val shelfName = _shelves.value.find { it.id == shelfId }?.name ?: "prateleira"
                _shelfAddedEvent.value = shelfName
            } else {
                _error.value = "Erro ao adicionar à prateleira"
            }
        }
    }

    fun resetShelfAddedEvent() {
        _shelfAddedEvent.value = null
    }

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
                // PATCH
                val result = repository.updateBookStatus(
                    registrationId.toString(),
                    newStatusId
                )

                if (result.isSuccess) {
                    fetchBook(bookId)
                }
            } else {
                // POST
                val result = repository.addBookToLibrary(
                    bookId = bookId,
                    statusId = newStatusId
                )

                if (result.isSuccess) {
                    _bookAddedEvent.value = true
                } else {
                    _error.value = result.exceptionOrNull()?.message
                }
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

            sendRatingToApi(bookId, rating)
        }
    }

    fun resetError() {
        _error.value = null
    }

    private suspend fun sendRatingToApi(bookId: String, rating: Int) {
        val result = repository.registerBookRating(bookId, rating)

        if (result.isSuccess) {
            _userRating.value = rating
            fetchBook(bookId)
        } else {
            val errorCode = result.exceptionOrNull()?.message ?: "UNKNOWN"
            _error.value = errorCode
        }
    }
}