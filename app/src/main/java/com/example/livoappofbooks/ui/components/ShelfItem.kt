package com.example.livoappofbooks.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.livoappofbooks.ui.icons.Arrow_forward_ios_new
import com.example.livoappofbooks.ui.theme.primary
import com.example.livoappofbooks.ui.theme.tertiary

data class Prateleira(
    val nome: String,
    val quantidadeLivros: Int,
    val capas: List<String>
)

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