package com.example.livoappofbooks.ui.components.modals.dialogs

import android.R.attr.padding
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.livoappofbooks.ui.components.OutlinedIconLabelButton
import com.example.livoappofbooks.ui.components.StarRating
import com.example.livoappofbooks.ui.components.modals.ModalHeader
import com.example.livoappofbooks.ui.icons.MarcaPagina
import com.example.livoappofbooks.ui.theme.ThemeProvider

@Composable
fun RemoveBookDialog(
    onDismiss: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 4.dp 
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Remover livro",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Esse livro será removido da sua biblioteca, juntamente com todos os registros de leituras associados a ele ",
                    style = MaterialTheme.typography.bodyMedium,
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ){

                    OutlinedIconLabelButton(
                        text = "Cancelar",
                        icon = Icons.Default.Book,
                        color = Color.Green,
                        onClick = onClick
                    )
                    OutlinedIconLabelButton(
                        text = "Remover",
                        icon = Icons.Default.Book,
                        color = Color.Red,
                        onClick = onClick
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun RemoveBookDialogPreview() {

        RemoveBookDialog(
            onDismiss = {},
            onClick = {}
        )
}