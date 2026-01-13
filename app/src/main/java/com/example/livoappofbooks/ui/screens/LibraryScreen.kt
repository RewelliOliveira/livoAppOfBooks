package com.example.livoappofbooks.ui.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.statusBars
import com.example.livoappofbooks.R
import com.example.livoappofbooks.data.model.Book
import com.example.livoappofbooks.domain.model.BookStatus
import com.example.livoappofbooks.ui.components.Book
import com.example.livoappofbooks.ui.components.SearchBar
import com.example.livoappofbooks.ui.components.FilterBar
import com.example.livoappofbooks.ui.theme.*
import com.example.livoappofbooks.ui.viewModel.LibraryUiState
import com.example.livoappofbooks.ui.viewModel.LibraryViewModel
import androidx.compose.foundation.isSystemInDarkTheme

@Composable
fun LibraryScreen(
    context: Context,
    onBookClick: (Book) -> Unit
) {
    val viewModel = remember { LibraryViewModel(context) }

    val books by viewModel.books.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("Todos") }

    LaunchedEffect(Unit) {
        viewModel.loadBooks()
    }

    val filteredBooks = books
        .filter {
            it.title.contains(searchQuery, ignoreCase = true)
        }
        .filter {
            when (selectedFilter) {
                "Todos" -> true
                "Lendo" -> it.status == BookStatus.LENDO
                "Lido" -> it.status == BookStatus.LIDO
                "Quero Ler" -> it.status == BookStatus.QUERO_LER
                "Abandonado" -> it.status == BookStatus.ABANDONADO
                else -> true
            }
        }

        .sortedWith(
            compareBy(
                { book ->
                    when (book.status) {
                        BookStatus.LENDO -> 1
                        BookStatus.LIDO -> 2
                        BookStatus.QUERO_LER -> 3
                        BookStatus.ABANDONADO -> 4
                        else -> 5
                    }
                },
            )
        )



    val isDarkTheme = isSystemInDarkTheme()
    val isDarkThemeVal = runCatching { rememberThemeState().isDarkTheme }
        .getOrElse { isDarkTheme }
    val logoRes = if (isDarkThemeVal) R.drawable.livo_white else R.drawable.livo

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        color = background
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.statusBars)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(logoRes),
                        contentDescription = "LIVO Logo",
                        modifier = Modifier
                            .height(30.dp)
                            .width(100.dp)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                SearchBar(
                    query = searchQuery,
                    placeholder = "Pesquisar na minha biblioteca",
                    onQueryChange = { searchQuery = it },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                FilterBar(
                    selectedFilter = selectedFilter,
                    onFilterSelected = { selectedFilter = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // CONTEÚDO
                when (uiState) {
                    is LibraryUiState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Carregando biblioteca...")
                        }
                    }

                    is LibraryUiState.Error -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = (uiState as LibraryUiState.Error).message,
                                color = tertiary,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    else -> {
                        if (filteredBooks.isEmpty()) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Nenhum livro encontrado",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = tertiary.copy(alpha = 0.6f),
                                    textAlign = TextAlign.Center
                                )
                            }
                        } else {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(3),
                                modifier = Modifier.fillMaxSize(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                items(filteredBooks) { book ->
                                    Book(
                                        status = book.status ?: BookStatus.QUERO_LER,
                                        progress = book.userReadProgress,
                                        evaluate = book.libraryRegistration?.personalRating ?: 0,
                                        imageUrl = book.thumbnail.orEmpty(),
                                        onClick = { onBookClick(book) }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}