package com.example.livoappofbooks.domain.model

import androidx.compose.ui.graphics.Color
import com.example.livoappofbooks.ui.theme.*

enum class BookStatus(val id: String, val displayName: String, val color: Color) {
    LENDO("LENDO", "Lendo", Color(0xFF003D3A)),
    LIDO("LIDO", "Lido", Color(0xFF00244D)),
    QUERO_LER("QUERO_LER", "Quero Ler", Color(0xFFF4B61A)),
    ABANDONADO("ABANDONADO", "Abandonado", Color(0xFF353535));

    companion object {
        fun fromString(status: String?): BookStatus {
            return when (status?.uppercase()) {
                "QUERO_LER" -> QUERO_LER
                "LENDO" -> LENDO
                "LIDO" -> LIDO
                "ABANDONADO" -> ABANDONADO
                else -> QUERO_LER // Um padrão seguro
            }
        }
    }
}
