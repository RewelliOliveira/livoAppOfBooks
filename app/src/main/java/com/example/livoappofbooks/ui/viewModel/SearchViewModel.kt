package com.example.livoappofbooks.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.livoappofbooks.data.repository.SearchRepository
import com.example.livoappofbooks.data.repository.SearchRepositoryImpl
import com.example.livoappofbooks.data.model.Book
import com.example.livoappofbooks.data.repository.LibraryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface SearchUiState {
    object Idle : SearchUiState
    object Loading : SearchUiState
    data class Success(val results: List<Book>) : SearchUiState
    data class Error(val message: String) : SearchUiState
}

class SearchViewModel(
    private val repository: SearchRepository = SearchRepositoryImpl(),
    private val libraryRepository: LibraryRepository
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Idle)
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun onQueryChange(newQuery: String) {
        _query.value = newQuery
    }

    fun search() {
        val q = _query.value.trim()
        if (q.isBlank()) {
            _uiState.value = SearchUiState.Error("Digite uma busca.")
            return
        }

        viewModelScope.launch {
            _uiState.value = SearchUiState.Loading
            try {
                val searchResults = repository.searchBooks(q)
                val userLibraryResult = libraryRepository.getUserBooks()

                val userBookIds = if (userLibraryResult.isSuccess) {
                    userLibraryResult.getOrThrow().map { it.id }.toSet()
                } else {
                    emptySet()
                }

                val finalResults = searchResults.map { book ->
                    if (userBookIds.contains(book.id)) {
                        book.copy(personalLibrary = true)
                    } else {
                        book
                    }
                }

                _uiState.value = SearchUiState.Success(finalResults)

            } catch (t: Throwable) {
                _uiState.value = SearchUiState.Error(t.message ?: "Erro na busca")
            }
        }
    }

    fun addBookToLibrary(bookId: String, statusId: String) {
        viewModelScope.launch {
            try {
                val result = libraryRepository.addBookToLibrary(bookId, statusId)
                if (result.isSuccess) {
                    search()
                }
            } catch (e: Exception) {
                // Sem tratamento de erro por enquanto
            }
        }
    }
}

