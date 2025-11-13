package com.example.livoappofbooks.ui.components.sheets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.livoappofbooks.ui.components.DialogTopBar
import com.example.livoappofbooks.ui.components.RadioButtonSingleSelection
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookStatusBottomSheet(
    radioOptions: List<String>,
    selectedOption: String,
    onSelectionChange: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true // Abre sempre totalmente
    )
    val scope = rememberCoroutineScope()
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DialogTopBar(
                title = "Status do livro",
                subTitle = "Escolha o status da leitura do seu livro",
                onDismiss = {
                    scope.launch { sheetState.hide() }
                    onDismiss()
                }
            )

            // Lista de opções
            RadioButtonSingleSelection(
                radioOptions = radioOptions,
                selectedOption = selectedOption,
                onSelectionChange = { option ->
                    onSelectionChange(option)
                    scope.launch {
                        sheetState.hide() // Fecha o bottom sheet
                        onDismiss()       // Notifica fechamento
                    }
                },
                onDismiss = {
                    scope.launch {
                        sheetState.hide()
                        onDismiss()
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Preview
@Composable
fun PreviewBookStatusDialog(){
    val radioOptions = listOf<String>("Lido", "Lendo", "Quero ler", "Abandonei")
    var selectedOption by remember { mutableStateOf<String>("") }
    BookStatusBottomSheet(radioOptions = radioOptions, selectedOption = radioOptions.first(), onSelectionChange = { selectedOption = it}, onDismiss = {})
}
