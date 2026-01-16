package com.example.livoappofbooks.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.History
import com.example.livoappofbooks.ui.theme.*
import com.example.livoappofbooks.ui.icons.*

@Composable
fun ReadingHistoryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = outline,
            disabledContentColor = outline.copy(alpha = 0.5f)
        ),
        border = BorderStroke(
            1.dp,
            outline
        ),
        modifier = modifier
            .defaultMinSize(minHeight = 48.dp)
    ) {

        Icon(
            imageVector = Icons.Outlined.History,
            contentDescription = "Histórico de leituras",
            tint = outline,
            modifier = Modifier.size(18.dp)
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = "Historico de Leituras",
            style = AppTypography.titleSmall.copy(color = primary),
            color = outline
        )
    }
}


