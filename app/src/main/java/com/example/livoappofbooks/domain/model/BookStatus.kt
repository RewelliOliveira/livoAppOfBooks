package com.example.livoappofbooks.domain.model

import androidx.compose.ui.graphics.Color
import com.example.livoappofbooks.ui.theme.*

enum class BookStatus(val displayName: String, val color: Color) {
    QUERO_LER("Quero ler", Color(0xFFCDB617)),
    LENDO("Lendo", Color(0xFF1A0A78)),
    LIDO("Lido", Color(0xFF003D3A)),
    ABANDONADO("Abandonado", Color.Gray);

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
