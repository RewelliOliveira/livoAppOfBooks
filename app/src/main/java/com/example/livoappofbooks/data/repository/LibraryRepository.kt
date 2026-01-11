package com.example.livoappofbooks.data.repository

import com.example.livoappofbooks.data.model.Book
import com.example.livoappofbooks.data.model.UserProfile
import com.example.livoappofbooks.data.service.LibraryService
import com.example.livoappofbooks.data.model.toDomainBook
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LibraryRepository(
    private val libraryService: LibraryService) {
    suspend fun getUserBooks(): Result<List<Book>> = withContext(Dispatchers.IO) {
        try {
            val response = libraryService.getUserBooks()

            if (response.isSuccessful) {
                val userBooks = response.body() ?: emptyList()
                val books = userBooks.map { it.toDomainBook() }
                Result.success(books)
            } else {
                Result.failure(Exception("Erro: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getBookById(bookId: String): Result<Book> = withContext(Dispatchers.IO) {
        try {
            val response = libraryService.getBookById(bookId)

            if (response.isSuccessful) {
                val book = response.body()
                if (book != null) {
                    Result.success(book)
                } else {
                    Result.failure(Exception("Book not found"))
                }
            } else {
                Result.failure(Exception("Erro: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getUserProfile(): UserProfile = withContext(Dispatchers.IO) {
        libraryService.getUserProfile()
    }
}