package com.example.livoappofbooks.data.remote.shelves

import android.content.Context
import com.example.livoappofbooks.data.remote.RetrofitInstance
import com.example.livoappofbooks.data.remote.shelves.dto.ShelfRequest
import com.example.livoappofbooks.data.remote.shelves.dto.ShelfResponse

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
        description: String?
    ): ShelfResponse {
        val body = ShelfRequest(
            name = name,
            description = description,
            books = emptyList()
        )
        return service.createShelf(body)
    }

    suspend fun deleteShelf(id: String) {
        service.deleteShelf(id)
    }
}
