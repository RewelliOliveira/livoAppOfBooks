package com.example.livoappofbooks.data.model

data class Book(
    val id: String,
    val title: String,
    val authors: List<String> = emptyList(),
    val publisher: String? = null,
    val publishedDate: String? = null,
    val pageCount: Int? = null,
    val averageRating: Double? = null,
    val ratingsCount: Int? = null,
    val thumbnail: String? = null,
    val language: String? = null,
    val description: String? = null,
    val personalLibrary: Boolean = false
)

