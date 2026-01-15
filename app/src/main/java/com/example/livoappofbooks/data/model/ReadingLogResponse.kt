package com.example.livoappofbooks.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReadingLogResponse(
    val id: Int,
    val libraryBookId: Int,
    val title: String?,
    val text: String?,
    val time: String,
    val pagesRead: Int,
    val percentageRead: Int
)