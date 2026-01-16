package com.example.livoappofbooks.data.remote.shelves.dto

data class BookShelfRequest(
    val id: Long,
    val bookId: String,
    val status: String
)
