package com.example.livoappofbooks.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.livoappofbooks.ui.icons.*
import kotlin.math.floor
import com.example.livoappofbooks.ui.theme.*

@Composable
fun StarRating(
    rating: Double,
    maxStars: Int = 5,
    starSize: Int = 24,
    starColor: Color = outline,
    onRatingChange: ((Double) -> Unit)? = null
) {
    Row {
        val fullStars = floor(rating).toInt()
        val hasHalfStar = rating - fullStars >= 0.5

        for (i in 1..maxStars) {
            val icon = when {
                i <= fullStars -> StarFull
                i == fullStars + 1 && hasHalfStar -> StarHalf
                else -> Star
            }

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = starColor,
                modifier = Modifier.size(starSize.dp)
                    .then(
                        if (onRatingChange != null)
                            Modifier.clickable{ onRatingChange(i.toDouble()) }
                        else Modifier
                    )
            )
        }
    }
}
