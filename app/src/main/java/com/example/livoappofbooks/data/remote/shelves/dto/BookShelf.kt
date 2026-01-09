package com.example.livoappofbooks.data.remote.shelves.dto

data class BookShelf(
    val id: String,
    val bookId: Long,
    val status: String,
    val addedAt: String? = null,
    val rating: Float? = null,
    // Campos opcionais que podem vir em endpoints específicos (ex: /shelfs/{id})
    val thumbnail: String? = null,
    val title: String? = null,
    val readingProgress: Int = 0,
    val personalRatting: Int? = null
)
