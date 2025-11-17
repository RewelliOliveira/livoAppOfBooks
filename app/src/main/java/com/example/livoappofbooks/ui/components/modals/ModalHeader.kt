package com.example.livoappofbooks.ui.components.modals

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun ModalHeader(
    title: String,
    subTitle: String,
    onDismiss: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = subTitle,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        CloseModalButton(modifier = Modifier.size(40.dp), onDismiss = onDismiss)
    }
}

@Preview
@Composable
fun PreviewDialogTopBar(){
    ModalHeader(title = "Status do livro", subTitle = "Escolha o status da leitura do livro", onDismiss = {})
}