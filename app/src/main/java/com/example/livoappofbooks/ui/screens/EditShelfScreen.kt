package com.example.livoappofbooks.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.livoappofbooks.ui.components.Input
import com.example.livoappofbooks.ui.components.PrimaryButton
import com.example.livoappofbooks.ui.theme.AppTypography
import com.example.livoappofbooks.ui.theme.background
import com.example.livoappofbooks.ui.theme.primary
import com.example.livoappofbooks.ui.theme.tertiary
import com.example.livoappofbooks.ui.viewModel.ShelvesViewModel
import androidx.compose.ui.graphics.Color
import com.example.livoappofbooks.ui.icons.Arrow_back_ios_new

@Composable
fun EditShelfScreen(
    viewModel: ShelvesViewModel,
    onBackClick: () -> Unit,
    onDeleteSuccess: () -> Unit
) {
    val shelf by viewModel.selectedShelf.observeAsState()
    val loading by viewModel.loading.observeAsState(false)
    val success by viewModel.operationSuccess.observeAsState(false)
    val error by viewModel.error.observeAsState()


    var name by remember { mutableStateOf(viewModel.formName) }
    var description by remember { mutableStateOf(viewModel.formDescription) }
    var initialized by remember { mutableStateOf(false) }


    var showDeleteDialog by remember { mutableStateOf(false) }
    var showSaveDialog by remember { mutableStateOf(false) }


    LaunchedEffect(shelf) {
        if (!initialized && shelf != null) {
            name = shelf?.name ?: ""
            description = shelf?.description ?: ""
            viewModel.formName = name
            viewModel.formDescription = description
            initialized = true
        }
    }


    LaunchedEffect(name) {
        viewModel.formName = name
    }

    LaunchedEffect(description) {
        viewModel.formDescription = description
    }

    LaunchedEffect(success) {
        if (success) {
            viewModel.resetOperationSuccess()
            onDeleteSuccess()
        }
    }


    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = {
                Text(
                    text = "Excluir Prateleira",
                    style = AppTypography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = "Tem certeza que deseja excluir a prateleira \"${shelf?.name}\"? Esta ação não pode ser desfeita.",
                    style = AppTypography.bodyMedium
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                        shelf?.id?.let { viewModel.deleteShelf(it) }
                    }
                ) {
                    Text("Excluir", color = Color.Red, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancelar", color = tertiary)
                }
            },
            containerColor = background
        )
    }


    if (showSaveDialog) {
        AlertDialog(
            onDismissRequest = { showSaveDialog = false },
            title = {
                Text(
                    text = "Salvar Alterações",
                    style = AppTypography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = "Deseja salvar as alterações na prateleira?",
                    style = AppTypography.bodyMedium
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showSaveDialog = false
                        if (name.isNotBlank()) {
                            shelf?.let { currentShelf ->
                                viewModel.updateShelf(currentShelf.id, name, description)
                            }
                        }
                    }
                ) {
                    Text("Salvar", color = primary, fontWeight = FontWeight.Bold)
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
                    text = "Editar Prateleira",
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
                label = "Descrição",
                value = description,
                onValueChange = { description = it },
                modifier = Modifier.height(120.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            TextButton(
                onClick = { showDeleteDialog = true }
            ) {
                Text(
                    text = "Excluir Prateleira",
                    color = Color.Red,
                    style = AppTypography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            if (error != null) {
                Text(
                    text = error ?: "",
                    color = Color.Red,
                    modifier = Modifier.padding(bottom = 16.dp),
                    textAlign = TextAlign.Center
                )
            }

            if (loading) {
                CircularProgressIndicator(color = primary)
                Spacer(modifier = Modifier.height(32.dp))
            } else {
                PrimaryButton(
                    text = "Salvar",
                    onClick = { showSaveDialog = true },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp)
                )
            }
        }
    }
}
