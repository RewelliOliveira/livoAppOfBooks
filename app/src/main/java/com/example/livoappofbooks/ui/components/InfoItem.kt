package com.example.livoappofbooks.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.livoappofbooks.ui.theme.AppTypography

@Composable
fun InfoItem(
    icon: ImageVector,
    text: String,
    width: Dp? = null,
    height: Dp? = null,
) {
    Row(
        modifier = Modifier
            .then(
                if (width != null) Modifier.width(width) else Modifier
            )
            .then(
                if (height != null) Modifier.height(height) else Modifier
            )
            .wrapContentWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )

        Spacer(Modifier.width(6.dp))

        Text(
            text = text,
            style = AppTypography.bodyMedium,
            color = Color.Black
        )
    }
}
