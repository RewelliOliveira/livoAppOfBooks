package com.example.livoappofbooks.ui.components

import androidx.compose.foundation.background
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
fun ProgressBarSimple(
    progress: Float,
    modifier: Modifier = Modifier
) {

    val progressInt = progress.toInt()
    val progressNormalized = (progress / 100f).coerceIn(0f, 1f)

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .weight(1f)
                .height(10.dp)
                .clip(RoundedCornerShape(50))
                .background(outline.copy(alpha = 0.2f))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(progressNormalized)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(50))
                    .background(outline)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "$progressInt%",
            style = TextStyle(
                color = outline,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}