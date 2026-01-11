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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
    ) {
        when {
            isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            error != null -> {
                Text(
                    text = "Erro: $error",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            book != null -> {
                val currentBook = book!!
                val secureImageUrl = currentBook.thumbnail?.replace("http:", "https:") ?: ""
                val authorText =
                    currentBook.authors.joinToString(", ").ifBlank { "Autor desconhecido" }
                val cleanDescription = parseHtmlToText(currentBook.description)
                val pageCountString = currentBook.pageCount?.toString() ?: "-"





                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp)
                        .align(Alignment.TopCenter)
                ) {
                    AsyncImage(
                        model = secureImageUrl,
                        placeholder = painterResource(R.drawable.livro_teste),
                        error = painterResource(R.drawable.livro_teste),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .blur(20.dp)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    listOf(
                                        Color.Black.copy(alpha = 0.3f),
                                        background
                                    )
                                )
                            )
                    )

                    AsyncImage(
                        model = secureImageUrl,
                        placeholder = painterResource(R.drawable.livro_teste),
                        error = painterResource(R.drawable.livro_teste),
                        contentDescription = currentBook.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(260.dp)
                            .width(170.dp)
                            .align(Alignment.BottomCenter)
                            .clip(RoundedCornerShape(12.dp))
                            .shadow(16.dp)
                    )

                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.TopStart)
                            .background(background.copy(alpha = 0.7f), CircleShape)
                    ) {
                        Icon(
                            imageVector = Arrow_back_ios_new,
                            contentDescription = "Voltar",
                            tint = onBackground
                        )
                    }
                }

                /* ================= CONTEÚDO ROLÁVEL ================= */

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 320.dp)
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 24.dp)
                ) {
                    Spacer(Modifier.height(24.dp))

                    Text(
                        text = currentBook.title,
                        style = AppTypography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = onBackground
                    )

                    Text(
                        text = authorText,
                        style = AppTypography.bodyMedium,
                        color = tertiary
                    )

                    Spacer(Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        InfoItem(CalendarDays, currentBook.publishedDate?.take(4) ?: "-")
                        InfoItem(BuildingLibrary, currentBook.publisher ?: "-")
                        InfoItem(BookOpen, "$pageCountString págs")
                    }

                    Spacer(Modifier.height(24.dp))

                    Text(
                        text = "Sinopse",
                        style = AppTypography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = onBackground
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = if (isSynopsisExpanded)
                            cleanDescription
                        else
                            cleanDescription.take(320) + "...",
                        style = AppTypography.bodyMedium,
                        textAlign = TextAlign.Justify,
                        color = onBackground
                    )

                    TextButton(
                        onClick = { isSynopsisExpanded = !isSynopsisExpanded }
                    ) {
                        Text(
                            text = if (isSynopsisExpanded) "Ver menos" else "Ver mais",
                            color = primary
                        )
                    }

                    Spacer(Modifier.height(120.dp))
                }

                /* ================= BOTÃO FIXO ================= */

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                listOf(Color.Transparent, background)
                            )
                        )
                        .padding(16.dp)
                ) {
                    Button(
                        onClick = { showStatusSheet = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(50)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Adicionar à biblioteca")
                    }
                }

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
