package com.example.livoappofbooks.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.livoappofbooks.ui.components.Input
import com.example.livoappofbooks.ui.components.PrimaryButton
import com.example.livoappofbooks.ui.theme.AppTypography
import com.example.livoappofbooks.ui.theme.background
import com.example.livoappofbooks.ui.theme.primary
import com.example.livoappofbooks.ui.theme.tertiary
import com.example.livoappofbooks.ui.viewModel.ShelvesViewModel
import com.example.livoappofbooks.ui.icons.Arrow_back_ios_new

@Composable
fun AddShelfScreen(
    viewModel: ShelvesViewModel,
    onBackClick: () -> Unit
) {
    // Estados locais vinculados ao ViewModel para sobreviver à rotação
    var name by remember { mutableStateOf(viewModel.formName) }
    var description by remember { mutableStateOf(viewModel.formDescription) }

    // Estado para o modal de confirmação
    var showSaveDialog by remember { mutableStateOf(false) }

    val loading by viewModel.loading.observeAsState(false)
    val success by viewModel.operationSuccess.observeAsState(false)

    // Sincroniza com ViewModel quando os valores mudam
    LaunchedEffect(name) {
        viewModel.formName = name
    }

    LaunchedEffect(description) {
        viewModel.formDescription = description
    }

    // Observa o sucesso para navegar de volta
    LaunchedEffect(success) {
        if (success) {
            viewModel.resetOperationSuccess()
            onBackClick()
        }
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
            
            Spacer(modifier = Modifier.weight(1f))

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
