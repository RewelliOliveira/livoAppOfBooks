package com.example.livoappofbooks.ui.screens.debug

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.tooling.preview.Preview
import com.example.livoappofbooks.ui.components.CustomSwitch
import com.example.livoappofbooks.ui.components.modals.dialogs.ConfirmRemoveBookDialog
import com.example.livoappofbooks.ui.components.modals.dialogs.RatingDialog
import com.example.livoappofbooks.ui.components.modals.sheets.BookStatusBottomSheet
import com.example.livoappofbooks.ui.components.modals.sheets.ShelfCheckboxBottomSheet
import com.example.livoappofbooks.ui.theme.ThemeProvider
import com.example.livoappofbooks.ui.viewModel.ThemeViewModel

@Composable
fun DebugModalsScreen(themeViewModel: ThemeViewModel) {
    val isDark by themeViewModel.isDarkTheme.collectAsState()

    // Estados de visibilidade dos modals
    var showRemoveBookDialog by remember { mutableStateOf(false) }
    var showRatingDialog by remember { mutableStateOf(false) }
    var showBookStatusSheet by remember { mutableStateOf(false) }
    var showShelfSheet by remember { mutableStateOf(false) }

    // Estados auxiliares
    var rating by remember { mutableStateOf(0.0) }
    val statusOptions = listOf("Lido", "Lendo", "Quero ler", "Abandonei")
    var selectedStatus by remember { mutableStateOf(statusOptions.first()) }
    val shelfOptions = listOf("Romance", "Religião", "Filosofia", "Ficção")
    var selectedShelves by remember { mutableStateOf(listOf<String>()) }

    ThemeProvider(
        isDarkTheme = isDark,
        toggleTheme = { themeViewModel.toggleTheme() }
    ) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Header com switch de tema
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Debug Modals", style = MaterialTheme.typography.titleLarge)
                    CustomSwitch(
                        checked = isDark,
                        onCheckedChange = { themeViewModel.toggleTheme() }
                    )
                }

                // Botões para abrir modals
                Button(onClick = { showRemoveBookDialog = true }) { Text("Abrir ConfirmRemoveBookDialog") }
                Button(onClick = { showRatingDialog = true }) { Text("Abrir RatingDialog") }
                Button(onClick = { showBookStatusSheet = true }) { Text("Abrir BookStatusBottomSheet") }
                Button(onClick = { showShelfSheet = true }) { Text("Abrir ShelfCheckboxBottomSheet") }
            }

            // Modals
            if (showRemoveBookDialog) ConfirmRemoveBookDialog(
                onDismiss = { showRemoveBookDialog = false },
                onConfirm = { showRemoveBookDialog = false }
            )

            if (showRatingDialog) RatingDialog(
                rating = rating,
                onRatingChange = { rating = it },
                onDismiss = { showRatingDialog = false }
            )

            if (showBookStatusSheet) BookStatusBottomSheet(
                options = statusOptions,
                selectedOption = selectedStatus,
                onSelectionChange = { selectedStatus = it },
                onDismiss = { showBookStatusSheet = false }
            )

            if (showShelfSheet) ShelfCheckboxBottomSheet(
                options = shelfOptions,
                selectedOptions = selectedShelves,
                onSelectionChange = { selectedShelves = it },
                onDismiss = { showShelfSheet = false }
            )
        }
    }
}
