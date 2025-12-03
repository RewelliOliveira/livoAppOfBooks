package com.example.livoappofbooks.data.repository

import com.example.livoappofbooks.data.model.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

/**
 * Implementação simples usando HttpURLConnection + org.json para evitar novas dependências.
 * Mapeia o JSON de resposta para List<Book>.
 */
class SearchRepositoryImpl : SearchRepository {
    override suspend fun searchBooks(query: String, maxResults: Int): List<Book> = withContext(Dispatchers.IO) {
        val encoded = URLEncoder.encode(query, "UTF-8")
        val apiUrl = "https://www.googleapis.com/books/v1/volumes?q=$encoded&maxResults=$maxResults"
        val url = URL(apiUrl)
        val conn = (url.openConnection() as HttpURLConnection).apply {
            requestMethod = "GET"
            connectTimeout = 10_000
            readTimeout = 10_000
        }

        try {
            val code = conn.responseCode
            if (code != HttpURLConnection.HTTP_OK) {
                throw Exception("HTTP $code")
            }

            val text = conn.inputStream.bufferedReader().use { it.readText() }
            val root = JSONObject(text)
            val items = root.optJSONArray("items") ?: JSONArray()
            val list = mutableListOf<Book>()

            for (i in 0 until items.length()) {
                val item = items.getJSONObject(i)
                val id = item.optString("id", "")
                val volumeInfo = item.optJSONObject("volumeInfo") ?: JSONObject()

                val title = volumeInfo.optString("title", "Sem título")
                val authorsArr = volumeInfo.optJSONArray("authors")
                val authors = mutableListOf<String>()
                if (authorsArr != null) {
                    for (j in 0 until authorsArr.length()) {
                        authors.add(authorsArr.optString(j))
                    }
                }

                val publisher = volumeInfo.optString("publisher", null)
                val publishedDate = volumeInfo.optString("publishedDate", null)
                val pageCount = if (volumeInfo.has("pageCount")) volumeInfo.optInt("pageCount") else null
                val averageRating = if (volumeInfo.has("averageRating")) volumeInfo.optDouble("averageRating") else null
                val ratingsCount = if (volumeInfo.has("ratingsCount")) volumeInfo.optInt("ratingsCount") else null
                val imageLinks = volumeInfo.optJSONObject("imageLinks")
                val thumbnail = imageLinks?.optString("thumbnail", null)
                val language = volumeInfo.optString("language", null)
                val description = volumeInfo.optString("description", null)

                val book = Book(
                    id = id,
                    title = title,
                    authors = if (authors.isNotEmpty()) authors else emptyList(),
                    publisher = publisher,
                    publishedDate = publishedDate,
                    pageCount = pageCount,
                    averageRating = averageRating,
                    ratingsCount = ratingsCount,
                    thumbnail = thumbnail,
                    language = language,
                    description = description,
                    personalLibrary = false
                )
                list.add(book)
            }

            list
        } finally {
            conn.disconnect()
        }
    }
}

