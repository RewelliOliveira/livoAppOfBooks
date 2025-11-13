package com.example.livoappofbooks.ui.screens.test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.livoappofbooks.ui.components.dialogs.BookStatusDialog

@Preview
@Composable
fun DialogsTestScreen() {
    var showDialog by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("") }
    val radioOptions = listOf("Lido", "Lendo", "Quero ler", "Abandonei")

    // 🔹 Tela principal
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 56.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Test Dialog Status",
                style = MaterialTheme.typography.titleLarge
            )
            Button(onClick = { showDialog = true }) {
                Text("Abrir Dialog")
            }
            // Mostra o resultado da seleção
            if (selectedOption.isNotEmpty()) {
                Text(
                    text = "Selecionado: $selectedOption",
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }

    // 🔹 Mostra o dialog somente se `showDialog` for verdadeiro
    if (showDialog) {
        BookStatusDialog(
            radioOptions = radioOptions,
            selectedOption = selectedOption,
            onSelectionChange = { option ->
                selectedOption = option
            },
            onDismiss = {
                showDialog = false
            }
        )
    }
}
