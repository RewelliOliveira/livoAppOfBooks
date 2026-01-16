package com.example.livoappofbooks.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.livoappofbooks.ui.theme.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun Input(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            onValueChange(newValue.replace("\n", ""))
        },
        label = {
            Text(
                label,
                color = tertiary,
                fontWeight = FontWeight.Bold
            )
        },
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        singleLine = true,
        maxLines = 1,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = onBackground,
            unfocusedBorderColor = onBackground,
            focusedLabelColor = background,
            unfocusedLabelColor = onBackground,
            cursorColor = outline.copy(alpha = 0.8f)
        ),
        visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        trailingIcon = {
            if (isPassword) {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                val description = if (passwordVisible) "Esconder senha" else "Mostrar senha"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = description)
                }
            }
        }
    )
    Spacer(modifier = Modifier.height(15.dp))
}