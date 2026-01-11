package com.example.livoappofbooks.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.livoappofbooks.data.repository.LibraryRepository
import com.example.livoappofbooks.ui.components.ViewBook
import com.example.livoappofbooks.ui.components.modals.sheets.BookStatusBottomSheet
import com.example.livoappofbooks.ui.viewModel.ViewBookViewModel
import com.example.livoappofbooks.utils.parseHtmlToText
import com.example.livoappofbooks.data.model.BookStatus

@Composable
fun ViewBookScreen(
    bookId: String,
    repository: LibraryRepository,
    onBackClick: () -> Unit,
    onRegisterClick: () -> Unit = {}
) {
    val viewModel = remember { ViewBookViewModel(repository) }
    val book by viewModel.book.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    var showStatusSheet by remember { mutableStateOf(false) }

    LaunchedEffect(bookId) {
        viewModel.fetchBook(bookId)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            isLoading -> CircularProgressIndicator()

            error != null -> Text(text = "Erro: $error", color = Color.Red)

            book != null -> {
                val currentBook = book!!
                val libReg = currentBook.libraryRegistration
                val currentStatusEnum = BookStatus.fromId(libReg?.status)

                val secureImageUrl = currentBook.thumbnail?.replace("http:", "https:") ?: ""
                val authorText = currentBook.authors.joinToString(", ").ifBlank { "Autor Desconhecido" }
                val cleanDescription = parseHtmlToText(currentBook.description)
                val shelfText = libReg?.shelf ?: "Geral"
                val userCurrentPage = if (currentBook.personalLibrary) libReg?.readingProgress else null
                val pageCountString = currentBook.pageCount?.toString() ?: "-"
                val totalPagesInt = currentBook.pageCount ?: 0

                ViewBook(
                    title = currentBook.title,
                    author = authorText,
                    rate = currentBook.averageRating ?: 0.0,
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
                    onRegisterClick = {
                        showStatusSheet = true
                    }
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
            }
        }
    }
}