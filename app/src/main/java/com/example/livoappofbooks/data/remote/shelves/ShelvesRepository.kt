package com.example.livoappofbooks.data.remote.shelves

import android.content.Context
import com.example.livoappofbooks.data.remote.RetrofitInstance
import com.example.livoappofbooks.data.remote.shelves.dto.AddBookToShelfRequest
import com.example.livoappofbooks.data.remote.shelves.dto.BookShelfRequest
import com.example.livoappofbooks.data.remote.shelves.dto.ShelfRequest
import com.example.livoappofbooks.data.remote.shelves.dto.ShelfResponse
import com.example.livoappofbooks.data.remote.shelves.dto.ShelfUpdateRequest

class ShelvesRepository(
    context: Context
) {

    private val service = RetrofitInstance
        .createService(context, ShelvesService::class.java)

    suspend fun getAllShelves(): List<ShelfResponse> {
        return service.getAllShelves()
    }

    suspend fun createShelf(
        name: String,
        description: String?,
        books: List<BookShelfRequest>
    ): ShelfResponse {
        val body = ShelfRequest(
            name = name,
            description = description,
            // Permite criar a prateleira já com uma lista de livros selecionados
            books = books
        )
        return service.createShelf(body)
    }

    suspend fun deleteShelf(id: String) {
        service.deleteShelf(id)
    }

    suspend fun getShelfById(id: String): ShelfResponse {
        return service.getShelfById(id)
    }

    suspend fun updateShelf(
        id: String,
        name: String,
        description: String?
    ): ShelfResponse {
        val body = ShelfUpdateRequest(
            name = name,
            description = description
        )
        return service.updateShelf(id, body)
    }

    suspend fun removeBookFromShelf(shelfId: String, bookId: String) {
        service.deleteBookFromShelf(shelfId, bookId)
    }

    suspend fun addBookToShelf(
        shelfId: String,
        registrationId: Long,
        bookId: String,
        status: String
    ): Result<Unit> {
        return try {
            val request = AddBookToShelfRequest(
                id = registrationId,
                bookId = bookId,
                status = status
            )
            val response = service.addBookToShelf(shelfId, request)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Erro ao adicionar livro: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
