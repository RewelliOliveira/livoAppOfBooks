package com.example.livoappofbooks.ui.components.sheets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.livoappofbooks.R
import com.example.livoappofbooks.ui.components.CheckboxSelection
import com.example.livoappofbooks.ui.components.DialogTopBar
import com.example.livoappofbooks.ui.components.RadioButtonSingleSelection
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShelfCheckboxBottomSheet(
    options: List<String>,
    selectedOptions: List<String>,
    onSelectionChange: (List<String>) -> Unit,
    onDismiss: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current
    val screenHeightPx = configuration.screenHeightDp.dp
    val maxSheetHeight = (screenHeightPx * 0.60f)

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = maxSheetHeight)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DialogTopBar(
                title = "Prateleiras",
                subTitle = "Escolha o status da leitura do seu livro",
                onDismiss = {
                    scope.launch { sheetState.hide() }
                    onDismiss()
                }
            )
            CheckboxSelection(
                options = options,
                selectedOptions = selectedOptions,
                onSelectionChange = onSelectionChange,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ShelfCheckboxBottomSheetPreview(){
    val options = listOf<String>("Romance", "Religião", "Filosofia", "Ficção")
    var selectedOptions by remember { mutableStateOf(listOf<String>()) }
    Box(){
        Text(text = "hello")
        ShelfCheckboxBottomSheet(options = options, selectedOptions = selectedOptions ,onSelectionChange = { selectedOptions = it}, onDismiss = {})
    }
}

