package com.example.livoappofbooks.data.remote.shelves

import com.example.livoappofbooks.data.remote.shelves.dto.BookShelf
import com.example.livoappofbooks.data.remote.shelves.dto.SearchBookResponse
import com.example.livoappofbooks.data.remote.shelves.dto.ShelfRequest
import com.example.livoappofbooks.data.remote.shelves.dto.ShelfResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ShelvesService {

    @POST("/library/shelfs")
    suspend fun createShelf(
        @Body body: ShelfRequest
    ): ShelfResponse

    @GET("/library/shelfs/all")
    suspend fun getAllShelves(): List<ShelfResponse>

    @GET("/library/shelfs/{id}")
    suspend fun getShelfById(
        @Path("id") id: String
    ): ShelfResponse

    @GET("/library/shelfs/{id}/books")
    suspend fun getBooksByStatus(
        @Path("id") id: String,
        @Query("status") status: String
    ): List<BookShelf>

    @DELETE("/library/shelfs/{id}")
    suspend fun deleteShelf(
        @Path("id") id: String
    )

    @GET("/library/shelfs/{id}/search/{term}")
    suspend fun searchBooks(
        @Path("id") shelfId: String,
        @Path("term") term: String
    ): List<SearchBookResponse>

    @retrofit2.http.PUT("/library/shelfs/{id}")
    suspend fun updateShelf(
        @Path("id") id: String,
        @Body body: ShelfRequest
    ): ShelfResponse
}
