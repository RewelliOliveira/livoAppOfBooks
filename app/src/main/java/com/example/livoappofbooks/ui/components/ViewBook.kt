package com.example.livoappofbooks.ui.components

import RatingButton
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.livoappofbooks.R
import com.example.livoappofbooks.domain.model.BookStatus
import com.example.livoappofbooks.ui.icons.*
import com.example.livoappofbooks.ui.theme.*

@Composable
fun ViewBook(
    title: String,
    author: String,
    rate: Double,
    sinopse: String,
    imageUrl: String,
    publishYear: String,
    publisher: String,
    pageCount: String,
    status: String,
    shelf: String,
    userCurrentPage: Int? = null,
    userTotalPages: Int = 0,
    onBackClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onStatusClick: () -> Unit,
    onShelfClick: () -> Unit,
    onRemoveClick: () -> Unit = {},
    onRatingClick: () -> Unit
) {
    val isExpanded = remember { mutableStateOf(false) }
    val previewLimit = 150
    val shouldTruncate = sinopse.length > previewLimit

    val displayedSinopse = if (isExpanded.value || !shouldTruncate) sinopse
    else sinopse.take(previewLimit) + "..."

    val safeImageModel = imageUrl.ifBlank { null }

    val currentStatus = BookStatus.fromString(status)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                AsyncImage(
                    model = safeImageModel,
                    placeholder = painterResource(id = R.drawable.capa_default),
                    error = painterResource(id = R.drawable.capa_default),
                    fallback = painterResource(id = R.drawable.capa_default),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp)
                        .blur(2.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, background),
                                startY = 10f
                            )
                        )
                )

                AsyncImage(
                    model = safeImageModel,
                    placeholder = painterResource(id = R.drawable.capa_default),
                    error = painterResource(id = R.drawable.capa_default),
                    fallback = painterResource(id = R.drawable.capa_default),
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(260.dp)
                        .width(170.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .shadow(16.dp, RoundedCornerShape(8.dp))
                        .align(Alignment.BottomCenter)
                )
            }

            Column(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.Bottom) {
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = title,
                            style = AppTypography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            color = onBackground
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = author,
                            style = AppTypography.bodyMedium,
                            color = tertiary
                        )
                    }
                    StarRating(rating = rate)
                }

                Spacer(Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    InfoItem(icon = CalendarDays, text = publishYear, 22.dp)
                    InfoItem(icon = BuildingLibrary, text = publisher.take(12), 22.dp)
                    InfoItem(icon = BookOpen, text = "$pageCount págs", 22.dp)
                }

                Spacer(Modifier.height(16.dp))
                HorizontalDivider(color = tertiary)
                Spacer(Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    PrimaryButton(
                        modifier = Modifier.weight(2f),
                        text = shelf.take(15),
                        onClick = onShelfClick,
                        icon = Bookshelf,
                        style = AppTypography.titleSmall
                    )
                    Spacer(Modifier.width(8.dp))

                    Status(
                        modifier = Modifier.weight(1f),
                        status = BookStatus.fromString(status),
                        onClick = onStatusClick
                    )
                }

                Spacer(Modifier.height(16.dp))

                if (currentStatus != BookStatus.QUERO_LER) {
                    Column {
                        PrimaryButton(
                            modifier = Modifier.fillMaxWidth(),
                            icon = Pencil,
                            text = "Registrar Leitura",
                            onClick = onRegisterClick,
                            style = AppTypography.titleSmall
                        )

                        if(currentStatus == BookStatus.LIDO)
                        RatingButton(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = onRatingClick
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Sinopse",
                            style = AppTypography.titleMedium.copy(
                                color = onBackground,
                                fontWeight = FontWeight.Bold
                            )
                        )

                        Text(
                            text = displayedSinopse,
                            style = AppTypography.bodyMedium.copy(color = onBackground),
                            textAlign = TextAlign.Justify,
                            modifier = Modifier.padding(top = 8.dp)
                        )

                        if (shouldTruncate) {
                            Text(
                                text = if (isExpanded.value) "Ver menos" else "Ver mais",
                                style = AppTypography.bodyMedium.copy(
                                    color = primary,
                                    fontWeight = FontWeight.SemiBold
                                ),
                                modifier = Modifier
                                    .padding(top = 4.dp)
                                    .clickable { isExpanded.value = !isExpanded.value }
                            )
                        }
                    }
                    Spacer(Modifier.height(32.dp))

                    Text(
                        text = "Remover livro",
                        style = AppTypography.titleMedium.copy(
                            color = error,
                            fontWeight = FontWeight.SemiBold,
                            textDecoration = TextDecoration.Underline
                        ),
                        modifier = Modifier
                            .padding(bottom = 60.dp)
                            .clickable(onClick = onRemoveClick)
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 24.dp, top = 42.dp)
                .size(40.dp)
                .background(Color.White.copy(alpha = 0.5f), CircleShape)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { onBackClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = Arrow_back_ios_new,
                contentDescription = "Voltar",
                tint = Color.Black.copy(alpha = 0.8f),
                modifier = Modifier.size(24.dp))
        }

        if (userCurrentPage != null && userCurrentPage >= 0) {
            ProgressBarBook(
                currentPage = userCurrentPage,
                totalPages = if (userTotalPages > 0) userTotalPages else 1,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            )
        }
    }
}