package com.example.livoappofbooks.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.livoappofbooks.data.model.BookStatus
import com.example.livoappofbooks.data.remote.shelves.ShelvesRepository
import com.example.livoappofbooks.data.repository.LibraryRepository
import com.example.livoappofbooks.ui.components.ViewBook
import com.example.livoappofbooks.ui.components.modals.dialogs.ConfirmRemoveBookDialog
import com.example.livoappofbooks.ui.components.modals.dialogs.RatingDialog
import com.example.livoappofbooks.ui.components.modals.sheets.AddToShelfBottomSheet
import com.example.livoappofbooks.ui.components.modals.sheets.BookStatusBottomSheet
import com.example.livoappofbooks.ui.viewModel.ViewBookViewModel
import com.example.livoappofbooks.utils.parseHtmlToText

@Composable
fun ViewBookScreen(
    bookId: String,
    repository: LibraryRepository,
    shelvesRepository: ShelvesRepository? = null,
    onBackClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onReadingHistoryClick: () -> Unit
)
 {
    val viewModel = remember { ViewBookViewModel(repository, shelvesRepository) }
    val book by viewModel.book.collectAsState()
    val userRating by viewModel.userRating.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val shelves by viewModel.shelves.collectAsState()
    val shelfAddedEvent by viewModel.shelfAddedEvent.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    // Estados para controlar a visibilidade dos modais (bottom sheets e dialogs)
    var showStatusSheet by remember { mutableStateOf(false) }
    var showRemoveDialog by remember { mutableStateOf(false) }
    var showRatingDialog by remember { mutableStateOf(false) }
    var showShelfSheet by remember { mutableStateOf(false) }

    // Carrega os dados do livro e as prateleiras ao abrir a tela
    LaunchedEffect(bookId) {
        viewModel.fetchBook(bookId)
        viewModel.fetchUserRating(bookId)
        viewModel.fetchUserShelves()
    }

    // Mostra mensagem de sucesso quando o livro é adicionado à prateleira
    LaunchedEffect(shelfAddedEvent) {
        shelfAddedEvent?.let { shelfName ->
            snackbarHostState.showSnackbar("Livro adicionado à prateleira \"$shelfName\"!")
            viewModel.resetShelfAddedEvent()
        }
    }

    LaunchedEffect(error) {
        error?.let {
            val message = when (it) {
                "NOT_READ" -> "Você só pode avaliar livros que já terminou de ler!"
                else -> it
            }
            snackbarHostState.showSnackbar(message)
            viewModel.resetError()
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        containerColor = Color.Transparent,
        contentWindowInsets = WindowInsets(0.dp)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding()),
            contentAlignment = Alignment.Center
        ) {
            when {
                isLoading && book == null -> CircularProgressIndicator()

                book != null -> {
                    val currentBook = book!!
                    val libReg = currentBook.libraryRegistration
                    val currentStatusEnum = BookStatus.fromId(libReg?.status)
                    val secureImageUrl = currentBook.thumbnail?.replace("http:", "https:") ?: ""
                    val authorText = currentBook.authors.joinToString(", ").ifBlank { "Autor Desconhecido" }
                    val cleanDescription = parseHtmlToText(currentBook.description)
                    val shelfText = libReg?.shelf ?: "Prateleiras"
                    val userCurrentPage = if (currentBook.personalLibrary) libReg?.readingProgress else null
                    val pageCountString = currentBook.pageCount?.toString() ?: "-"
                    val totalPagesInt = currentBook.pageCount ?: 0
                    val displayRating = if (userRating > 0) userRating.toDouble() else (currentBook.averageRating ?: 0.0)

                    ViewBook(
                        title = currentBook.title,
                        author = authorText,
                        rate = displayRating,
                        sinopse = cleanDescription,
                        imageUrl = secureImageUrl,
                        publishYear = currentBook.publishedDate?.take(4) ?: "Ano N/A",
                        publisher = currentBook.publisher ?: "Edit. N/A",
                        pageCount = pageCountString,
                        status = currentStatusEnum.id,
                        shelf = shelfText,
                        userCurrentPage = userCurrentPage,
                        userTotalPages = totalPagesInt,
                        onBackClick = onBackClick,
                        onRegisterClick = onRegisterClick,
                        onStatusClick = { showStatusSheet = true },
                        onShelfClick = { showShelfSheet = true },
                        onRemoveClick = { showRemoveDialog = true },
                        onRatingClick = { showRatingDialog = true },
                        onReadingHistoryClick = onReadingHistoryClick

                    )

                    if (showStatusSheet) {
                        val optionsList = BookStatus.entries.map { it.displayName }
                        BookStatusBottomSheet(
                            options = optionsList,
                            selectedOption = currentStatusEnum.displayName,
                            onDismiss = { showStatusSheet = false },
                            onSelectionChange = { selectedName ->
                                val newStatusEnum = BookStatus.entries.find { it.displayName == selectedName }
                                    ?: BookStatus.QUERO_LER
                                viewModel.updateBookStatus(currentBook.id, newStatusEnum.id)
                                showStatusSheet = false
                            }
                        )
                    }

                    if (showShelfSheet) {
                        AddToShelfBottomSheet(
                            shelves = shelves,
                            onShelfSelected = { shelfId ->
                                viewModel.addBookToShelf(shelfId)
                                showShelfSheet = false
                            },
                            onDismiss = { showShelfSheet = false }
                        )
                    }

                    if (showRemoveDialog) {
                        ConfirmRemoveBookDialog(
                            onDismiss = { showRemoveDialog = false },
                            onConfirm = {
                                viewModel.removeBook(
                                    onSuccess = {
                                        showRemoveDialog = false
                                        onBackClick()
                                    }
                                )
                            }
                        )
                    }

                    if (showRatingDialog) {
                        RatingDialog(
                            rating = userRating.toDouble(),
                            onDismiss = { showRatingDialog = false },
                            onRatingChange = { newRating ->
                                viewModel.rateBook(currentBook.id, newRating.toInt())
                                showRatingDialog = false
                            }
                        )
                    }
                }
            }
        }
    }
}