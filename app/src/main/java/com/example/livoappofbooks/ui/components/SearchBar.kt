package com.example.livoappofbooks.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.livoappofbooks.ui.theme.PrincipalColor
import com.example.livoappofbooks.ui.theme.rememberThemeState

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val themeState = rememberThemeState()

    val backgroundColor = MaterialTheme.colorScheme.background

    val borderColor = if (themeState.isDarkTheme) {
        Color.White
    } else {
        PrincipalColor
    }

    val textColor = if (themeState.isDarkTheme) {
        Color.White
    } else {
        Color.Black
    }

    val placeholderColor = if (themeState.isDarkTheme) {
        Color.White
    } else {
        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
    }

    val iconColor = if (themeState.isDarkTheme) {
        Color.White
    } else {
        PrincipalColor
    }

    Box(
        modifier = modifier
            .height(41.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = query,
                onValueChange = onQueryChange,
                textStyle = TextStyle(
                    color = textColor,
                    fontSize = 16.sp
                ),
                cursorBrush = SolidColor(iconColor),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (query.isEmpty()) {
                            Text(
                                text = "Pesquisar na minha biblioteca",
                                style = TextStyle(
                                    fontFamily = null,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 10.sp,
                                    lineHeight = 10.sp,
                                    color = placeholderColor
                                )
                            )
                        }
                        innerTextField()
                    }
                }
            )

            Spacer(modifier = Modifier.width(12.dp))

            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Pesquisar",
                tint = iconColor,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}