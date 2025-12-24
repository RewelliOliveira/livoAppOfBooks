package com.example.livoappofbooks.ui.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.livoappofbooks.data.remote.shelves.ShelvesRepository
import com.example.livoappofbooks.data.remote.shelves.dto.ShelfResponse
import kotlinx.coroutines.launch

class ShelvesViewModel(
    context: Context
) : ViewModel() {

    private val repository = ShelvesRepository(context)

    private val _shelves = MutableLiveData<List<ShelfResponse>>()
    val shelves: LiveData<List<ShelfResponse>> = _shelves

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun loadShelves() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null

            try {
                _shelves.value = repository.getAllShelves()
            } catch (e: Exception) {
                _error.value = "Erro ao carregar prateleiras"
            } finally {
                _loading.value = false
            }
        }
    }

    fun createShelf(
        name: String,
        description: String?
    ) {
        viewModelScope.launch {
            _loading.value = true

            try {
                repository.createShelf(name, description)
                loadShelves() // recarrega a lista
            } catch (e: Exception) {
                _error.value = "Erro ao criar prateleira"
                _loading.value = false
            }
        }
    }

    fun deleteShelf(id: String) {
        viewModelScope.launch {
            _loading.value = true

            try {
                repository.deleteShelf(id)
                loadShelves()
            } catch (e: Exception) {
                _error.value = "Erro ao remover prateleira"
                _loading.value = false
            }
        }
    }
}
