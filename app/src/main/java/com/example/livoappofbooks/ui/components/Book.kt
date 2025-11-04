package com.example.livoappofbooks.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun Book(
    status: String,
    progresso: Int,
    avaliacao: Int,
    imageUrl: String? = null
) {
    Column(
        modifier = Modifier
            .width(120.dp)
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f)
                .clip(RoundedCornerShape(8.dp))
        ) {
            if (!imageUrl.isNullOrEmpty()) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Capa do livro",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(android.R.drawable.ic_menu_gallery),
                    error = painterResource(android.R.drawable.ic_menu_gallery)
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF4A6572)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "CAPA",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val corFundo = when (status) {
                "Lido" -> Color(0xFF003D3A)
                "Lendo" -> Color(0xFF00244D)
                "Quero Ler" -> Color(0xFFF4B61A)
                "Abandonado" -> Color(0xFF444444)
                else -> Color(0xFF004D40)
            }

            Box(
                modifier = Modifier
                    .width(83.dp)
                    .height(20.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(corFundo),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = status,
                    color = Color.White,
                    fontSize = 9.47.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 9.47.sp
                )
            }

            if (status == "Lido") {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.height(14.dp)
                ) {
                    Text(
                        text = "$avaliacao",
                        color = corFundo,
                        fontSize = 9.47.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 9.47.sp
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Avaliação",
                        tint = Color(0xFFFFC107),
                        modifier = Modifier.size(12.dp)
                    )
                }
            } else {
                Text(
                    text = "$progresso%",
                    color = corFundo,
                    fontSize = 9.47.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 9.47.sp
                )
            }
        }
    }
}