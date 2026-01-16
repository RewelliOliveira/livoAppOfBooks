package com.example.livoappofbooks.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.livoappofbooks.ui.components.Shelf
import com.example.livoappofbooks.R
import com.example.livoappofbooks.ui.components.PrimaryButton
import com.example.livoappofbooks.ui.components.SearchBar
import com.example.livoappofbooks.ui.components.ShelfItem
import com.example.livoappofbooks.ui.icons.PlusCircle
import com.example.livoappofbooks.ui.theme.*
import com.example.livoappofbooks.ui.viewModel.ShelvesViewModel

@Composable
fun ShelvesScreen(
    onShelfClick: (Shelf) -> Unit = {},
    onAddShelfClick: () -> Unit = {},
    viewModel: ShelvesViewModel,
) {


    val shelvesResponse by viewModel.shelves.observeAsState(emptyList())
    val loading by viewModel.loading.observeAsState(false)
    val error by viewModel.error.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.loadShelves()
    }

    var search by remember { mutableStateOf("") }

    val shelves = remember(shelvesResponse) {
        shelvesResponse.map { shelf ->
            Shelf(
                id = shelf.id,
                nome = shelf.name,
                quantidadeLivros = shelf.quantity,

                capas = shelf.bookShelfDto
                    .map { it.thumbnail?.takeIf { url -> url.isNotBlank() } }
                    .take(3)
            )
        }
    }

    val shelvesFiltradas = remember(search, shelves) {
        if (search.isBlank()) shelves
        else shelves.filter {
            it.nome.contains(search, ignoreCase = true)
        }
    }

    val isDarkTheme = runCatching { rememberThemeState().isDarkTheme }
        .getOrElse { isSystemInDarkTheme() }

    val logoRes = if (isDarkTheme) R.drawable.livo_white else R.drawable.livo

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
                painter = painterResource(id = logoRes),
                contentDescription = "LIVO Logo",
                modifier = Modifier
                    .height(30.dp)
                    .width(100.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        SearchBar(
            query = search,
            onQueryChange = { search = it },
            placeholder = "Pesquisar shelf",
            onSearch = {},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Prateleiras",
            style = AppTypography.headlineMedium,
            color = outline,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            when {
                loading -> {
                    CircularProgressIndicator(color = outline)
                }
                error != null -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = error ?: "Erro desconhecido",
                            color = tertiary,
                            style = AppTypography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        TextButton(onClick = { viewModel.loadShelves() }) {
                            Text("Tentar novamente", color = primary)
                        }
                    }
                }
                shelvesFiltradas.isEmpty() -> {
                    Text(
                        text = if (search.isBlank()) "Nenhuma prateleira encontrada" else "Nenhum resultado para \"$search\"",
                        color = tertiary.copy(alpha = 0.6f),
                        style = AppTypography.bodyMedium
                    )
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ) {
                        items(shelvesFiltradas) { shelf ->
                            ShelfItem(
                                prateleira = shelf,
                                onClick = { onShelfClick(shelf) }
                            )
                        }
                    }
                }
            }
        }

        PrimaryButton(
            text = "Criar Prateleira",
            icon = PlusCircle,
            onClick = onAddShelfClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            height = 50.dp,
            style = AppTypography.labelLarge
        )
    }
}