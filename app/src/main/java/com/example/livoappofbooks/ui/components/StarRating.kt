package com.example.livoappofbooks.ui.components

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.livoappofbooks.ui.theme.PrincipalColor
import com.example.livoappofbooks.ui.icons.*
import kotlin.math.floor

@Composable
fun StarRating(
    rating: Double,
    maxStars: Int = 5,
    starSize: Int = 24,
    starColor: Color = MaterialTheme.colorScheme.primary,
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
                            Modifier.clickable() { onRatingChange(i.toDouble()) }
                        else Modifier
                    )
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

@Preview(showBackground = true)
@Composable
fun StarRatingClickable() {
    var rating by remember { mutableStateOf(0.0) }
    Row(Modifier.padding(top = 128.dp)) {
        StarRating(
            starSize = 56,
            rating = rating.toDouble(),
            onRatingChange = { newValue ->
                rating = if (rating == newValue - 0.5)
                    newValue
                else
                    newValue - 0.5
            }
        )
    }
}
