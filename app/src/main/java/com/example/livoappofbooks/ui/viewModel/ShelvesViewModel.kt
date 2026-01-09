package com.example.livoappofbooks.ui.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.livoappofbooks.data.remote.shelves.ShelvesRepository
import com.example.livoappofbooks.data.remote.shelves.dto.ShelfResponse
import kotlinx.coroutines.launch

class ShelvesViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShelvesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ShelvesViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

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

    private val _selectedShelf = MutableLiveData<ShelfResponse?>()
    val selectedShelf: LiveData<ShelfResponse?> = _selectedShelf

    private val _operationSuccess = MutableLiveData<Boolean>()
    val operationSuccess: LiveData<Boolean> = _operationSuccess

    // Estado do formulário para sobreviver à rotação de tela
    var formName: String = ""
    var formDescription: String = ""

    // Limpa o estado do formulário ao abrir uma nova tela
    fun clearFormState() {
        formName = ""
        formDescription = ""
    }

    fun resetOperationSuccess() {
        _operationSuccess.value = false
    }

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

    fun loadShelfDetails(id: String) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null

            try {
                _selectedShelf.value = repository.getShelfById(id)
            } catch (e: Exception) {
                _error.value = "Erro ao carregar detalhes da prateleira"
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
                _operationSuccess.value = true
                // Limpa o formulário após sucesso
                formName = ""
                formDescription = ""
                loadShelves()
            } catch (e: Exception) {
                _error.value = "Erro ao criar prateleira"
            } finally {
                _loading.value = false
            }
        }
    }

    fun updateShelf(
        id: String,
        name: String,
        description: String?
    ) {
        viewModelScope.launch {
            _loading.value = true

            try {
                repository.updateShelf(id, name, description)
                _operationSuccess.value = true
                // Limpa o formulário após sucesso
                formName = ""
                formDescription = ""
                loadShelves()
            } catch (e: Exception) {
                _error.value = "Erro ao atualizar prateleira"
            } finally {
                _loading.value = false
            }
        }
    }

    fun deleteShelf(id: String) {
        viewModelScope.launch {
            _loading.value = true

            try {
                repository.deleteShelf(id)
                _operationSuccess.value = true
                loadShelves()
            } catch (e: Exception) {
                _error.value = "Erro ao remover prateleira"
            } finally {
                _loading.value = false
            }
        }
    }

    fun removeBookFromShelf(shelfId: String, bookId: Long) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null

            try {
                repository.removeBookFromShelf(shelfId, bookId)
                // Recarrega os detalhes da prateleira para atualizar a lista
                loadShelfDetails(shelfId)
                // Poderíamos usar operationSuccess aqui se quisermos mostrar uma mensagem
                 _operationSuccess.value = true
            } catch (e: Exception) {
                _error.value = "Erro ao remover livro da prateleira"
            } finally {
                _loading.value = false
            }
        }
    }
}
