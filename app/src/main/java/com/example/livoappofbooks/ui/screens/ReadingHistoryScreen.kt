package com.example.livoappofbooks.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.livoappofbooks.data.model.BookStatus
import com.example.livoappofbooks.data.repository.LibraryRepository
import com.example.livoappofbooks.ui.components.*
import com.example.livoappofbooks.ui.icons.Arrow_back_ios_new
import com.example.livoappofbooks.ui.icons.BookOpen
import com.example.livoappofbooks.ui.icons.Pencil
import com.example.livoappofbooks.ui.theme.*
import com.example.livoappofbooks.ui.viewModel.ReadingHistoryHeaderViewModel
import com.example.livoappofbooks.ui.viewModel.ReadingHistoryHeaderViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadingHistoryScreen(
    bookId: String,
    repository: LibraryRepository,
    onBackClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    // ViewModel do header
    val viewModel: ReadingHistoryHeaderViewModel = viewModel(
        factory = ReadingHistoryHeaderViewModelFactory(bookId, repository)
    )
    val uiState by viewModel.uiState.collectAsState()

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
        bottomBar = {
            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                icon = Pencil,
                text = "Registrar Leitura",
                onClick = onRegisterClick,
                style = AppTypography.titleSmall,
                height = 48.dp
            )
        },
        containerColor = background
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
                .background(background),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            // HEADER DINÂMICO
            if (uiState.isLoading) {
                ProgressBarSimple(progress = 0.5f) // indicador simples enquanto carrega
            } else if (uiState.error != null) {
                Text(
                    text = uiState.error ?: "Erro desconhecido",
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 14.sp
                )
            } else {
                val book = uiState.book
                val totalPages = book?.pageCount ?: 0
                val progressCurrent = book?.userReadProgress ?: 0
                val progressPercent = if (totalPages > 0) progressCurrent.toFloat() / totalPages else 0f
                val statusText = book?.status?.displayName ?: "Não iniciado"
                val statusColor = book?.status?.color ?: BackgroundLight

                Row(verticalAlignment = Alignment.CenterVertically) {

                    AsyncImage(
                        model = book?.thumbnail,
                        contentDescription = "Capa do livro",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(85.dp, 120.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(BackgroundLight)
                    )

                    Spacer(Modifier.width(16.dp))

                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

                        Text(
                            text = book?.title ?: "Título não disponível",
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
                                text = book?.authors?.joinToString(", ") ?: "Autor não disponível",
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
                                text = statusText.uppercase(),
                                fontSize = 12.sp,
                                color = BackgroundLight,
                                modifier = Modifier
                                    .background(statusColor, RoundedCornerShape(30.dp))
                                    .padding(horizontal = 16.dp, vertical = 4.dp)
                            )

                            StarRating(
                                rating = uiState.userRating.toDouble(),
                                maxStars = 5,
                                starSize = 18
                            )
                        }
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    ProgressBarSimple(progress = progressPercent)
                }
            }

            // MANTÉM MOCKS ABAIXO
            HorizontalDivider(color = tertiary)

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    ReadingHistoryItem(
                        title = "Leitura 1",
                        date = "10/01/2026",
                        pages = "78/320",
                        review = "Comentário de exemplo sobre essa leitura.",
                        time = "14:25"
                    )
                }

                item {
                    ReadingHistoryItem(
                        title = "Leitura 2",
                        date = "11/01/2026",
                        pages = "120/320",
                        review = null,
                        time = "16:10"
                    )
                }

                item {
                    ReadingHistoryItem(
                        title = null,
                        date = "12/01/2026",
                        pages = "200/320",
                        review = "Leitura feita durante a viagem.",
                        time = "09:42"
                    )
                }

                item {
                    ReadingHistoryItem(
                        title = null,
                        date = "13/01/2026",
                        pages = "250/320",
                        review = null,
                        time = "21:05"
                    )
                }

                item {
                    ReadingHistoryItem(
                        title = "Leitura com um título extremamente longo para testar quebra de linha no layout do componente",
                        date = "14/01/2026",
                        pages = "320/320",
                        review = "Finalização do livro.",
                        time = "23:59"
                    )
                }
            }
        }
    }
}
