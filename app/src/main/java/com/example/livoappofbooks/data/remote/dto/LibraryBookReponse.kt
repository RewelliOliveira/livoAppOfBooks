// data/model/UserBookResponse.kt
package com.example.livoappofbooks.data.model

import com.example.livoappofbooks.domain.model.BookStatus
import com.google.gson.annotations.SerializedName

data class LibraryBookReponse(
    @SerializedName("id")
    val userBookId: Long, // ID da relação usuário-livro

    @SerializedName("bookId")
    val googleBookId: String, // Mesmo que o id do Book

    @SerializedName("bookStatus")
    val bookStatus: String, // "QUERO_LER", "LENDO", etc

    @SerializedName("thumbnail")
    val thumbnail: String?,

    @SerializedName("title")
    val title: String,

    @SerializedName("readingProgress")
    val readingProgress: Int,

    @SerializedName("personalRatting")
    val personalRating: Int?
) {
    fun toBookStatus(): BookStatus {
        return BookStatus.fromString(bookStatus)
    }
}