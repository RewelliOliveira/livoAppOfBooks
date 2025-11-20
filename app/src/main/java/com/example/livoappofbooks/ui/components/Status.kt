    package com.example.livoappofbooks.ui.components

    import com.example.livoappofbooks.ui.icons.*
    import androidx.compose.foundation.BorderStroke
    import androidx.compose.foundation.layout.Spacer
    import androidx.compose.foundation.layout.defaultMinSize
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.heightIn
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.size
    import androidx.compose.foundation.layout.width
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.material3.Icon
    import androidx.compose.material3.OutlinedButton
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.tooling.preview.Preview
    import androidx.compose.ui.unit.dp
    import com.example.livoappofbooks.domain.model.BookStatus

    import com.example.livoappofbooks.ui.theme.AppTypography
    @Composable
    fun Status(
        modifier: Modifier,
        status: BookStatus,
        onClick: () -> Unit
    ) {
        OutlinedButton(
            modifier = Modifier
                .defaultMinSize(minHeight = 40.dp)
                .heightIn(min = 40.dp),
            onClick = onClick,
            border = BorderStroke(2.dp, status.color),
            shape = RoundedCornerShape(50)
        ) {
            Icon(
                imageVector = MarcaPagina,
                contentDescription = "Marca Página",
                modifier = Modifier.size(18.dp),
                tint = status.color
            )
            Spacer(Modifier.width(6.dp))
            Text(
                text = status.displayName,
                style = AppTypography.titleSmall.copy(color = status.color)
            )
        }
    }

