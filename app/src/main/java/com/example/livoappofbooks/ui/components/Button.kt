package com.example.livoappofbooks.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.livoappofbooks.ui.theme.*

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    enabled: Boolean = true,
    height: Dp? = null,
    width: Dp? = null,
    style: TextStyle = AppTypography.labelMedium
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.outline,

            contentColor = MaterialTheme.colorScheme.background,

            // Cores para estado desabilitado
            disabledContainerColor = inversePrimary,
            disabledContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        shape = RoundedCornerShape(50),
        modifier = modifier
            .then(if (width != null) Modifier.width(width) else Modifier)
            .then(if (height != null) Modifier.height(height) else Modifier)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(8.dp))
            }

            Text(
                text = text,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = LocalContentColor.current,
                style = style
            )
        }
    }
}