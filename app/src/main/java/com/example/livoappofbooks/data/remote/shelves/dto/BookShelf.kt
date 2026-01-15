package com.example.livoappofbooks.data.remote.shelves.dto

data class BookShelf(
    val id: String,         // UUID
    val bookId: Long,       // ID interno
    val googleBookId: String,
    val status: String,
    val thumbnail: String?,
    val title: String,
    val addedAt: String?,   // LocalDateTime (como String para Moshi ou custom adapter)
    val rating: Float?
)
