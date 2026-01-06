package com.example.livoappofbooks.data.model

import com.example.livoappofbooks.domain.model.BookStatus

data class LibraryBookReponse(

    val id: Long,

    val bookId: String,

    val bookStatus: String,

    val thumbnail: String?,

    val title: String,

    val readingProgress: Int,

    val personalRatting: Int?
) {
    fun toBookStatus(): BookStatus {
        return BookStatus.fromString(bookStatus)
    }
}