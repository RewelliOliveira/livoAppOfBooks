package com.example.livoappofbooks.data.model

import com.example.livoappofbooks.domain.model.BookStatus
import com.squareup.moshi.Json

data class Book(
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "thumbnail") val thumbnail: String? = null,
    @Json(name = "authors") val authors: List<String> = emptyList(),
    @Json(name = "publisher") val publisher: String? = null,
    @Json(name = "publishedDate") val publishedDate: String? = null,
    @Json(name = "pageCount") val pageCount: Int? = null,
    @Json(name = "averageRating") val averageRating: Double? = null,
    @Json(name = "ratingsCount") val ratingsCount: Int? = null,
    @Json(name = "language") val language: String? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "personalLibrary") val personalLibrary: Boolean = false,
    @Json(name = "libraryRegistration") val libraryRegistration: LibraryRegistration? = null
) {
    val status: BookStatus?
        get() = libraryRegistration?.status?.let { BookStatus.fromString(it) }

    val shelf: String
        get() = libraryRegistration?.shelf ?: "Geral"

    val userReadProgress: Int
        get() = libraryRegistration?.readingProgress ?: 0
}

data class LibraryRegistration(
    @Json(name = "id") val id: String?,
    @Json(name = "status") val status: String?,
    @Json(name = "shelf") val shelf: String?,
    @Json(name = "readingProgress") val readingProgress: Int?,
    @Json(name = "personalRating") val personalRating: Int?
)

fun LibraryBookReponse.toDomainBook(): Book {
    val libReg = LibraryRegistration(
        id = id.toString(),
        status = bookStatus,
        shelf = null,
        readingProgress = readingProgress,
        personalRating = personalRatting
    )

    return Book(
        id = bookId,
        title = title,
        thumbnail = thumbnail,
        personalLibrary = true,
        libraryRegistration = libReg,
        authors = emptyList(),
        publisher = null,
        publishedDate = null,
        pageCount = null,
        description = null
    )
}