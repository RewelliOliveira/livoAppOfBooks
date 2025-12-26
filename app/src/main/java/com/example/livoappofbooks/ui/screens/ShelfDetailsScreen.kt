package com.example.livoappofbooks.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.livoappofbooks.ui.components.PrimaryButton

@Composable
fun ShelfDetailsScreen(
    shelfId: String?,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Detalhes da Prateleira: $shelfId")
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryButton(
            text = "Voltar",
            onClick = onBackClick
        )
    }
}
