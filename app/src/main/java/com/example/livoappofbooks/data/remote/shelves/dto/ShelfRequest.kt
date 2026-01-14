package com.example.livoappofbooks.data.remote.shelves.dto

/**
 * Corpo da requisição para criação de uma prateleira.
 *
 * Agora permite o envio de uma lista de livros completos, em vez de apenas ids,
 * para que o backend receba também o status de cada livro.
 */
data class ShelfRequest(
    val name: String,
    val description: String?,
    val books: List<BookShelfRequest>
)
