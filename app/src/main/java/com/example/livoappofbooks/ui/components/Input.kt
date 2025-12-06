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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.livoappofbooks.ui.theme.*

@Composable
fun Input(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                label,
                color = tertiary,
                fontWeight = FontWeight.Bold
            )
        },
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = onBackground,      // cor da borda ao focar
            unfocusedBorderColor = onBackground,    // cor da borda normal
            focusedLabelColor = background,       // cor do label focado
            unfocusedLabelColor = onBackground,     // label quando não focado
            cursorColor = background.copy(alpha = 0.8f) // cursor com opacidade
        )
    )
    Spacer(modifier = Modifier.height(15.dp))
}