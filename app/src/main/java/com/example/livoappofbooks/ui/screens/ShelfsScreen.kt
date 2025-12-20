package com.example.livoappofbooks.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.livoappofbooks.R
import com.example.livoappofbooks.ui.components.PrimaryButton
import com.example.livoappofbooks.ui.components.SearchBar
import com.example.livoappofbooks.ui.components.ShelfItem
import com.example.livoappofbooks.ui.icons.PlusCircle
import com.example.livoappofbooks.ui.theme.*

data class Prateleira(
    val nome: String,
    val quantidadeLivros: Int,
    val capas: List<String?>
)

@Composable
fun ShelfsScreen(
    onShelfClick: (Prateleira) -> Unit = {},
    onAddShelfClick: () -> Unit = {}
) {
    var search by remember { mutableStateOf("") }

    val capa1 = "http://books.google.com/books/publisher/content?id=OF0NEQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
    val capa2 = "http://books.google.com/books/publisher/content?id=OF0NEQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
    val capa3 = "http://books.google.com/books/publisher/content?id=OF0NEQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
    val capa4 = null

    val prateleirasMock = listOf(
        Prateleira("Fantasia", 2, listOf(capa1, capa2)),
        Prateleira("Tecnologia", 1, listOf(capa1)),
        Prateleira("Favoritos", 8, listOf(capa3, capa4, capa2))
    )

    val prateleirasFiltradas = remember(search) {
        if (search.isBlank()) prateleirasMock
        else prateleirasMock.filter {
            it.nome.contains(search, ignoreCase = true)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .windowInsetsPadding(WindowInsets.statusBars),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.livo),
                contentDescription = "LIVO Logo",
                colorFilter = ColorFilter.tint(primary),
                modifier = Modifier
                    .height(30.dp)
                    .width(100.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        SearchBar(
            query = search,
            onQueryChange = { search = it },
            placeholder = "Pesquisar prateleira",
            onSearch = {},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Prateleiras",
            style = AppTypography.headlineSmall,
            color = primary,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(prateleirasFiltradas) { prateleira ->
                ShelfItem(
                    prateleira = prateleira,
                    onClick = { onShelfClick(prateleira) }
                )
            }
        }

        PrimaryButton(
            text = "Criar prateleira",
            icon = PlusCircle,
            onClick = onAddShelfClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )
    }
}