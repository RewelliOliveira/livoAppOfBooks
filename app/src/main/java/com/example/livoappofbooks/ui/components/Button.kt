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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.livoappofbooks.ui.theme.AppTypography
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import com.example.livoappofbooks.ui.theme.*
@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    enabled: Boolean = true,
    height: Dp? = null,
    width: Dp? = null
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = outline,
            disabledContainerColor = inversePrimary
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
                    tint = background,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(8.dp))
            }

            Text(
                text = text,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = AppTypography.labelMedium.copy(color = background)
            )
        }
    }
}




