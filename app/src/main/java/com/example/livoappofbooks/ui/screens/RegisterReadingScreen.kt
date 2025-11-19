package com.example.livoappofbooks.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Save
import com.example.livoappofbooks.ui.icons.Arrow_back_ios_new
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.livoappofbooks.ui.components.ProgressBarSimple
import com.example.livoappofbooks.ui.components.StarRating
import com.example.livoappofbooks.ui.icons.BookOpen
import com.example.livoappofbooks.ui.theme.BackgroundLight
import com.example.livoappofbooks.ui.theme.PrincipalColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterReadingScreen() {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    "Registrar Leitura", fontWeight = FontWeight.Bold, fontSize = 20.sp
                )
            }, navigationIcon = {
                IconButton(onClick = { /* TODO: voltar */ }) {
                    Icon(
                        imageVector = Arrow_back_ios_new, contentDescription = "Voltar"
                    )
                }
            })
        },

        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(70.dp), tonalElevation = 3.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(onClick = { }) {
                        Text("Cancelar")
                    }

                    Button(onClick = { }) {
                        Icon(Icons.Default.Save, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Salvar")
                    }
                }
            }
        }) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(85.dp, 120.dp)
                        .background(Color.Gray, RoundedCornerShape(8.dp))
                ) {
                    Icon(
                        Icons.Default.Book,
                        contentDescription = "Livro",
                        tint = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                Spacer(Modifier.width(16.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Text(
                        "As Estrelas do Amanhã", fontWeight = FontWeight.Bold, fontSize = 18.sp
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Marina Alves",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            modifier = Modifier.weight(1f),
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
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "Lendo",
                            color = BackgroundLight,
                            modifier = Modifier
                                .background(
                                    PrincipalColor,
                                    RoundedCornerShape(30.dp)
                                )
                                .padding(horizontal = 8.dp, vertical = 3.dp),
                        )

                        StarRating(
                            rating = 3.5,
                            maxStars = 5,
                            starSize = 20,
                        )
                    }
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProgressBarSimple(
                    progress = 0.7f,
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Título da resenha (opcional)") },
                    modifier = Modifier.weight(1f)
                )

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Pág.") },
                    modifier = Modifier.width(90.dp)
                )
            }

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Escreva sua resenha (opcional)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRegisterReadingScreen() {
    RegisterReadingScreen()
}
