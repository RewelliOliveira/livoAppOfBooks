package com.example.livoappofbooks.data.service

import com.example.livoappofbooks.data.model.Book
import com.example.livoappofbooks.data.model.LibraryBookReponse
import com.example.livoappofbooks.data.model.UserProfile
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LibraryService {
    @GET("/library")
    suspend fun getUserBooks(): Response<List<LibraryBookReponse>>

    @GET("/books/{book_id}")
    suspend fun getBookById(@Path("book_id") bookId: String): Response<Book>

    @GET("user/profile")
    suspend fun getUserProfile(): UserProfile
}