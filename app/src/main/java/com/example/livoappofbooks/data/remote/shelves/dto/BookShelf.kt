package com.example.livoappofbooks.data.remote.shelves.dto

data class BookShelf(
    val id: String,
    val bookId: Long,
    val status: String,
    val addedAt: String,
    val rating: Float?
)
