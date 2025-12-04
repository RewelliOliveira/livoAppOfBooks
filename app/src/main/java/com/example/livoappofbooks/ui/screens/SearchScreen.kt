package com.example.livoappofbooks.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import com.example.livoappofbooks.ui.components.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import com.example.livoappofbooks.R
import com.example.livoappofbooks.ui.components.CardBook
import com.example.livoappofbooks.ui.theme.BackgroundLight
import com.example.livoappofbooks.ui.theme.AppTypography
import com.example.livoappofbooks.ui.theme.PrincipalColor
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.livoappofbooks.ui.viewModel.SearchViewModel
import com.example.livoappofbooks.ui.viewModel.SearchUiState

@Composable
fun SearchScreen(onNavigate: () -> Unit, viewModel: SearchViewModel = viewModel()){
    val query by viewModel.query.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    Column (modifier = Modifier
        .background(BackgroundLight)
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.livo),
                contentDescription = "LIVO Logo",
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        SearchBar(
            query = query,
            onQueryChange = { viewModel.onQueryChange(it) },
            onSearch = { viewModel.search() },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32 .dp))


        when (uiState) {
            is SearchUiState.Idle -> {
                Column(modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Livros Populares",
                        style = AppTypography.headlineSmall,
                        color = PrincipalColor
                    )

                    Spacer(modifier = Modifier.height(24 .dp))

                    CardBook(
                        title = "Peter Pan in Wonderland",
                        author = "Samira Sales",
                        rate = 3.7,
                        imageUrl = "https://covers.openlibrary.org/b/id/15119025-L.jpg",
                        publishYear = "2025",
                        pageCount = 240,
                        personalLibrary = true
                    )
                }
            }
            is SearchUiState.Loading -> {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is SearchUiState.Success -> {
                val results = (uiState as SearchUiState.Success).results
                if (results.isEmpty()) {
                    Text(text = "Nenhum resultado encontrado.", modifier = Modifier.padding(top = 16.dp))
                } else {
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        items(results) { book ->
                            CardBook(
                                title = book.title,
                                author = book.authors.firstOrNull() ?: "Desconhecido",
                                rate = book.averageRating ?: 0.0,
                                imageUrl = book.thumbnail ?: "",
                                publishYear = book.publishedDate?.take(4) ?: "--",
                                pageCount = book.pageCount ?: 0,
                                personalLibrary = book.personalLibrary
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }
                }
            }
            is SearchUiState.Error -> {
                val message = (uiState as SearchUiState.Error).message
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = message, style = AppTypography.bodyMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    // Opcional: botões de retry poderiam ser adicionados aqui
                }
            }
        }
    }
}