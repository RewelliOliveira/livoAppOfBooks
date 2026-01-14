package com.example.livoappofbooks.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.livoappofbooks.ui.icons.BookOpen
import com.example.livoappofbooks.ui.theme.onBackground
import com.example.livoappofbooks.ui.theme.tertiary

@Composable
fun ReadingHistoryItem(
    title: String,
    date: String,
    pages: String,
    review: String,
    time: String
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color.Transparent,
        border = BorderStroke(1.dp, tertiary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    color = onBackground,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = date,
                    fontSize = 12.sp,
                    color = tertiary
                )
            }

            HorizontalDivider(
                color = tertiary,
                thickness = 1.dp
            )

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

            Text(
                text = review,
                fontSize = 14.sp,
                color = onBackground
            )

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
