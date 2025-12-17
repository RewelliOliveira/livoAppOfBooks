package com.example.livoappofbooks.domain.model

import com.example.livoappofbooks.data.model.LibraryBookReponse

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
    val personalRating: Int? = null
)

fun LibraryBookReponse.toDomainBook(): Book {
    return Book(
        id = this.googleBookId,
        title = this.title,
        thumbnail = this.thumbnail,
        personalLibrary = true,
        userBookId = this.userBookId,
        bookStatus = this.toBookStatus(),
        readingProgress = this.readingProgress,
        personalRating = this.personalRating
    )
}