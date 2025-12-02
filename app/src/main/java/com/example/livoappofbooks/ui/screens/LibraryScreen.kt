package com.example.livoappofbooks.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
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
import com.example.livoappofbooks.R
import com.example.livoappofbooks.ui.components.Book
import com.example.livoappofbooks.ui.components.SearchBar
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.statusBars
import com.example.livoappofbooks.ui.components.FilterBar
import com.example.livoappofbooks.ui.theme.rememberThemeState

data class Livro(
    val status: String,
    val progress: Int,
    val evaluate: Int,
    val imageUrl: String
)

@Composable
fun LibraryScreen(
    onNavigate: () -> Unit,
    onBookClick: (Livro) -> Unit
) {
    val themeState = rememberThemeState()
    val isDark = themeState.isDarkTheme

    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("Todos") }

    val livrosMock = listOf(
        Livro(
            "Lido",
            100,
            4,
            "https://m.media-amazon.com/images/I/81iqZ2HHD-L._AC_UF1000,1000_QL80_.jpg"
        ),
        Livro(
            "Lido",
            100,
            4,
            "https://m.media-amazon.com/images/I/71kxa1-0mfL._AC_UF1000,1000_QL80_.jpg"
        ),
        Livro(
            "Lido",
            100,
            4,
            "https://m.media-amazon.com/images/I/71jLBXtWJWL._AC_UF1000,1000_QL80_.jpg"
        ),
        Livro(
            "Lido",
            100,
            5,
            "https://m.media-amazon.com/images/I/81iqZ2HHD-L._AC_UF1000,1000_QL80_.jpg"
        ),
        Livro(
            "Lendo",
            30,
            0,
            "https://m.media-amazon.com/images/I/71jLBXtWJWL._AC_UF1000,1000_QL80_.jpg"
        ),
        Livro(
            "Lendo",
            50,
            0,
            "https://m.media-amazon.com/images/I/81iqZ2HHD-L._AC_UF1000,1000_QL80_.jpg"
        ),
        Livro(
            "Abandonado",
            40,
            0,
            "https://m.media-amazon.com/images/I/71kxa1-0mfL._AC_UF1000,1000_QL80_.jpg"
        )
    )

    val filteredLivros = when (selectedFilter) {
        "Todos" -> livrosMock
        "Lendo" -> livrosMock.filter { it.status == "Lendo" }
        "Lido" -> livrosMock.filter { it.status == "Lido" }
        "Quero Ler" -> livrosMock.filter { it.status == "Quero Ler" }
        "Abandonado" -> livrosMock.filter { it.status == "Abandonado" }
        else -> livrosMock
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.statusBars)
                    .padding(horizontal = 25.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(
                            id = if (isDark) R.drawable.livo_dark else R.drawable.livo
                        ),
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
                        .padding(bottom = 16.dp)
                )

                FilterBar(
                    selectedFilter = selectedFilter,
                    onFilterSelected = { selectedFilter = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                )

                if (filteredLivros.isNotEmpty()) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        content = {
                            items(filteredLivros) { livro ->
                                Book(
                                    status = livro.status,
                                    progress = livro.progress,
                                    evaluate = livro.evaluate,
                                    imageUrl = livro.imageUrl,
                                    onClick = { onBookClick(livro) }
                                )
                            }
                        }
                    )
                }
            }

            if (filteredLivros.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Nenhum livro encontrado",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.6f),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
