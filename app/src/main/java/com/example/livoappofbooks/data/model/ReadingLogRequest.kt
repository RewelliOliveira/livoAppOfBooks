package com.example.livoappofbooks.data.model

data class ReadingLogRequest (
    val libraryBookId: Int,
    val title: String?,
    val text: String?,
    val time: String,
    val pagesRead: Int,
)