package com.example.livoappofbooks.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.livoappofbooks.R
import com.example.livoappofbooks.ui.components.PrimaryButton
import com.example.livoappofbooks.ui.components.ShelfItem
import com.example.livoappofbooks.ui.icons.PlusCircle
import com.example.livoappofbooks.ui.theme.*

data class Prateleira(
    val nome: String,
    val quantidadeLivros: Int,
    val capas: List<String?>
)

@Composable
fun ShelfsScreen(
    onShelfClick: (Prateleira) -> Unit = {},
    onAddShelfClick: () -> Unit = {}
) {

    val capa1 =
        "http://books.google.com/books/publisher/content?id=OF0NEQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
    val capa2 =
        "http://books.google.com/books/publisher/content?id=OF0NEQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
    val capa3 =
        "http://books.google.com/books/publisher/content?id=OF0NEQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
    val capa4 = null

    val prateleirasMock = listOf(
        Prateleira("Fantasia", 12, listOf(capa1, capa2, capa3)),
        Prateleira("Tecnologia", 5, listOf(capa4, capa2, capa1)),
        Prateleira("Favoritos", 8, listOf(capa3, capa4, capa2))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Prateleiras",
            color = primary,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(24.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(prateleirasMock) { prateleira ->
                ShelfItem(
                    prateleira = prateleira,
                    onClick = { onShelfClick(prateleira) }
                )
            }

            item { Spacer(modifier = Modifier.height(100.dp)) }
        }

        PrimaryButton(
            text = "Criar prateleira",
            icon = PlusCircle,
            onClick = onAddShelfClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
    }
}
