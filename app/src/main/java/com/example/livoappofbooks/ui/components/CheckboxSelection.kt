package com.example.livoappofbooks.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.livoappofbooks.ui.theme.*

@Composable
fun CheckboxSelection(
    options: List<String>,
    selectedOptions: List<String>,
    onSelectionChange: (List<String>) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = modifier.fillMaxWidth()
    ) {
        items(options) { option ->
            val isSelected = option in selectedOptions
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val newList = if (isSelected) selectedOptions - option else selectedOptions + option
                        onSelectionChange(newList)
                    }
                    .padding(vertical = 8.dp, horizontal = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isSelected,
                    onCheckedChange = { checked ->
                        val newList = if (checked) selectedOptions + option else selectedOptions - option
                        onSelectionChange(newList)
                    }
                )
                Text(
                    text = option,
                    style = MaterialTheme.typography.bodyLarge,
                    color = onBackground,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}