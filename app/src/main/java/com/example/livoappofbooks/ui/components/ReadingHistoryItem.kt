package com.example.livoappofbooks.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.livoappofbooks.ui.theme.BackgroundLight
import com.example.livoappofbooks.ui.theme.onBackground
import com.example.livoappofbooks.ui.theme.tertiary

@Composable
fun ReadingHistoryItem(
    title: String,
    pages: String,
    date: String,
    review: String
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
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = title, fontWeight = FontWeight.SemiBold)
            Text(
                text = "$pages • $date",
                fontSize = 12.sp,
                color = tertiary
            )
            Text(
                text = review,
                fontSize = 14.sp,
                color = onBackground
            )
        }
    }
}
