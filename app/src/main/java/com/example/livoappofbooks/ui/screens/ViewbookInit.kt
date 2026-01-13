package com.example.livoappofbooks.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.livoappofbooks.R
import com.example.livoappofbooks.data.model.BookStatus
import com.example.livoappofbooks.data.repository.LibraryRepository
import com.example.livoappofbooks.ui.components.InfoItem
import com.example.livoappofbooks.ui.components.StarRating
import com.example.livoappofbooks.ui.components.modals.sheets.BookStatusBottomSheet
import com.example.livoappofbooks.ui.icons.Arrow_back_ios_new
import com.example.livoappofbooks.ui.icons.BookOpen
import com.example.livoappofbooks.ui.icons.BuildingLibrary
import com.example.livoappofbooks.ui.icons.CalendarDays
import com.example.livoappofbooks.ui.theme.*
import com.example.livoappofbooks.ui.viewModel.ViewBookViewModel
import com.example.livoappofbooks.utils.parseHtmlToText

@Composable
fun ViewBookInitScreen(
    bookId: String,
    repository: LibraryRepository,
    onBackClick: () -> Unit,
    onBookAdded: (String) -> Unit
) {
    val viewModel = remember { ViewBookViewModel(repository) }

    val book by viewModel.book.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val bookAdded by viewModel.bookAddedEvent.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    var showStatusSheet by remember { mutableStateOf(false) }
    var isSynopsisExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(bookId) {
        viewModel.fetchBook(bookId)
    }

    LaunchedEffect(bookAdded) {
        if (bookAdded) {
            onBookAdded(bookId)
        }
    }

    LaunchedEffect(error) {
        error?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.resetError()
        }
    }

    // Uso do Scaffold para manter consistência com ViewBookScreen
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = Color.Transparent,
        contentWindowInsets = WindowInsets(0.dp)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(background)
                .padding(bottom = paddingValues.calculateBottomPadding())
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                book != null -> {
                    val currentBook = book!!
                    val secureImageUrl = currentBook.thumbnail?.replace("http:", "https:") ?: ""
                    val authorText = currentBook.authors.joinToString(", ").ifBlank { "Autor desconhecido" }
                    val cleanDescription = parseHtmlToText(currentBook.description)
                    val pageCountString = currentBook.pageCount?.toString() ?: "-"
                    val displayRating = currentBook.averageRating ?: 0.0

                    // === CONTEÚDO ROLÁVEL ===
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        // --- CABEÇALHO DA IMAGEM ---
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(350.dp),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            // Imagem de fundo com Blur
                            AsyncImage(
                                model = secureImageUrl,
                                placeholder = painterResource(R.drawable.livro_teste),
                                error = painterResource(R.drawable.livro_teste),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(320.dp)
                                    .blur(20.dp) // Blur aumentado para match ViewBookScreen
                            )

                            // Gradiente de transição
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(320.dp)
                                    .background(
                                        Brush.verticalGradient(
                                            colors = listOf(Color.Transparent, background),
                                            startY = 150f
                                        )
                                    )
                            )

                            // Capa do Livro em destaque
                            AsyncImage(
                                model = secureImageUrl,
                                placeholder = painterResource(R.drawable.capa_default),
                                error = painterResource(R.drawable.capa_default),
                                contentDescription = currentBook.title,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(260.dp)
                                    .width(170.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .shadow(16.dp, RoundedCornerShape(8.dp))
                                    .align(Alignment.BottomCenter)
                            )
                        }

                        // --- INFORMAÇÕES DO LIVRO ---
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 24.dp, vertical = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(Modifier.height(16.dp))

                            // Título, Autor e Avaliação Média
                            Row(verticalAlignment = Alignment.Bottom) {
                                Column(
                                    modifier = Modifier.weight(1f),
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        text = currentBook.title,
                                        style = AppTypography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                        color = onBackground
                                    )
                                    Spacer(Modifier.height(8.dp))
                                    Text(
                                        text = authorText,
                                        style = AppTypography.bodyMedium,
                                        color = tertiary
                                    )
                                }
                                StarRating(rating = displayRating)
                            }

                            Spacer(Modifier.height(16.dp))


                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                InfoItem(CalendarDays, currentBook.publishedDate?.take(4) ?: "-", 24.dp, AppTypography.bodyMedium)
                                InfoItem(BuildingLibrary, currentBook.publisher?.take(12) ?: "-", 24.dp, AppTypography.bodyMedium)
                                InfoItem(BookOpen, "$pageCountString págs", 24.dp, AppTypography.bodyMedium)
                            }

                            Spacer(Modifier.height(16.dp))
                            HorizontalDivider(color = tertiary)
                            Spacer(Modifier.height(16.dp))

                            // Sinopse
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = "Sinopse",
                                    style = AppTypography.titleMedium.copy(
                                        color = onBackground,
                                        fontWeight = FontWeight.Bold
                                    )
                                )

                                Spacer(Modifier.height(8.dp))

                                Text(
                                    text = if (isSynopsisExpanded)
                                        cleanDescription
                                    else
                                        cleanDescription.take(320) + "...",
                                    style = AppTypography.bodyMedium.copy(color = onBackground),
                                    textAlign = TextAlign.Justify
                                )

                                TextButton(
                                    onClick = { isSynopsisExpanded = !isSynopsisExpanded },
                                    contentPadding = PaddingValues(0.dp)
                                ) {
                                    Text(
                                        text = if (isSynopsisExpanded) "Ver menos" else "Ver mais",
                                        color = primary,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }

                            Spacer(Modifier.height(100.dp))
                        }
                    }

                    // === BOTÃO VOLTAR ===
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(start = 24.dp, top = 42.dp) // Ajuste para status bar
                            .size(40.dp)
                            .background(Color.White.copy(alpha = 0.5f), CircleShape)
                    ) {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                imageVector = Arrow_back_ios_new,
                                contentDescription = "Voltar",
                                tint = Color.Black.copy(alpha = 0.8f),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                    // === BOTÃO FLUTUANTE ===
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, background),
                                    startY = 0f
                                )
                            )
                            .padding(16.dp)
                    ) {
                        Button(
                            onClick = { showStatusSheet = true },
                            colors = ButtonDefaults.buttonColors(containerColor = primary),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .shadow(8.dp, RoundedCornerShape(50)),
                            shape = RoundedCornerShape(50)
                        ) {
                            Icon(Icons.Default.Add, contentDescription = null)
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = "Adicionar à biblioteca",
                                style = AppTypography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                            )
                        }
                    }

                    // === MODAL DE STATUS ===
                    if (showStatusSheet) {
                        val options = BookStatus.entries.map { it.displayName }

                        BookStatusBottomSheet(
                            options = options,
                            selectedOption = BookStatus.QUERO_LER.displayName,
                            onDismiss = { showStatusSheet = false },
                            onSelectionChange = { selected ->
                                val status = BookStatus.entries.find {
                                    it.displayName == selected
                                } ?: BookStatus.QUERO_LER

                                viewModel.updateBookStatus(
                                    currentBook.id,
                                    status.id
                                )
                                showStatusSheet = false
                            }
                        )
                    }
                }
            }
        }
    }
}