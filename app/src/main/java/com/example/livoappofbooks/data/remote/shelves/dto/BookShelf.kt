package com.example.livoappofbooks.data.remote.shelves.dto

data class BookShelf(
    val id: String,
    val bookId: Long,
    val googleBookId: String,
    val status: String,
    val thumbnail: String?,
    val title: String,
    val addedAt: String?,
    val rating: Float?
)
