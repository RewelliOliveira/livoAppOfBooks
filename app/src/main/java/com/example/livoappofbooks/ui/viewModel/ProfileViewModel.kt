package com.example.livoappofbooks.ui.viewModel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.livoappofbooks.data.model.ProfileUiState
import com.example.livoappofbooks.data.repository.LibraryRepository
import com.example.livoappofbooks.data.remote.local.TokenManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

class ProfileViewModel(
    private val repository: LibraryRepository,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState(isLoading = true))
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    private val _profileImagePath = MutableStateFlow<String?>(null)
    val profileImagePath: StateFlow<String?> = _profileImagePath.asStateFlow()

    init {
        _profileImagePath.value = tokenManager.getProfilePath()
        fetchUserProfile()
    }

    fun fetchUserProfile() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val profile = repository.getUserProfile()
                _uiState.update {
                    it.copy(isLoading = false, userProfile = profile, error = null)
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, error = e.message ?: "Erro desconhecido")
                }
            }
        }
    }

    fun updateProfileImage(context: Context, uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            val savedPath = saveImageToInternalStorage(context, uri)
            if (savedPath != null) {
                tokenManager.saveProfilePath(savedPath)
                _profileImagePath.value = savedPath
            }
        }
    }

    private fun saveImageToInternalStorage(context: Context, uri: Uri): String? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val fileName = "profile_picture.jpg"
            val file = File(context.filesDir, fileName)
            val outputStream = FileOutputStream(file)

            inputStream?.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }
            file.absolutePath
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun logout() {
        tokenManager.clearToken()
    }
}

class ProfileViewModelFactory(
    private val repository: LibraryRepository,
    private val tokenManager: TokenManager
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(repository, tokenManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}