package com.example.livoappofbooks.data.repository

import com.example.livoappofbooks.data.model.Book

interface SearchRepository {
    suspend fun searchBooks(query: String, maxResults: Int = 20): List<Book>
}

