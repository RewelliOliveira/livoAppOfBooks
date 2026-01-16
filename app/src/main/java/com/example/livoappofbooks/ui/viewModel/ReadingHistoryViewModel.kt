package com.example.livoappofbooks.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.livoappofbooks.data.model.Book
import com.example.livoappofbooks.data.model.ReadingLogResponse
import com.example.livoappofbooks.data.repository.LibraryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ReadingHistoryUiState(
    val isLoading: Boolean = true,
    val book: Book? = null,
    val userRating: Int = 0,
    val logs: List<ReadingLogResponse> = emptyList(),
    val error: String? = null
)

class ReadingHistoryViewModel(
    private val targetBookId: String,
    private val repository: LibraryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReadingHistoryUiState())
    val uiState: StateFlow<ReadingHistoryUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                val rating = repository.getUserRating(targetBookId)
                val bookResult = repository.getBookById(targetBookId)
                var bookDetails = bookResult.getOrNull()
                var logs: List<ReadingLogResponse> = emptyList()

                if (bookDetails != null) {
                    var libraryInternalId = bookDetails.libraryRegistration?.id

                    if (libraryInternalId == null) {
                        val userBooks = repository.getUserBooks().getOrNull()
                        val foundInUserLibrary = userBooks?.find { it.id == targetBookId }

                        if (foundInUserLibrary != null) {
                            libraryInternalId = foundInUserLibrary.libraryRegistration?.id
                            bookDetails = bookDetails.copy(
                                personalLibrary = true,
                                libraryRegistration = foundInUserLibrary.libraryRegistration
                            )
                        }
                    }

                    if (!libraryInternalId.isNullOrEmpty()) {
                        logs = repository.getReadingLogs(libraryInternalId).getOrDefault(emptyList())
                    }
                }

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        book = bookDetails,
                        userRating = rating,
                        logs = logs,
                        error = if (bookDetails == null) "Livro não encontrado" else null
                    )
                }

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, error = e.message)
                }
            }
        }
    }
}

class ReadingHistoryViewModelFactory(
    private val bookId: String,
    private val repository: LibraryRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReadingHistoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ReadingHistoryViewModel(bookId, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}