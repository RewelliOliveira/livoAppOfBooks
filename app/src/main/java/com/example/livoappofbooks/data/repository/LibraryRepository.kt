package com.example.livoappofbooks.data.repository

import com.example.livoappofbooks.data.model.AddBookRequest
import com.example.livoappofbooks.data.model.Book
import com.example.livoappofbooks.data.model.ReadingLogRequest
import com.example.livoappofbooks.data.model.UserProfile
import com.example.livoappofbooks.data.service.LibraryService
import com.example.livoappofbooks.data.model.toDomainBook
import com.example.livoappofbooks.data.service.RatingRequest
import com.example.livoappofbooks.data.service.StatusUpdateRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LibraryRepository(
    private val libraryService: LibraryService
) {
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

    suspend fun addBookToLibrary(
        bookId: String,
        statusId: String
    ): Result<Unit> {
        return try {
            val response = libraryService.addBookToLibrary(
                AddBookRequest(
                    bookId = bookId,
                    bookStatus = statusId
                )
            )

            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(
                    Exception("Erro ao adicionar livro: ${response.code()}")
                )
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

    suspend fun updateBookStatus(userBookId: String, newStatus: String): Result<Boolean> = withContext(Dispatchers.IO) {
        try {
            val requestBody = StatusUpdateRequest(status = newStatus)
            val response = libraryService.updateBookStatus(userBookId, requestBody)

            if (response.isSuccessful) {
                Result.success(true)
            } else {
                Result.failure(Exception("Falha ao atualizar: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun removeBook(userBookId: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val response = libraryService.removeBookFromLibrary(userBookId)

            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Erro ao remover: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getUserRating(bookId: String): Int {
        return withContext(Dispatchers.IO) {
            try {
                val response = libraryService.getUserRating(bookId)
                if (response.isSuccessful) {
                    response.body()?.rating ?: 0
                } else {
                    0
                }
            } catch (e: Exception) {
                0
            }
        }
    }

    suspend fun saveBookRating(bookId: String, rating: Int): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val checkResponse = libraryService.getUserRating(bookId)
                val hasExistingRating = checkResponse.isSuccessful && checkResponse.body() != null
                val request = RatingRequest(rating)

                if (hasExistingRating) {
                    // ATUALIZAÇÃO (PUT)
                    val response = libraryService.updateBookRating(bookId, request)
                    if (response.isSuccessful) Result.success(Unit)
                    else Result.failure(Exception("Erro ao atualizar (PUT): ${response.code()}"))
                } else {
                    // CRIAÇÃO (POST)
                    val response = libraryService.registerBookRating(bookId, request)
                    if (response.isSuccessful) Result.success(Unit)
                    else Result.failure(Exception("Erro ao criar (POST): ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun registerBookRating(bookId: String, rating: Int): Result<Unit> = saveBookRating(bookId, rating)

    suspend fun createReadingLog(
        libraryBookId: Int,
        title: String?,
        text: String?,
        pagesRead: Int
    ): Result<Unit> {
        return try {
            val currentTime = java.time.LocalDateTime.now().toString()

            val safetitle = if (title.isNullOrBlank()) "Leitura Registrada" else title
            val safetext = if (text.isNullOrBlank()) "Sem comentário" else text

            val request = ReadingLogRequest(
                libraryBookId = libraryBookId,
                title = safetitle,
                text = safetext,
                time = currentTime,
                pagesRead = pagesRead
            )

            val response = libraryService.createReadingLog(request) // Use sua instância do service

            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Erro ao registrar leitura: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}