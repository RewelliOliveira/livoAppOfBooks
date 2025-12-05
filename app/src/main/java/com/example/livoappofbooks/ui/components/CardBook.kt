package com.example.livoappofbooks.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.livoappofbooks.R
import com.example.livoappofbooks.ui.icons.BookOpen
import com.example.livoappofbooks.ui.icons.CalendarDays
import com.example.livoappofbooks.ui.icons.CheckCircle
import com.example.livoappofbooks.ui.icons.PlusCircle
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
                spotColor = onBackground.copy(alpha = 0.5f),
                ambientColor = onBackground.copy(alpha = 0.5f)
            )
            .background(color = background, shape = RoundedCornerShape(10.dp))
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = imageUrl,
                placeholder = painterResource(id = R.drawable.livro_teste),
                contentDescription = null,
                modifier = Modifier
                    .height(140.dp)
                    .width(100.dp)
                    .clip(RoundedCornerShape(15.dp))
            )

            Spacer(Modifier.width(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = title,
                    style = AppTypography.titleMedium,
                    color = onBackground
                )

                Spacer(Modifier.height(2.dp))

                Text(
                    text = author,
                    style = AppTypography.bodyMedium,
                    color = tertiary
                )

                Spacer(Modifier.height(10.dp))

                StarRating(rate)

                Spacer(Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column(
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        InfoItem(
                            icon = CalendarDays,
                            text = publishYear,
                            height = 22.dp
                        )
                        InfoItem(
                            icon = BookOpen,
                            text = "$pageCount págs",
                            height = 22.dp
                        )
                    }

                    PrimaryButton(
                        text = if (!personalLibrary) "Já adicionado" else "Adicionar",
                        icon = if (!personalLibrary) CheckCircle else PlusCircle,
                        onClick = {},
                        height = 36.dp,
                        width = 140.dp
                    )
                }
            }
        }
    }
}

