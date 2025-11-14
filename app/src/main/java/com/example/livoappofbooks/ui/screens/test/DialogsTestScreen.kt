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
import com.example.livoappofbooks.ui.components.sheets.BookStatusBottomSheet
import com.example.livoappofbooks.ui.components.sheets.ShelfCheckboxBottomSheet

@Preview
@Composable
fun DialogsTestScreen() {
    var showStatusModal by remember { mutableStateOf(false) }
    var showShelfsModal by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("") }
    val radioOptions = listOf("Lido", "Lendo", "Quero ler", "Abandonei")
    val checkboxOptions = listOf<String>("Romance", "Religião", "Filosofia", "Ficção")
    var selectedOptions by remember { mutableStateOf(listOf<String>()) }

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
                text = "Test Modal Status",
                style = MaterialTheme.typography.titleLarge
            )
            Button(onClick = { showStatusModal = true }) {
                Text("Abrir Status Modal")
            }
            // Mostra o resultado da seleção
            if (selectedOption.isNotEmpty()) {
                Text(
                    text = "Selecionado: $selectedOption",
                    style = MaterialTheme.typography.bodyLarge,
                )
            }

            Text(
                text = "Test Modal Shelfs",
                style = MaterialTheme.typography.titleLarge
            )
            Button(onClick = { showShelfsModal = true }) {
                Text("Abrir Shelfs Modal")
            }
            // Mostra o resultado da seleção
            selectedOptions.forEach { option ->
                Text(
                    text = "Selecionado: $option",
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }

    if (showStatusModal) {
        BookStatusBottomSheet(
            options = radioOptions,
            selectedOption = selectedOption,
            onSelectionChange = { option ->
                selectedOption = option
            },
            onDismiss = {
                showStatusModal = false
            }
        )
    }

    if(showShelfsModal){
        ShelfCheckboxBottomSheet(
            options = checkboxOptions,
            selectedOptions = selectedOptions,
            onSelectionChange = {selectedOptions = it},
            onDismiss = {
                showShelfsModal = false
            }
        )
    }
}
