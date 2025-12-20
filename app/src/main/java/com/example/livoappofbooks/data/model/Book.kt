package com.example.livoappofbooks.data.model

import com.example.livoappofbooks.domain.model.BookStatus

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
    val personalLibrary: Boolean = false,

    val userBookId: Long? = null,
    val bookStatus: BookStatus? = null,
    val readingProgress: Int = 0,
    val personalRatting: Int? = null
)

fun LibraryBookReponse.toDomainBook(): Book = Book(
    id = bookId,
    title = title,
    thumbnail = thumbnail,
    personalLibrary = true,
    userBookId = id,
    bookStatus = toBookStatus(),
    readingProgress = readingProgress,
    personalRatting = personalRatting
)