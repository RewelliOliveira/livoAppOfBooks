package com.example.livoappofbooks.ui.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CornerCloseButton(onDismiss: () -> Unit) {
    Box(
        modifier = Modifier
            .size(40.dp) // controla o tamanho total do botão
            .background(Color(0xFFD9D9D9), shape = CircleShape)
            .clickable(onClick = onDismiss),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Fechar",
            modifier = Modifier.size(20.dp), // tamanho real do ícone
            tint = MaterialTheme.colorScheme.primary
        )
    }
}