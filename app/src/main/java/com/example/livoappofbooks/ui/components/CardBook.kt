package com.example.livoappofbooks.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.livoappofbooks.R
import com.example.livoappofbooks.ui.icons.BookOpen
import com.example.livoappofbooks.ui.icons.CalendarDays
import com.example.livoappofbooks.ui.icons.CheckCircle
import com.example.livoappofbooks.ui.icons.PlusCircle

import kotlin.String
import com.example.livoappofbooks.ui.theme.*


@Composable
fun CardBook(
    title: String,
    author: String,
    rate: Double,
    publishYear: String,
    pageCount: Int,
    imageUrl: String,
    personalLibrary: Boolean
) {
    Box(
        Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 10.dp,
                spotColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                ambientColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
            )
            .height(140.dp)
            .background(color = MaterialTheme.colorScheme.background, shape = RoundedCornerShape(10.dp))
            .padding(10.dp)
    ) {
        Row {
            AsyncImage(
                model = imageUrl,
                placeholder = painterResource(id = R.drawable.livro_teste),
                contentDescription = null,
                modifier = Modifier.fillMaxHeight()
            )

            Spacer(Modifier.width(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = title,
                    style = AppTypography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = author,
                    style = AppTypography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary
                )

                Spacer(Modifier.height(10.dp))

                StarRating(rate)

                Spacer(Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column {
                        InfoItem(
                            icon = CalendarDays,
                            text = publishYear,
                            height = 16.dp
                        )
                        Spacer(Modifier.height(4.dp))
                        InfoItem(
                            icon = BookOpen,
                            text = "$pageCount págs",
                            height = 16.dp
                        )
                    }

                    if (!personalLibrary) {
                        PrimaryButton(
                            text = "Já adicionado",
                            icon = CheckCircle,
                            onClick = {},
                            height = 28.dp,
                            width = 150.dp
                        )
                    } else {
                        PrimaryButton(
                            text = "Adicionar",
                            icon = PlusCircle,
                            onClick = {},
                            height = 28.dp,
                            width = 150.dp
                        )
                    }
                }
            }
        }
    }
}
