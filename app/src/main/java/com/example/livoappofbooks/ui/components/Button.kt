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
import androidx.compose.material.icons.filled.Add
import com.example.livoappofbooks.ui.icons.MarcaPagina // Exemplo para o preview

/**
 * Um botão primário reutilizável para o projeto.
 *
 * @param text O texto a ser exibido no botão.
 * @param onClick A ação a ser executada quando o botão for clicado.
 * @param modifier O modificador para customizar o botão.
 * @param icon (Opcional) O ícone a ser exibido à esquerda do texto.
 * @param enabled (Opcional) Define se o botão está ativo ou desativado.
 */
@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = PrincipalColor,
            disabledContainerColor = Color.Gray.copy(alpha = 0.5f)
        ),
        shape = RoundedCornerShape(50),
        modifier = modifier
            .defaultMinSize(minHeight = 40.dp) // 👈 mesma altura do Status
            .heightIn(min = 40.dp)              // garante consistência visual
            .shadow(
                elevation = if (enabled) 4.dp else 0.dp,
                shape = RoundedCornerShape(50)
            )
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(18.dp) // 👈 mesmo tamanho do ícone do Status
            )
            Spacer(Modifier.width(8.dp))
        }
        Text(
            text = text,
            style = AppTypography.titleSmall.copy(color = Color.White)
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
            icon = Icons.Default.Add // Usando o ícone do seu código original
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
