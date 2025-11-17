package com.example.livoappofbooks.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.StarHalf
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.livoappofbooks.ui.theme.PrincipalColor
import kotlin.math.floor

@Composable
fun StarRating(
    rating: Double,
    maxStars: Int = 5,
    starSize: Int = 24,
    starColor: Color = PrincipalColor
) {
    Row {
        val fullStars = floor(rating).toInt()
        val hasHalfStar = rating - fullStars >= 0.5

        for (i in 1..maxStars) {
            val icon = when {
                i <= fullStars -> Icons.Rounded.Star
                i == fullStars + 1 && hasHalfStar -> Icons.AutoMirrored.Rounded.StarHalf
                else -> Icons.Outlined.StarOutline
            }

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = starColor,
                modifier = Modifier.size(starSize.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StarRatingPreview() {
    Row(Modifier.padding(16.dp)) {
        StarRating(rating = 3.7)
    }
}

@Preview(showBackground = true)
@Composable
fun StarRatingEmptyPreview() {
    Row(Modifier.padding(16.dp)) {
        StarRating(rating = 2.1)
    }
}
