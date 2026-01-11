package com.example.livoappofbooks.data.model

import androidx.compose.ui.graphics.Color

enum class BookStatus(val id: String, val displayName: String, val color: Color) {
    LENDO("LENDO", "Lendo", Color(0xFF4CAF50)),
    LIDO("LIDO", "Lido", Color(0xFF2196F3)),
    QUERO_LER("QUERO_LER", "Quero Ler", Color(0xFFFFC107)),
    ABANDONADO("ABANDONADO", "Abandonado", Color(0xFFF44336));

    companion object {
        fun fromId(id: String?): BookStatus {
            return entries.find { it.id.equals(id, ignoreCase = true) } ?: QUERO_LER
        }

    }
}