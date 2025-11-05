package com.example.livoappofbooks.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.livoappofbooks.ui.theme.LightColor
import com.example.livoappofbooks.ui.theme.PrincipalColor
import com.example.livoappofbooks.ui.theme.rememberThemeState

@Composable
fun FilterBar(
    selectedFilter: String,
    onFilterSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val filters = listOf("Todos", "Lendo", "Lido", "Quero Ler", "Abandonado")
    val themeState = rememberThemeState()
    val isDarkTheme = themeState.isDarkTheme

    val baseLineColor = if (isDarkTheme) Color(0xFF666666) else Color(0xFFB8D5D3)
    val selectedLineColor = if (isDarkTheme) LightColor else PrincipalColor
    val selectedTextColor = if (isDarkTheme) LightColor else PrincipalColor
    val unselectedTextColor = if (isDarkTheme) Color(0xFFAAAAAA) else Color.Gray

    var textPositions by remember { mutableStateOf<Map<String, Pair<Float, IntSize>>>(emptyMap()) }
    var rowWidth by remember { mutableStateOf(0) }
    val density = LocalDensity.current

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .onGloballyPositioned { coordinates ->
                    rowWidth = coordinates.size.width
                },
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            filters.forEach { filter ->
                Text(
                    text = filter,
                    fontSize = 14.sp,
                    fontWeight = if (selectedFilter == filter) FontWeight.Bold else FontWeight.Normal,
                    color = if (selectedFilter == filter) selectedTextColor else unselectedTextColor,
                    modifier = Modifier
                        .clickable { onFilterSelected(filter) }
                        .onGloballyPositioned {
                            textPositions = textPositions + (filter to (it.positionInParent().x to it.size))
                        }
                )
            }
        }

        Box(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(baseLineColor)
            )

            textPositions[selectedFilter]?.let { (xPx, sizePx) ->
                val currentIndex = filters.indexOf(selectedFilter)

                val left = when (currentIndex) {
                    0 -> 0f
                    else -> {
                        val prev = filters[currentIndex - 1]
                        val prevData = textPositions[prev]
                        if (prevData != null)
                            (prevData.first + prevData.second.width + xPx) / 2f
                        else xPx
                    }
                }

                val right = when (currentIndex) {
                    filters.lastIndex -> rowWidth.toFloat() // fim real do Row
                    else -> {
                        val next = filters[currentIndex + 1]
                        val nextData = textPositions[next]
                        if (nextData != null)
                            (xPx + sizePx.width + nextData.first) / 2f
                        else xPx + sizePx.width
                    }
                }

                Box(
                    modifier = Modifier
                        .offset(x = with(density) { left.toDp() })
                        .width(with(density) { (right - left).toDp() })
                        .height(2.dp)
                        .background(selectedLineColor)
                )
            }
        }
    }
}