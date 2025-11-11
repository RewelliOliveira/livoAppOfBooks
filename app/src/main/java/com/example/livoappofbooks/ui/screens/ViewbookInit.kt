package com.example.livoappofbooks.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.livoappofbooks.R
import com.example.livoappofbooks.ui.components.StarRating
import com.example.livoappofbooks.ui.components.InfoItem
import com.example.livoappofbooks.ui.theme.AppTypography
import com.example.livoappofbooks.ui.theme.LightColor
import com.example.livoappofbooks.ui.theme.PrincipalColor

// 1. ADICIONADOS NOVOS PARÂMETROS
@Composable
fun ViewBookScreen(
    title: String,
    author: String,
    rate: Double,
    sinopse: String,
    imageUrl: String,
    publishYear: String,
    publisher: String,
    pageCount: String,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            AsyncImage(
                model = imageUrl,
                placeholder = painterResource(id = R.drawable.livro_teste),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
                    .blur(10.dp)
                    .shadow(20.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, LightColor.copy(alpha = 1f)),
                            startY = 250f
                        )
                    )
            )

            AsyncImage(
                model = imageUrl,
                placeholder = painterResource(id = R.drawable.livro_teste),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(260.dp)
                    .width(170.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .shadow(16.dp, RoundedCornerShape(8.dp))
                    .align(Alignment.BottomCenter)
            )

            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
                    .size(36.dp)
                    .background(Color.White, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Voltar",
                    tint = Color.Black
                )
            }
        }

        // --- Conteúdo rolável + botão fixo ---
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp), // Aumentei o padding para um melhor respiro
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(360.dp))

            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = title,
                        style = AppTypography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = PrincipalColor
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = author,
                        style = AppTypography.bodyMedium,
                        color = Color.DarkGray
                    )
                }
                StarRating(rating = rate)
            }
            Spacer(Modifier.height(24.dp))

            // 2. SEÇÃO DE ÍCONES CORRIGIDA E REUTILIZÁVEL
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoItem(icon = Icons.Default.CalendarMonth, text = publishYear)
                InfoItem(icon = Icons.Default.MenuBook, text = "$pageCount págs")
                InfoItem(icon = Icons.Default.Book, text = publisher)
            }

            Spacer(Modifier.height(24.dp))
            Divider(color = Color.LightGray.copy(alpha = 0.6f))
            Spacer(Modifier.height(16.dp))

            Text(
                text = "Sinopse",
                style = AppTypography.titleMedium.copy(
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = sinopse,
                style = AppTypography.bodyMedium.copy(color = Color.DarkGray),
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(Modifier.height(100.dp)) // Espaço para não colar no botão
        }

        // --- Botão fixado no rodapé ---
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, LightColor.copy(alpha = 0.98f))
                    )
                )
                .padding(16.dp)
        ) {
            Button(
                onClick = { /* ação */ },
                colors = ButtonDefaults.buttonColors(containerColor = PrincipalColor),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .shadow(8.dp, RoundedCornerShape(50))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar",
                    tint = Color.White
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Adicionar à biblioteca",
                    style = AppTypography.titleSmall.copy(color = Color.White)
                )
            }
        }
    }
}

// 4. PREVIEW ATUALIZADO COM OS NOVOS DADOS
@Preview(showBackground = true)
@Composable
fun ViewBookScreenPreview() {
    ViewBookScreen(
        title = "As Estrelas do Amanhã",
        author = "Marina Alves",
        rate = 3.7,
        sinopse = "Em um futuro próximo, a Terra enfrenta crises ambientais que ameaçam a vida humana. Um grupo de jovens cientistas descobre um método para viajar até um planeta habitável...",
        imageUrl = "url_qualquer",
        publishYear = "2023",
        publisher = "Galera",
        pageCount = "240",
        onBackClick = {}
    )
}
