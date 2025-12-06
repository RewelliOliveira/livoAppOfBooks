package com.example.livoappofbooks.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.livoappofbooks.ui.components.HistoryCard
import com.example.livoappofbooks.ui.components.PrimaryButton
import com.example.livoappofbooks.ui.components.ProgressBarSimple
import com.example.livoappofbooks.ui.components.StarRating
import com.example.livoappofbooks.ui.icons.BookOpen
import com.example.livoappofbooks.ui.theme.*
import com.example.livoappofbooks.ui.theme.onBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    onBackClick: () -> Unit,
    onRegisterReadingClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Histórico de Resenhas",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = background
                )
            )
        },

        bottomBar = {
            Surface(
                shadowElevation = 8.dp,
                color = background
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    PrimaryButton(
                        text = "Registrar Leitura",
                        onClick = onRegisterReadingClick,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                AsyncImage(
                    model = "https://covers.openlibrary.org/b/id/15119025-L.jpg",
                    contentDescription = "Capa do livro",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(85.dp, 120.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(Modifier.width(16.dp))

                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

                    Text(
                        "As Estrelas do Amanhã",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = onBackground
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Marina Alves",
                            fontSize = 14.sp,
                            color = tertiary,
                            modifier = Modifier.weight(1f)
                        )

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = BookOpen,
                                contentDescription = "Livro aberto"
                            )
                            Text("367 pags.", fontSize = 12.sp)
                        }
                    }

                    Spacer(Modifier.width(30.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text( //APARENTEMENTE MOCKADO
                            "Lendo",
                            fontSize = 12.sp,
                            color = background,
                            modifier = Modifier
                                .background(
                                    primary,
                                    RoundedCornerShape(30.dp)
                                )
                                .padding(horizontal = 30.dp, vertical = 3.dp)
                        )

                        StarRating(
                            rating = 3.5,
                            maxStars = 5,
                            starSize = 20
                        )
                    }
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                ProgressBarSimple(progress = 0.7f)
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                HistoryCard(
                    title = "Terminei o livro",
                    date = "08/11/2025",
                    time = "14:25",
                    progressLabel = "364/364",
                    body = "absolut book"
                )

                HistoryCard(
                    title = "Aceitei já",
                    date = "07/11/2025",
                    time = "09:10",
                    progressLabel = "125/364",
                    body = "Agora o cabra lá foi coro de besta, podendo ter pegado ela..."
                )

                HistoryCard(
                    title = null,
                    date = "06/11/2025",
                    time = "20:10",
                    progressLabel = "13/364",
                    body = null
                )
            }

            Spacer(Modifier.height(80.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHistoryScreen() {
    HistoryScreen(
        onBackClick = {},
        onRegisterReadingClick = {}
    )
}
