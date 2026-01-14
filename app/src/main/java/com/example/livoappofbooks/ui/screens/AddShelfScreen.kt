package com.example.livoappofbooks.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.livoappofbooks.data.model.Book
import com.example.livoappofbooks.domain.model.BookStatus
import com.example.livoappofbooks.ui.components.CardBook
import com.example.livoappofbooks.ui.components.Input
import com.example.livoappofbooks.ui.components.PrimaryButton
import com.example.livoappofbooks.ui.theme.AppTypography
import com.example.livoappofbooks.ui.theme.background
import com.example.livoappofbooks.ui.theme.primary
import com.example.livoappofbooks.ui.theme.tertiary
import com.example.livoappofbooks.ui.viewModel.LibraryUiState
import com.example.livoappofbooks.ui.viewModel.LibraryViewModel
import com.example.livoappofbooks.ui.viewModel.ShelvesViewModel
import com.example.livoappofbooks.ui.icons.Arrow_back_ios_new

@Composable
fun AddShelfScreen(
    context: Context,
    shelvesViewModel: ShelvesViewModel,
    onBackClick: () -> Unit
) {
    // Estados locais vinculados ao ViewModel para sobreviver à rotação
    var name by remember { mutableStateOf(shelvesViewModel.formName) }
    var description by remember { mutableStateOf(shelvesViewModel.formDescription) }

    // Estado para o modal de confirmação
    var showSaveDialog by remember { mutableStateOf(false) }

    val loading by shelvesViewModel.loading.observeAsState(false)
    val success by shelvesViewModel.operationSuccess.observeAsState(false)

    // ViewModel da biblioteca para listar os livros do usuário
    val libraryViewModel = remember { LibraryViewModel(context) }
    val books by libraryViewModel.books.collectAsState()
    val libraryState by libraryViewModel.uiState.collectAsState()

    // Sincroniza com ViewModel quando os valores mudam
    LaunchedEffect(name) {
        shelvesViewModel.formName = name
    }

    LaunchedEffect(description) {
        shelvesViewModel.formDescription = description
    }

    // Observa o sucesso para navegar de volta
    LaunchedEffect(success) {
        if (success) {
            shelvesViewModel.resetOperationSuccess()
            onBackClick()
        }
    }

    // Carrega os livros da biblioteca na abertura da tela
    LaunchedEffect(Unit) {
        libraryViewModel.loadBooks()
    }

    // Modal de confirmação para criar
    if (showSaveDialog) {
        AlertDialog(
            onDismissRequest = { showSaveDialog = false },
            title = {
                Text(
                    text = "Criar Prateleira",
                    style = AppTypography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = "Deseja criar a prateleira \"$name\"?",
                    style = AppTypography.bodyMedium
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showSaveDialog = false
                        if (name.isNotBlank()) {
                            viewModel.createShelf(name, description.ifBlank { null })
                        }
                    }
                ) {
                    Text("Criar", color = primary, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showSaveDialog = false }) {
                    Text("Cancelar", color = tertiary)
                }
            },
            containerColor = background
        )
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    viewModel.clearFormState()
                    onBackClick()
                }) {
                    Icon(
                        imageVector = Arrow_back_ios_new,
                        contentDescription = "Voltar",
                        tint = primary
                    )
                }
                Text(
                    text = "Criar Prateleira",
                    style = AppTypography.headlineSmall,
                    color = primary,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        },
        containerColor = background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Input(
                label = "Nome da Prateleira",
                value = name,
                onValueChange = { name = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Input(
                label = "Descrição (opcional)",
                value = description,
                onValueChange = { description = it },
                modifier = Modifier.height(120.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Seus livros",
                style = AppTypography.titleMedium,
                color = primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            // Lista de livros da biblioteca com seleção simples
            when (libraryState) {
                is LibraryUiState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = primary)
                    }
                }

                is LibraryUiState.Error -> {
                    val message = (libraryState as LibraryUiState.Error).message
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = message,
                            style = AppTypography.bodyMedium,
                            color = tertiary
                        )
                    }
                }

                else -> {
                    if (books.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Nenhum livro na sua biblioteca ainda.",
                                style = AppTypography.bodyMedium,
                                color = tertiary
                            )
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(books) { book ->
                                ShelfSelectableBookItem(
                                    book = book,
                                    isSelected = book.libraryRegistration?.id?.toLongOrNull()
                                        ?.let { shelvesViewModel.selectedBooks.containsKey(it) }
                                        ?: false,
                                    onToggle = { registrationId ->
                                        shelvesViewModel.toggleBookSelection(
                                            registrationId = registrationId,
                                            bookId = book.id,
                                            status = book.status
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }

            if (loading) {
                CircularProgressIndicator(color = primary)
                Spacer(modifier = Modifier.height(32.dp))
            } else {
                PrimaryButton(
                    text = "Salvar",
                    onClick = {
                        if (name.isNotBlank()) {
                            showSaveDialog = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp)
                )
            }
        }
    }
}

@Composable
private fun ShelfSelectableBookItem(
    book: Book,
    isSelected: Boolean,
    onToggle: (Long) -> Unit
) {
    // Usa o id do registro da biblioteca como chave para seleção.
    val registrationId = book.libraryRegistration?.id?.toLongOrNull()
        ?: return

    CardBook(
        bookId = book.id,
        title = book.title,
        author = book.authors.firstOrNull() ?: "",
        rate = book.averageRating ?: 0.0,
        publishYear = book.publishedDate ?: "",
        pageCount = book.pageCount ?: 0,
        imageUrl = book.thumbnail.orEmpty(),
        personalLibrary = isSelected
    ) { _ ->
        onToggle(registrationId)
    }
}
