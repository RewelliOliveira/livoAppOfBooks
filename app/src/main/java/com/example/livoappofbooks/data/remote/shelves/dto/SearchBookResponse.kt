package com.example.livoappofbooks.data.remote.shelves.dto

data class SearchBookResponse(
    val id: Long,
    val bookId: String,
    val bookStatus: String,
    val thumbnail: String,
    val title: String,
    val readingProgress: Double,
    val personalRatting: Int?
)
