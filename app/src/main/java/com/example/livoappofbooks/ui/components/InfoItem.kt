package com.example.livoappofbooks.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.livoappofbooks.ui.theme.*
@Composable
fun InfoItem(
    icon: ImageVector,
    text: String,
    iconSize: Dp = 14.dp,
    textStyle: androidx.compose.ui.text.TextStyle = AppTypography.bodySmall
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = onBackground ,
            modifier = Modifier.size(iconSize)
        )

        Spacer(Modifier.width(6.dp))

        Text(
            text = text,
            style = textStyle,
            color = onBackground
        )
    }
}