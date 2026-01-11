package com.example.livoappofbooks.data.service

import com.example.livoappofbooks.data.model.Book
import com.example.livoappofbooks.data.model.LibraryBookReponse
import com.example.livoappofbooks.data.model.UserProfile
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

data class StatusUpdateRequest(
    val status: String
)

interface LibraryService {
    @GET("/library")
    suspend fun getUserBooks(): Response<List<LibraryBookReponse>>

    @GET("/books/{book_id}")
    suspend fun getBookById(@Path("book_id") bookId: String): Response<Book>

    @GET("user/profile")
    suspend fun getUserProfile(): UserProfile

    @PUT("/library/{book_id}")
    suspend fun updateBookStatus(
        @Path("book_id") bookId: String,
        @Body request: StatusUpdateRequest
    ): Response<Unit>

    @DELETE("/library/{book_id}")
    suspend fun removeBookFromLibrary(
        @Path("book_id") userBookId: String
    ): Response<Unit>
}