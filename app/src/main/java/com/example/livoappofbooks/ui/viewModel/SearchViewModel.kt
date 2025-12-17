package com.example.livoappofbooks.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.livoappofbooks.data.repository.SearchRepository
import com.example.livoappofbooks.data.repository.SearchRepositoryImpl
import com.example.livoappofbooks.data.model.Book
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
    private val repository: SearchRepository = SearchRepositoryImpl()
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
                val results = repository.searchBooks(q)
                _uiState.value = SearchUiState.Success(results)
            } catch (t: Throwable) {
                _uiState.value = SearchUiState.Error(t.message ?: "Erro na busca")
            }
        }
    }
}

