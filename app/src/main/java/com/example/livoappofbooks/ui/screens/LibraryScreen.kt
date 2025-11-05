package com.example.livoappofbooks.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.livoappofbooks.R
import com.example.livoappofbooks.ui.components.Book
import com.example.livoappofbooks.ui.components.SearchBar
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.statusBars

data class Livro(
    val status: String,
    val progresso: Int,
    val avaliacao: Int,
    val imageUrl: String
)

@Composable
fun LibraryScreen() {

    var searchQuery by remember { mutableStateOf("") }

    val livrosMock = listOf(
        Livro("Lido", 100, 4, "https://m.media-amazon.com/images/I/81iqZ2HHD-L._AC_UF1000,1000_QL80_.jpg"),
        Livro("Lido", 100, 4, "https://m.media-amazon.com/images/I/71kxa1-0mfL._AC_UF1000,1000_QL80_.jpg"),
        Livro("Lido", 100, 4, "https://m.media-amazon.com/images/I/71jLBXtWJWL._AC_UF1000,1000_QL80_.jpg"),
        Livro("Lido", 100, 4, "https://m.media-amazon.com/images/I/91B2nUwGW+L._AC_UF1000,1000_QL80_.jpg"),
        Livro("Lendo", 30, 0, "https://m.media-amazon.com/images/I/71jLBXtWJWL._AC_UF1000,1000_QL80_.jpg"),
        Livro("Lendo", 50, 0, "https://m.media-amazon.com/images/I/81iqZ2HHD-L._AC_UF1000,1000_QL80_.jpg"),
        Livro("Abandonado", 40, 0, "https://m.media-amazon.com/images/I/71kxa1-0mfL._AC_UF1000,1000_QL80_.jpg"),
        Livro("Lido", 100, 5, "https://m.media-amazon.com/images/I/81iqZ2HHD-L._AC_UF1000,1000_QL80_.jpg")
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(horizontal = 25.dp)
        ) {
            // Header com logo LIVO
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 24.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(id = R.drawable.livo),
                    contentDescription = "LIVO Logo",
                    modifier = Modifier
                        .height(30.dp)
                        .width(100.dp)
                )
            }

            SearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                content = {
                    items(livrosMock) { livro ->
                        Book(
                            status = livro.status,
                            progresso = livro.progresso,
                            avaliacao = livro.avaliacao,
                            imageUrl = livro.imageUrl
                        )
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LibraryPreview() {
    LibraryScreen()
}