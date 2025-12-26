package com.example.livoappofbooks.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.livoappofbooks.ui.icons.Arrow_forward_ios_new
import com.example.livoappofbooks.ui.theme.primary
import com.example.livoappofbooks.ui.theme.tertiary
import androidx.compose.ui.res.painterResource
import com.example.livoappofbooks.R
import com.example.livoappofbooks.ui.theme.background
import com.example.livoappofbooks.ui.theme.onBackground


data class Shelf(
    val id: String,
    val nome: String,
    val quantidadeLivros: Int,
    val capas: List<String?>
)

@Composable
fun ShelfItem(
    prateleira: Shelf,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 10.dp,
                spotColor = onBackground.copy(alpha = 0.5f),
                ambientColor = onBackground.copy(alpha = 0.5f)
            )
            .background(color = background, shape = RoundedCornerShape(10.dp))
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .width(105.dp)
                    .height(90.dp)
            ) {

                val capasOrdenadas = prateleira.capas
                    .sortedBy { it == null }
                    .take(3)
                    .asReversed()

                val total = capasOrdenadas.size
                val overlap = 28
                val baseOffset = when (total) {
                    1 -> overlap
                    2 -> overlap / 2
                    else -> 0
                }

                capasOrdenadas.forEachIndexed { index, url ->
                    val realIndex = (total - 1) - index
                    val offsetX = baseOffset + realIndex * overlap

                    Box(
                        modifier = Modifier
                            .width(60.dp)
                            .height(90.dp)
                            .offset(x = offsetX.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(tertiary.copy(alpha = 0.2f))
                    ) {
                        AsyncImage(
                            model = url,
                            contentDescription = "Capa do livro",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop,
                            placeholder = painterResource(R.drawable.capa_default),
                            error = painterResource(R.drawable.capa_default)
                        )
                    }
                }
            }

            Spacer(Modifier.width(16.dp))

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

            androidx.compose.foundation.Image(
                imageVector = Arrow_forward_ios_new,
                contentDescription = "Abrir",
                modifier = Modifier.size(28.dp),
                colorFilter = ColorFilter.tint(primary)
            )
        }
    }
}
