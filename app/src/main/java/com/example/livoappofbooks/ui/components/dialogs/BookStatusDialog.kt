package com.example.livoappofbooks.ui.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.livoappofbooks.ui.components.RadioButtonSingleSelection

@Composable
fun BookStatusDialog(
    radioOptions: List<String>,
    selectedOption: String,
    onSelectionChange: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Box(
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .padding(24.dp)
        ) {
            Column (verticalArrangement = Arrangement.SpaceBetween){
                DialogTopBar(title = "Status do livro",
                    subTitle = "Escolha o status da leitura do seu livro",
                    onDismiss = onDismiss)

                Spacer(Modifier.height(8.dp))
                RadioButtonSingleSelection(radioOptions = radioOptions,
                    selectedOption = selectedOption,
                    onSelectionChange = onSelectionChange,
                    onDismiss = onDismiss)
            }
        }
    }
}


@Preview
@Composable
fun PreviewBookStatusDialog(){
    val radioOptions = listOf<String>("Lido", "Lendo", "Quero ler", "Abandonei")
    var selectedOption by remember { mutableStateOf<String>("") }
    BookStatusDialog(radioOptions = radioOptions, selectedOption = radioOptions.first(), onSelectionChange = { selectedOption = it}, onDismiss = {})
}
