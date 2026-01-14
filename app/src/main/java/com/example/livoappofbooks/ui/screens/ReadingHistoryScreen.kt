package com.example.livoappofbooks.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.livoappofbooks.ui.components.ProgressBarSimple
import com.example.livoappofbooks.ui.components.StarRating
import com.example.livoappofbooks.ui.icons.Arrow_back_ios_new
import com.example.livoappofbooks.ui.icons.BookOpen
import com.example.livoappofbooks.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadingHistoryScreen(
    bookId: String,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Histórico de Leitura",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Arrow_back_ios_new,
                            contentDescription = "Voltar"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = background
                )
            )
        },
        containerColor = background
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
                .background(background),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            // ===== HEADER COPIADO DO RegisterReadingScreen =====

            // mocks
            val totalPages = 320
            val progressCurrent = 320
            val progressPercent = 1f

            Row(verticalAlignment = Alignment.CenterVertically) {

                AsyncImage(
                    model = null, // mock
                    contentDescription = "Capa do livro",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(85.dp, 120.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(BackgroundLight)
                )

                Spacer(Modifier.width(16.dp))

                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

                    Text(
                        text = "Título mockado",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = onBackground,
                        maxLines = 2
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Autor mockado",
                            fontSize = 14.sp,
                            color = onBackground,
                            modifier = Modifier.weight(1f),
                            maxLines = 1
                        )

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(BookOpen, null, tint = onBackground)
                            Text(
                                " $totalPages pags.",
                                fontSize = 12.sp,
                                color = onBackground
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "LIDO",
                            fontSize = 12.sp,
                            color = BackgroundLight,
                            modifier = Modifier
                                .background(PrincipalColor, RoundedCornerShape(30.dp))
                                .padding(horizontal = 16.dp, vertical = 4.dp)
                        )

                        StarRating(
                            rating = 4.5,
                            maxStars = 5,
                            starSize = 18
                        )
                    }
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                ProgressBarSimple(progress = progressPercent)
            }

            HorizontalDivider(color = tertiary)

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(5) { index ->
                    ReadingHistoryItem(
                        title = "Leitura ${index + 1}",
                        pages = "${(index + 1) * 20} páginas",
                        date = "10/0${index + 1}/2026",
                        review = "Comentário de exemplo sobre essa leitura."
                    )
                }
            }
        }
    }
}

@Composable
private fun ReadingHistoryItem(
    title: String,
    pages: String,
    date: String,
    review: String
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = BackgroundLight,
        tonalElevation = 1.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = title, fontWeight = FontWeight.SemiBold)
            Text(
                text = "$pages • $date",
                fontSize = 12.sp,
                color = tertiary
            )
            Text(
                text = review,
                fontSize = 14.sp,
                color = onBackground
            )
        }
    }
}
