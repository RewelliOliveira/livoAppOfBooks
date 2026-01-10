package com.example.livoappofbooks.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import com.example.livoappofbooks.ui.components.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.livoappofbooks.R
import com.example.livoappofbooks.ui.components.CardBook
import com.example.livoappofbooks.ui.theme.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.livoappofbooks.ui.viewModel.SearchViewModel
import com.example.livoappofbooks.ui.viewModel.SearchUiState

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = viewModel(),
    onBookClick: (bookId: String, isInLibrary: Boolean) -> Unit
) {

    val isDarkTheme = runCatching { rememberThemeState().isDarkTheme }
        .getOrElse { isSystemInDarkTheme() }

    val logoRes = if (isDarkTheme) {
        R.drawable.livo
    } else {
        R.drawable.livo
    }
    val query by viewModel.query.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    // Obter livros populares a partir do provedor de mocks
    val popularByCategory = remember { com.example.livoappofbooks.data.mock.PopularBooks.shuffled() }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        color = background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = logoRes),
                    contentDescription = "LIVO Logo",
                    colorFilter = ColorFilter.tint(primary),
                    modifier = Modifier
                        .height(30.dp)
                        .width(100.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            SearchBar(
                query = query,
                onQueryChange = { viewModel.onQueryChange(it) },
                onSearch = { viewModel.search() },
                placeholder = "Buscar livros",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))
            when (uiState) {
                is SearchUiState.Idle -> {
                    // Mostrar livros populares organizados por categoria
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ) {

                        Text(
                            text = "Descubra",
                            style = AppTypography.headlineMedium,
                            color = outline
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Para cada categoria, renderizar um título e um LazyRow de itens
                        popularByCategory.forEach { (category, books) ->
                            Text(
                                text = category,
                                style = AppTypography.titleMedium,
                                color = onBackground,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )

                            LazyRow(modifier = Modifier.fillMaxWidth()) {
                                items(books) { book ->
                                    com.example.livoappofbooks.ui.components.PopularBookItem(book = book, modifier = Modifier.padding(end = 8.dp)) { id: String ->
                                        onBookClick(id, book.personalLibrary)
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))
                        }

                    }
                }

                is SearchUiState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is SearchUiState.Success -> {
                    val results = (uiState as SearchUiState.Success).results

                    if (results.isEmpty()) {
                        Text(
                            text = "Nenhum resultado encontrado.",
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    } else {
                        LazyColumn(modifier = Modifier.fillMaxWidth()) {
                            items(results) { book ->
                                CardBook(
                                    bookId = book.id,
                                    title = book.title,
                                    author = book.authors.firstOrNull() ?: "Desconhecido",
                                    rate = book.averageRating ?: 0.0,
                                    imageUrl = book.thumbnail ?: "",
                                    publishYear = book.publishedDate?.take(4) ?: "--",
                                    pageCount = book.pageCount ?: 0,
                                    personalLibrary = book.personalLibrary,
                                    onClick = { id ->
                                        onBookClick(id, book.personalLibrary)
                                    }
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                            }
                        }
                    }
                }

                is SearchUiState.Error -> {
                    val message = (uiState as SearchUiState.Error).message
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = message, style = AppTypography.bodyMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}