package com.example.livoappofbooks.ui.components.modals.sheets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.livoappofbooks.data.remote.shelves.dto.ShelfResponse
import com.example.livoappofbooks.ui.components.modals.ModalHeader
import com.example.livoappofbooks.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddToShelfBottomSheet(
    shelves: List<ShelfResponse>,
    onShelfSelected: (shelfId: String) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState,
        containerColor = background,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ModalHeader(
                title = "Adicionar à Prateleira",
                subTitle = "Escolha uma prateleira",
                onDismiss = {
                    scope.launch { sheetState.hide() }
                    onDismiss()
                }
            )

            if (shelves.isEmpty()) {
                Text(
                    text = "Nenhuma prateleira encontrada",
                    style = AppTypography.bodyMedium,
                    color = tertiary,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(shelves) { shelf ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    scope.launch {
                                        sheetState.hide()
                                        onDismiss()
                                    }
                                    onShelfSelected(shelf.id)
                                }
                                .padding(vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = shelf.name,
                                style = AppTypography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                                color = onBackground,
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = "${shelf.quantity} livros",
                                style = AppTypography.bodySmall,
                                color = tertiary
                            )
                        }
                        HorizontalDivider(color = tertiary.copy(alpha = 0.3f))
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
