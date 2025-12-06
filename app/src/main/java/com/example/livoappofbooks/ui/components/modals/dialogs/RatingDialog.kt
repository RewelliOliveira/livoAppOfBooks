package com.example.livoappofbooks.ui.components.modals.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.livoappofbooks.ui.components.StarRating
import com.example.livoappofbooks.ui.components.modals.ModalHeader
import com.example.livoappofbooks.ui.theme.*

@Composable
fun RatingDialog(
    rating: Double,
    onDismiss: () -> Unit,
    onRatingChange: (Double) -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = background,
            tonalElevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                ModalHeader(
                    title = "Avaliação",
                    subTitle = "Dê estrelas para avaliar sua leitura",
                    onDismiss = { onDismiss() }
                )

                StarRating(
                    rating = rating,
                    starSize = 56,
                    starColor = onBackground,
                    onRatingChange = onRatingChange
                )
            }
        }
    }
}

@Preview
@Composable
fun RatingDialogPreview() {
    var rating by remember { mutableStateOf(0.0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(surface)
    ) {
        RatingDialog(
            rating = rating,
            onRatingChange = { newValue ->
                rating = if (rating == newValue - 0.5)
                    newValue
                else
                    newValue - 0.5
            },
            onDismiss = {}
        )
    }
}