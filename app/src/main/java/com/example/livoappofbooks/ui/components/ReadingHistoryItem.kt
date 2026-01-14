package com.example.livoappofbooks.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.livoappofbooks.ui.icons.BookOpen
import com.example.livoappofbooks.ui.theme.BackgroundLight
import com.example.livoappofbooks.ui.theme.onBackground
import com.example.livoappofbooks.ui.theme.tertiary

@Composable
fun ReadingHistoryItem(
    title: String,
    date: String,
    pages: String, // ex: "78/666"
    review: String,
    time: String // ex: "14:25"
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = BackgroundLight,
        tonalElevation = 1.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // ─── Linha 1: Título + Data ───
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = date,
                    fontSize = 12.sp,
                    color = tertiary
                )
            }

            // ─── Divider ───
            HorizontalDivider(color = tertiary)

            // ─── Linha 2: Ícone + páginas ───
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    imageVector = BookOpen,
                    contentDescription = null,
                    tint = onBackground,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = pages,
                    fontSize = 13.sp,
                    color = onBackground
                )
            }

            // ─── Linha 3: Review ───
            Text(
                text = review,
                fontSize = 14.sp,
                color = onBackground
            )

            // ─── Linha 4: Hora (direita) ───
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = time,
                    fontSize = 12.sp,
                    color = tertiary
                )
            }
        }
    }
}
