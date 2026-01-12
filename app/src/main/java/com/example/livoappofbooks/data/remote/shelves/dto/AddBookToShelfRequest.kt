package com.example.livoappofbooks.data.remote.shelves.dto

data class AddBookToShelfRequest(
    val id: Long,       // ID do registro na biblioteca (userBook.id)
    val bookId: String, // ID do livro na API do Google
    val status: String  // Status atual do livro (ex: "QUERO_LER")
)
