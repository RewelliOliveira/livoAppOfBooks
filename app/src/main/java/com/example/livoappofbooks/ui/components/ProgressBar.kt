package com.example.livoappofbooks.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import com.example.livoappofbooks.ui.theme.*

@Composable
fun ProgressBarBook(
    currentPage: Int, // ATENÇÃO: A API está enviando a PORCENTAGEM (ex: 26) aqui, não as páginas.
    totalPages: Int,
    modifier: Modifier = Modifier
) {
    val safeTotalPages = if (totalPages > 0) totalPages else 1

    val percentage = currentPage.coerceIn(0, 100)

    val progressFactor = percentage / 100f

    val estimatedPagesRead = (progressFactor * safeTotalPages).toInt()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, outline.copy(alpha = 0.9F), RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .background(background, RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .padding(horizontal = 10.dp, vertical = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$percentage% do livro foi lido",
                style = TextStyle(
                    color = outline,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            )
            Text(
                text = "$estimatedPagesRead/$safeTotalPages",
                style = TextStyle(
                    color = outline,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }

        Spacer(Modifier.height(6.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(RoundedCornerShape(50))
                .background(outline.copy(alpha = 0.2f)) // trilho suave
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(progressFactor) // Usa o fator baseado na porcentagem (0.26)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(50))
                    .background(outline) // barra principal
            )
        }
    }
}