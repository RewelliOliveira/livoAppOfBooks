package com.example.livoappofbooks.data.remote.shelves.dto

data class ShelfRequest(
    val name: String,
    val description: String?,
    val books: List<BookShelfRequest>?
)
