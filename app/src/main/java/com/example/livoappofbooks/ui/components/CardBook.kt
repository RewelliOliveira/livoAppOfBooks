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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
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
                elevation = 4.dp,
                spotColor = Color(0x40000000),
                ambientColor = Color(0x40000000)
            )
            .height(140.dp)
            .background(color = BackgroundLight, shape = RoundedCornerShape(10.dp))
            .padding(10.dp)
    ) {
        Row {
            AsyncImage(
                model = "https://covers.openlibrary.org/b/id/15119025-L.jpg",
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
                    color = Black
                )

                Text(
                    text = author,
                    style = AppTypography.bodyMedium,
                    color = SubtitlesColor
                )

                Spacer(Modifier.height(10.dp))

                StarRating(3.7)

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
                            useDarkColor = true,
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

@Preview(showBackground = true)
@Composable
fun CardBookPreview() {
    CardBook(
        title = "Peter Pan in Wonderland",
        author = "Samira Sales",
        rate = 3.7,
        imageUrl = "https://covers.openlibrary.org/b/id/15119025-L.jpg",
        publishYear = "2025",
        pageCount = 240,
        personalLibrary = false
    )
}

@Preview(showBackground = true)
@Composable
fun CardBookPreview2() {
    CardBook(
        title = "Peter Pan in Wonderland",
        author = "Samira Sales",
        rate = 3.7,
        imageUrl = "https://covers.openlibrary.org/b/id/15119025-L.jpg",
        publishYear = "2025",
        pageCount = 240,
        personalLibrary = true
    )
}
