package com.example.livoappofbooks.data.repository

import com.example.livoappofbooks.domain.model.Book

interface SearchRepository {
    suspend fun searchBooks(query: String, maxResults: Int = 20): List<Book>
}

