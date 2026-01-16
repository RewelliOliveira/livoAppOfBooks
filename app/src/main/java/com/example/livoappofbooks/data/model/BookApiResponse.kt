package com.example.livoappofbooks.data.model

data class BookApiResponse(
    val id: String,
    val volumeInfo: VolumeInfo
)

data class AddBookRequest(
    val bookId: String,
    val bookStatus: String
)

data class VolumeInfo(
    val title: String?,
    val authors: List<String>?,
    val publisher: String?,
    val publishedDate: String?,
    val pageCount: Int?,
    val description: String?,
    val imageLinks: ImageLinks?
)

data class ImageLinks(
    val smallThumbnail: String?,
    val thumbnail: String?
)

fun BookApiResponse.toDomainBook(): Book {
    return Book(
        id = this.id,
        title = this.volumeInfo.title ?: "Título desconhecido",
        authors = this.volumeInfo.authors ?: emptyList(),
        publisher = this.volumeInfo.publisher,
        publishedDate = this.volumeInfo.publishedDate,
        pageCount = this.volumeInfo.pageCount,
        thumbnail = this.volumeInfo.imageLinks?.thumbnail?.replace("http://", "https://"),
        description = this.volumeInfo.description
    )
}
