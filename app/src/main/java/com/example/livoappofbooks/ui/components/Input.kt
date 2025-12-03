package com.example.livoappofbooks.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun Input(label: String, modifier: Modifier = Modifier){
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { newText -> text = newText },
        label = { Text(
            label,
            color = MaterialTheme.colorScheme.tertiary,
            fontWeight = FontWeight.Bold
        ) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.onBackground,      // cor da borda ao focar
            unfocusedBorderColor = MaterialTheme.colorScheme.onBackground,    // cor da borda normal
            focusedLabelColor = MaterialTheme.colorScheme.background,       // cor do label focado
            unfocusedLabelColor = MaterialTheme.colorScheme.onBackground,     // label quando não focado
            cursorColor = MaterialTheme.colorScheme.background.copy(alpha = 0.8f) // cursor com opacidade
        )
    )
    Spacer(modifier = Modifier.height(15.dp))

}