package com.example.livoappofbooks.ui.screens

import RatingButton
import com.example.livoappofbooks.ui.icons.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.livoappofbooks.R
import com.example.livoappofbooks.domain.model.BookStatus
import com.example.livoappofbooks.navigation.Screen
import com.example.livoappofbooks.ui.components.StarRating
import com.example.livoappofbooks.ui.components.InfoItem
import com.example.livoappofbooks.ui.components.PrimaryButton
import com.example.livoappofbooks.ui.components.ProgressBarBook
import com.example.livoappofbooks.ui.components.Status
import com.example.livoappofbooks.ui.theme.AlertColor
import com.example.livoappofbooks.ui.theme.*
import com.example.livoappofbooks.ui.theme.PrincipalColor

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
    onBackClick: () -> Unit,
    onRegisterClick: () -> Unit
) {

    val isExpanded = remember { mutableStateOf(false) }
    val previewLimit = 150
    val shouldTruncate = sinopse.length > previewLimit

    val displayedSinopse = if (isExpanded.value || !shouldTruncate) sinopse
    else sinopse.take(previewLimit) + "..."

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
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
                    model = imageUrl,
                    placeholder = painterResource(id = R.drawable.initiallogo_svg),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp)
                        .blur(10.dp)
                        .shadow(20.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, White.copy(alpha = 1f)),
                                startY = 250f
                            )
                        )
                )

                AsyncImage(
                    model = imageUrl,
                    placeholder = painterResource(id = R.drawable.initiallogo_svg),
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
                        modifier = Modifier.weight(1f), horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = title,
                            style = AppTypography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            color = PrincipalColor
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = author, style = AppTypography.bodyMedium, color = Color.DarkGray
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
                    InfoItem(icon = CalendarDays, text = publishYear)
                    InfoItem(icon = BuildingLibrary, text = publisher.take(12))
                    InfoItem(icon = BookOpen, text = "$pageCount págs")
                }

                Spacer(Modifier.height(16.dp))
                Divider(color = Gray)
                Spacer(Modifier.height(8.dp))

                PrimaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    icon = Pencil,
                    text = "Registrar Leitura",
                    onClick = onRegisterClick
                )

                Spacer(Modifier.height(16.dp))

                Column {
                    RatingButton(
                        modifier = Modifier.fillMaxWidth(), onClick = {})
                }

                Spacer(Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Sinopse", style = AppTypography.titleMedium.copy(
                                color = Color.Black, fontWeight = FontWeight.SemiBold
                            )
                        )

                        Text(
                            text = displayedSinopse,
                            style = AppTypography.bodyMedium.copy(color = Color.DarkGray),
                            textAlign = TextAlign.Justify,
                            modifier = Modifier.padding(top = 8.dp)
                        )

                        if (shouldTruncate) {
                            Text(
                                text = if (isExpanded.value) "Ver menos" else "Ver mais",
                                style = AppTypography.bodyMedium.copy(
                                    color = PrincipalColor, fontWeight = FontWeight.SemiBold
                                ),
                                modifier = Modifier
                                    .padding(top = 4.dp)
                                    .clickable { isExpanded.value = !isExpanded.value })
                        }
                    }
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "Remover livro", style = AppTypography.titleMedium.copy(
                            color = AlertColor,
                            fontWeight = FontWeight.SemiBold,
                            textDecoration = TextDecoration.Underline
                        ), modifier = Modifier
                            .padding(bottom = 60.dp)
                            .clickable(onClick = {})
                    )
                }
            }
        }

        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(24.dp, 42.dp)
                .size(16.dp)
                .background(Color.White, CircleShape)
        ) {
            Icon(
                imageVector = Arrow_back_ios_new,
                contentDescription = "Voltar",
                tint = Color.Black,
            )
        }

        ProgressBarBook(
            currentPage = 108,
            totalPages = 240,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        )
    }
}
