package com.example.livoappofbooks.data.service

import com.example.livoappofbooks.data.model.AddBookRequest
import com.example.livoappofbooks.data.model.Book
import com.example.livoappofbooks.data.model.LibraryBookReponse
import com.example.livoappofbooks.data.model.UserProfile
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

data class StatusUpdateRequest(
    val status: String
)

@JsonClass(generateAdapter = true)
data class UserRatingResponse(
    @Json(name = "id") val id: String,
    @Json(name = "rating") val rating: Int, // O valor que queremos (ex: 4)
    @Json(name = "bookId") val bookId: String
)

@JsonClass(generateAdapter = true)
data class RatingRequest(
    @Json(name = "rating") val rating: Int
)

interface LibraryService {
    @GET("/library")
    suspend fun getUserBooks(): Response<List<LibraryBookReponse>>

    @GET("/books/{book_id}")
    suspend fun getBookById(@Path("book_id") bookId: String): Response<Book>

    @GET("user/profile")
    suspend fun getUserProfile(): UserProfile

    @GET("/books/{bookId}/rating/me")
    suspend fun getUserRating(@Path("bookId") bookId: String): Response<UserRatingResponse>

    @PUT("/library/{book_id}")
    suspend fun updateBookStatus(
        @Path("book_id") bookId: String,
        @Body request: StatusUpdateRequest
    ): Response<Unit>

    @DELETE("/library/{book_id}")
    suspend fun removeBookFromLibrary(
        @Path("book_id") userBookId: String
    ): Response<Unit>

    @POST("library")
    suspend fun addBookToLibrary(
        @Body body: AddBookRequest
    ): Response<Unit>

    // NOVO ENDPOINT DE AVALIAÇÃO
    @POST("/books/{book_id}/rating")
    suspend fun registerBookRating(
        @Path("book_id") bookId: String,
        @Body request: RatingRequest
    ): Response<Unit>
}