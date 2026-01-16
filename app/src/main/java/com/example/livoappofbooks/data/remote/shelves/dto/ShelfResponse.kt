package com.example.livoappofbooks.data.remote.shelves.dto

data class ShelfResponse(
    val id: String,
    val name: String,
    val description: String?,
    val quantity: Int,
    val bookShelfDto: List<BookShelf>
)
