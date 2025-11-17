package com.example.livoappofbooks.ui.components.modals.dialogs

import com.example.livoappofbooks.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.livoappofbooks.ui.components.OutlinedIconLabelButton

@Composable
fun ConfirmRemoveBookDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
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
                verticalArrangement = Arrangement.spacedBy(12.dp),
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
                        color = MaterialTheme.colorScheme.secondary,
                        onClick = onDismiss
                    )
                    OutlinedIconLabelButton(
                        text = "Remover",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_trash_bin),
                        color = MaterialTheme.colorScheme.error,
                        onClick = onConfirm
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun RemoveBookDialogPreview() {

        ConfirmRemoveBookDialog(
            onDismiss = {},
            onConfirm = {}
        )
}