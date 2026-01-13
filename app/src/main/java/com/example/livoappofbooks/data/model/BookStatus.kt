package com.example.livoappofbooks.data.model

import androidx.compose.ui.graphics.Color

enum class BookStatus(val id: String, val displayName: String, val color: Color) {
    LENDO("LENDO", "Lendo", Color(0xFF003D3A)),
    LIDO("LIDO", "Lido", Color(0xFF00244D)),
    QUERO_LER("QUERO_LER", "Quero Ler", Color(0xFFF4B61A)),
    ABANDONADO("ABANDONADO", "Abandonado", Color(0xFF353535));

    companion object {
        fun fromId(id: String?): BookStatus {
            return entries.find { it.id.equals(id, ignoreCase = true) } ?: QUERO_LER
        }

    }
}