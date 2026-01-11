package com.example.livoappofbooks.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.livoappofbooks.ui.components.*
import com.example.livoappofbooks.ui.icons.Arrow_back_ios_new
import com.example.livoappofbooks.ui.icons.BookOpen
import com.example.livoappofbooks.ui.icons.Close_small
import com.example.livoappofbooks.ui.icons.Send
import com.example.livoappofbooks.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterReadingScreen(onNavigate: () -> Unit) {

    var titulo by remember { mutableStateOf("") }
    var paginas by remember { mutableStateOf("") }
    var resenha by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Registrar Leitura",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onNavigate() }) {
                        Icon(
                            imageVector = Arrow_back_ios_new,
                            contentDescription = "Voltar"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = background
                )
            )
        },
        bottomBar = {
            Surface(
                color = background
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    OutlinedButton(
                        onClick = {},
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp),
                        border = BorderStroke(1.dp, outline),
                        shape = RoundedCornerShape(50)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Close_small,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp),
                                tint = outline
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = "Cancelar",
                                color = outline
                            )
                        }
                    }

                    Button(
                        onClick = {},
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = outline
                        ),
                        shape = RoundedCornerShape(50)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Salvar",
                                color = background
                            )
                            Spacer(Modifier.width(8.dp))
                            Icon(
                                imageVector = Send,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp),
                                tint = background
                            )
                        }
                    }
                }
            }
        },
        containerColor = background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
                .background(background),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = "https://covers.openlibrary.org/b/id/15119025-L.jpg",
                    contentDescription = "Capa do livro",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(85.dp, 120.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(Modifier.width(16.dp))

                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

                    Text(
                        "As Estrelas do Amanhã",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = onBackground
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Marina Alves",
                            fontSize = 14.sp,
                            color = onBackground,
                            modifier = Modifier.weight(1f)
                        )

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = BookOpen,
                                contentDescription = "Livro aberto"
                            )
                            Text("367 pags.", fontSize = 12.sp)
                        }
                    }

                    Spacer(Modifier.width(30.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "Lendo",
                            fontSize = 12.sp,
                            color = BackgroundLight,
                            modifier = Modifier
                                .background(
                                    PrincipalColor,
                                    RoundedCornerShape(30.dp)
                                )
                                .padding(horizontal = 30.dp, vertical = 3.dp)
                        )

                        StarRating(
                            rating = 3.5,
                            maxStars = 5,
                            starSize = 20
                        )
                    }
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                ProgressBarSimple(progress = 0.7f)
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

                SmallInput(
                    value = titulo,
                    onValueChange = { titulo = it },
                    label = "Título da resenha (opcional)",
                    modifier = Modifier.weight(1f)
                )

                SmallInput(
                    value = paginas,
                    onValueChange = {
                        if (it.all { char -> char.isDigit() }) {
                            paginas = it
                        }
                    },
                    label = "Pág.",
                    modifier = Modifier.width(90.dp)
                )
            }

            OutlinedTextField(
                value = resenha,
                onValueChange = { resenha = it },
                label = { Text("Escreva sua resenha (opcional)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                shape = RoundedCornerShape(12.dp),
            )
        }
    }
}