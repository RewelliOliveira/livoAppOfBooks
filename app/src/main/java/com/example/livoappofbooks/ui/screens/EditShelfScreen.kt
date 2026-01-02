package com.example.livoappofbooks.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.livoappofbooks.ui.components.Input
import com.example.livoappofbooks.ui.components.PrimaryButton
import com.example.livoappofbooks.ui.theme.AppTypography
import com.example.livoappofbooks.ui.theme.background
import com.example.livoappofbooks.ui.theme.primary
import com.example.livoappofbooks.ui.theme.tertiary
import com.example.livoappofbooks.ui.viewModel.ShelvesViewModel
import androidx.compose.foundation.background
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

    var name by remember { mutableStateOf(shelf?.name ?: "") }
    var description by remember { mutableStateOf("") }
    // Assumindo que a lógica de detalhes da prateleira possa buscar isso, mas ShelfResponse pode não ter descrição.
    // ShelfResponse: id, name, quantity, bookShelfDto. Sem descrição?
    // Verificando ShelfResponse.kt novamente. Ele NÃO tem descrição.
    // Se ShelfResponse não tem descrição, não posso pré-preencher.
    // Vou manter a lógica do campo, mas ele começará vazio se não for encontrado.

    LaunchedEffect(shelf) {
        shelf?.let {
            name = it.name
            // description = it.description // Missing in ShelfResponse
        }
    }

    LaunchedEffect(success) {
        if (success) {
            viewModel.resetOperationSuccess()
            onDeleteSuccess()
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
                onClick = {
                    shelf?.id?.let { viewModel.deleteShelf(it) }
                }
            ) {
                Text(
                    text = "Excluir Prateleira",
                    color = Color.Red,
                    style = AppTypography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            if (loading) {
                CircularProgressIndicator(color = primary)
            } else {
                PrimaryButton(
                    text = "Salvar",
                    onClick = {
                        if (name.isNotBlank()) {
                            shelf?.id?.let { id ->
                                viewModel.updateShelf(id, name, description)
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp)
                )
            }
            
            if (error != null) {
                Text(
                    text = error ?: "",
                    color = Color.Red,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }
    }
}
