package com.example.livoappofbooks.data.service

import com.example.livoappofbooks.data.model.LibraryBookReponse
import retrofit2.Response
import retrofit2.http.GET

interface LibraryService {
    @GET("/library")
    suspend fun getUserBooks(): Response<List<LibraryBookReponse>>
}