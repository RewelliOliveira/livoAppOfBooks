package com.example.livoappofbooks.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.livoappofbooks.data.repository.LibraryRepository
import com.example.livoappofbooks.ui.components.*
import com.example.livoappofbooks.ui.icons.Arrow_back_ios_new
import com.example.livoappofbooks.ui.icons.BookOpen
import com.example.livoappofbooks.ui.icons.Pencil
import com.example.livoappofbooks.ui.theme.*
import com.example.livoappofbooks.ui.viewModel.ReadingHistoryViewModel
import com.example.livoappofbooks.ui.viewModel.ReadingHistoryViewModelFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun formatDateTime(isoString: String): Pair<String, String> {
    return try {
        val parsedDate = LocalDateTime.parse(isoString)
        val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        Pair(parsedDate.format(dateFormatter), parsedDate.format(timeFormatter))
    } catch (e: Exception) {
        Pair(isoString, "")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadingHistoryScreen(
    bookId: String,
    repository: LibraryRepository,
    onBackClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    val viewModel: ReadingHistoryViewModel = viewModel(
        factory = ReadingHistoryViewModelFactory(bookId, repository)
    )
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(bookId) {
        viewModel.loadData()
    }

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

            if (uiState.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    ProgressBarSimple(progress = 0.5f)
                }
            } else {
                val book = uiState.book
                val progressPercent = (book?.libraryRegistration?.readingProgress ?: 0).toFloat()

                val statusText = book?.status?.displayName ?: "..."
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
                            text = book?.title ?: "Título indisponível",
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
                                text = book?.authors?.joinToString(", ") ?: "Autor desconhecido",
                                fontSize = 14.sp,
                                color = onBackground,
                                modifier = Modifier.weight(1f),
                                maxLines = 1
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(BookOpen, null, tint = onBackground)
                                Text(
                                    " ${book?.pageCount ?: 0} pags.",
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

                HorizontalDivider(color = tertiary)

                if (uiState.logs.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Nenhum registro de leitura ainda.", color = Gray)
                    }
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(uiState.logs) { log ->
                            val (dateFormatted, timeFormatted) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                formatDateTime(log.time)
                            } else {
                                Pair(log.time, "")
                            }

                            val pagesDisplay = "${log.pagesRead} págs"

                            ReadingHistoryItem(
                                title = log.title,
                                date = dateFormatted,
                                pages = pagesDisplay,
                                review = log.text,
                                time = timeFormatted
                            )
                        }
                    }
                }
            }
        }
    }
}