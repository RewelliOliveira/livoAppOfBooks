package com.example.livoappofbooks.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.livoappofbooks.ui.theme.Gray

@Composable
fun FilterBar(
    selectedFilter: String,
    onFilterSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val filters = listOf("Todos", "Lendo", "Lido", "Quero Ler", "Abandonado")

    var textPositions by remember { mutableStateOf<Map<String, Pair<Float, IntSize>>>(emptyMap()) }
    var rowWidth by remember { mutableStateOf(0) }
    val density = LocalDensity.current

    Column(modifier = modifier.fillMaxWidth()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .onGloballyPositioned { rowWidth = it.size.width },
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            filters.forEach { filter ->
                Text(
                    text = filter,
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    fontWeight = if (selectedFilter == filter) FontWeight.Bold else FontWeight.Normal,
                    color = if (selectedFilter == filter)
                        MaterialTheme.colorScheme.primary
                    else
                        Gray,
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
                    .background(MaterialTheme.colorScheme.surface)
            )

            textPositions[selectedFilter]?.let { (xPx, sizePx) ->

                val index = filters.indexOf(selectedFilter)

                val left = when (index) {
                    0 -> 0f
                    else -> {
                        val prev = textPositions[filters[index - 1]]
                        if (prev != null) (prev.first + prev.second.width + xPx) / 2f else xPx
                    }
                }

                val right = when (index) {
                    filters.lastIndex -> rowWidth.toFloat()
                    else -> {
                        val next = textPositions[filters[index + 1]]
                        if (next != null) (xPx + sizePx.width + next.first) / 2f else xPx + sizePx.width
                    }
                }

                Box(
                    modifier = Modifier
                        .offset(x = with(density) { left.toDp() })
                        .width(with(density) { (right - left).toDp() })
                        .height(2.dp)
                        .background(MaterialTheme.colorScheme.primary)
                )
            }
        }
    }
}
