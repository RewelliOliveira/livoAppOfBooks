package com.example.livoappofbooks.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.livoappofbooks.ui.components.PrimaryButton
import com.example.livoappofbooks.ui.icons.Arrow_forward_ios_new
import com.example.livoappofbooks.ui.theme.*

data class Prateleira(
    val nome: String,
    val quantidadeLivros: Int,
    val capas: List<String>
)

@Composable
fun ShelfsScreen(
    navController: NavController,
    onShelfClick: (Prateleira) -> Unit = {},
    onAddShelfClick: () -> Unit = {}
) {

    val capa1 = "https://m.media-amazon.com/images/I/91bYsX41DVL._SL1500_.jpg"
    val capa2 = "https://m.media-amazon.com/images/I/71g2ednj0JL._SL1500_.jpg"
    val capa3 = "https://m.media-amazon.com/images/I/81iqZ2HHD-L._SL1500_.jpg"
    val capa4 = "https://m.media-amazon.com/images/I/81OdwZ9PJbL._SL1500_.jpg"

    val prateleirasMock = listOf(
        Prateleira("Fantasia", 12, listOf(capa1, capa2, capa3)),
        Prateleira("Tecnologia", 5, listOf(capa4, capa2, capa1)),
        Prateleira("Favoritos", 8, listOf(capa3, capa4, capa2))
    )

    // REMOVA o Surface e Box externos, pois o Scaffold já cuida disso
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

        // Botão agora fica dentro da Column, não mais com align
        PrimaryButton(
            text = "Criar prateleira",
            onClick = onAddShelfClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp) // Ajuste o padding inferior
        )
    }
}

@Composable
fun ShelfItem(
    prateleira: Prateleira,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .width(120.dp)
                .height(90.dp)
        ) {
            prateleira.capas.take(3).forEachIndexed { index, url ->
                AsyncImage(
                    model = url,
                    contentDescription = "Capa do livro",
                    modifier = Modifier
                        .size(90.dp)
                        .offset(x = (index * 25).dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
        }

        Spacer(Modifier.width(20.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = prateleira.nome,
                color = tertiary,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "${prateleira.quantidadeLivros} livros",
                color = tertiary,
                fontSize = 14.sp
            )
        }

        Image(
            imageVector = Arrow_forward_ios_new,
            contentDescription = "Abrir",
            modifier = Modifier.size(28.dp),
            colorFilter = ColorFilter.tint(primary)
        )
    }
}