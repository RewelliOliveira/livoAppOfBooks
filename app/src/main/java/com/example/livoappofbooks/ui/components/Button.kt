package com.example.livoappofbooks.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.livoappofbooks.ui.theme.AppTypography
import com.example.livoappofbooks.ui.theme.PrincipalColor
import com.example.livoappofbooks.ui.theme.DarkColor
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.unit.Dp

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    enabled: Boolean = true,
    useDarkColor: Boolean = false,
    height: Dp? = null,
    width: Dp? = null
) {
    val backgroundColor =
        if (useDarkColor) DarkColor else PrincipalColor

    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            disabledContainerColor = Color.Gray.copy(alpha = 0.5f)
        ),
        shape = RoundedCornerShape(50),
        modifier = modifier
            .then(
                if (width != null) Modifier.width(width) else Modifier
            )
            .then(
                if (height != null) Modifier.height(height) else Modifier
            )
            .padding(horizontal= 10.dp)
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )
            Spacer(Modifier.width(8.dp))
        }
        Text(
            text = text,
            maxLines = 1,
            style = AppTypography.labelMedium.copy(color = Color.White)
        )
    }
}


// --- PREVIEWS PARA DEMONSTRAÇÃO ---

@Preview(showBackground = true, name = "Botão Padrão com Ícone")
@Composable
fun PrimaryButtonWithIconPreview() {
    Box(Modifier.padding(16.dp)) {
        PrimaryButton(
            text = "Adicionar à biblioteca",
            onClick = {},
            icon = Icons.Default.Add
        )
    }
}

@Preview(showBackground = true, name = "Botão Padrão sem Ícone")
@Composable
fun PrimaryButtonWithoutIconPreview() {
    Box(Modifier.padding(16.dp)) {
        PrimaryButton(
            text = "Confirmar",
            onClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Botão Desativado")
@Composable
fun PrimaryButtonDisabledPreview() {
    Box(Modifier.padding(16.dp)) {
        PrimaryButton(
            text = "Adicionar à biblioteca",
            onClick = {},
            icon = Icons.Default.Add,
            enabled = false
        )
    }
}
