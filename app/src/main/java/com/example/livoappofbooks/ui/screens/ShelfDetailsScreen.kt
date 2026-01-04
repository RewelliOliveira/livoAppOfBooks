package com.example.livoappofbooks.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import com.example.livoappofbooks.R
import com.example.livoappofbooks.domain.model.BookStatus
import com.example.livoappofbooks.ui.components.Book
import com.example.livoappofbooks.ui.components.FilterBar
import com.example.livoappofbooks.ui.components.SearchBar
import com.example.livoappofbooks.ui.theme.*
import com.example.livoappofbooks.ui.viewModel.ShelvesViewModel

@Composable
fun ShelfDetailsScreen(
    shelfId: String?,
    viewModel: ShelvesViewModel,
    onBackClick: () -> Unit,
    onEditClick: (String) -> Unit,
    onBookClick: (Long) -> Unit
) {
    val shelf by viewModel.selectedShelf.observeAsState()
    val loading by viewModel.loading.observeAsState(false)
    val error by viewModel.error.observeAsState()

    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("Todos") }

    LaunchedEffect(shelfId) {
        if (!shelfId.isNullOrBlank()) {
            viewModel.loadShelfDetails(shelfId)
        }
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = com.example.livoappofbooks.ui.icons.Arrow_back_ios_new,
                        contentDescription = "Voltar",
                        tint = primary
                    )
                }
                Text(
                    text = shelf?.name ?: "Carregando...",
                    style = AppTypography.headlineSmall,
                    color = primary,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    maxLines = 1
                )
                IconButton(onClick = { shelfId?.let { onEditClick(it) } }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_edit),
                        contentDescription = "Editar",
                        tint = primary
                    )
                }
            }
        },
        containerColor = background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

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
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Content
             if (loading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = primary)
                }
            } else if (error != null) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = error ?: "Erro ao carregar", color = tertiary)
                }
            } else {
                 val books = shelf?.bookShelfDto ?: emptyList()
                 val filteredBooks = books
                    .filter { book ->
                        (book.title ?: "").contains(searchQuery, ignoreCase = true)
                    }

                 if (filteredBooks.isEmpty()) {
                     Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                         Text(
                             text = "Nenhum livro nesta prateleira",
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
                         // Mapeando BookShelf para os parâmetros do componente Book
                         items(filteredBooks) { book ->
                             Book(
                                 status = BookStatus.fromString(book.status),
                                 progress = 0, // Placeholder
                                 evaluate = book.rating?.toInt() ?: 0,
                                 imageUrl = book.thumbnail ?: "",
                                 onClick = { onBookClick(book.bookId) }
                             )
                         }
                     }
                 }
            }
        }
    }
}
