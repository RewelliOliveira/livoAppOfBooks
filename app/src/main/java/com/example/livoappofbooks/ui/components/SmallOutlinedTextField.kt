package com.example.livoappofbooks.ui.components

import android.inputmethodservice.Keyboard
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions

@Composable
fun SmallInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontSize = 13.sp) },
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(fontSize = 16.sp)
    )
}

