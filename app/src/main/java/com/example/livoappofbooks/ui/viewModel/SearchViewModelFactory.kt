package com.example.livoappofbooks.ui.viewModel
//Tive que criar essa fabrica pq o SearchViewModel precisa de parametros no construtor que tem em library
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.livoappofbooks.data.repository.LibraryRepository
import com.example.livoappofbooks.data.repository.SearchRepositoryImpl

class SearchViewModelFactory(
    private val libraryRepository: LibraryRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            // O SearchRepository tem valor padrão, então só precisa passar o libraryRepository
            return SearchViewModel(
                repository = SearchRepositoryImpl(),
                libraryRepository = libraryRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}