package com.example.livoappofbooks.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.livoappofbooks.ui.components.*
import com.example.livoappofbooks.ui.icons.Arrow_back_ios_new
import com.example.livoappofbooks.ui.icons.BookOpen
import com.example.livoappofbooks.ui.icons.Close_small
import com.example.livoappofbooks.ui.icons.Send
import com.example.livoappofbooks.ui.theme.*
import com.example.livoappofbooks.ui.viewModel.RegisterReadingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterReadingScreen(
    viewModel: RegisterReadingViewModel,
    onNavigateBack: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    // Efeitos colaterais (Sucesso e Erro)
    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            onNavigateBack()
        }
    }

    LaunchedEffect(state.error) {
        state.error?.let { errorMsg ->
            snackbarHostState.showSnackbar(errorMsg)
            viewModel.clearError()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Registrar Leitura", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                },
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(imageVector = Arrow_back_ios_new, contentDescription = "Voltar")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = background)
            )
        },
        bottomBar = {
            // Barra de Ações (Cancelar / Salvar)
            Surface(
                color = background,
                shadowElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = { onNavigateBack() },
                        modifier = Modifier.weight(1f).height(52.dp),
                        border = BorderStroke(1.dp, outline),
                        shape = RoundedCornerShape(50),
                        enabled = !state.isSubmitting
                    ) {
                        Icon(Close_small, null, Modifier.size(20.dp), tint = outline)
                        Spacer(Modifier.width(8.dp))
                        Text("Cancelar", color = outline)
                    }

                    Button(
                        onClick = { viewModel.submitLog() },
                        modifier = Modifier.weight(1f).height(52.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = outline,
                            disabledContainerColor = outline.copy(alpha = 0.6f)
                        ),
                        shape = RoundedCornerShape(50),
                        enabled = !state.isSubmitting && !state.isLoadingBook
                    ) {
                        if (state.isSubmitting) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = background,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Text("Salvar", color = background)
                            Spacer(Modifier.width(8.dp))
                            Icon(Send, null, Modifier.size(20.dp), tint = background)
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

            // --- HEADER: DETALHES DO LIVRO ---
            if (state.isLoadingBook) {
                Box(Modifier.fillMaxWidth().height(120.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = PrincipalColor)
                }
            } else {
                val book = state.book
                val totalPages = book?.pageCount ?: 1
                val progressCurrent = book?.userReadProgress ?: 0
                val progressPercent = if (totalPages > 0) progressCurrent.toFloat() / totalPages else 0f

                Row(verticalAlignment = Alignment.CenterVertically) {

                    AsyncImage(
                        model = book?.thumbnail?.replace("http:", "https:"),
                        contentDescription = "Capa do livro",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(85.dp, 120.dp).clip(RoundedCornerShape(8.dp))
                    )

                    Spacer(Modifier.width(16.dp))

                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        Text(
                            text = book?.title ?: "Sem título",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = onBackground,
                            maxLines = 2
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = book?.authors?.firstOrNull() ?: "Autor desconhecido",
                                fontSize = 14.sp,
                                color = onBackground,
                                modifier = Modifier.weight(1f),
                                maxLines = 1
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(BookOpen, null, tint = onBackground)
                                Text(" ${book?.pageCount ?: "?"} pags.", fontSize = 12.sp, color = onBackground)
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = book?.status?.displayName ?: "Desconhecido",
                                fontSize = 12.sp,
                                color = BackgroundLight,
                                modifier = Modifier
                                    .background(PrincipalColor, RoundedCornerShape(30.dp))
                                    .padding(horizontal = 16.dp, vertical = 4.dp)
                            )
                            // Avaliação do Usuário
                            StarRating(
                                rating = state.userRating.toDouble(),
                                maxStars = 5,
                                starSize = 18
                            )
                        }
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    ProgressBarSimple(progress = progressPercent)
                }
            }


            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                SmallInput(
                    value = state.title,
                    onValueChange = { viewModel.onTitleChange(it) },
                    label = "Título da resenha (opcional)",
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions.Default
                )

                SmallInput(
                    value = state.pages,
                    onValueChange = { viewModel.onPagesChange(it) },
                    label = "Pág. lidas",
                    modifier = Modifier.width(110.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }

            OutlinedTextField(
                value = state.review,
                onValueChange = { viewModel.onReviewChange(it) },
                label = { Text("Escreva sua resenha (opcional)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                shape = RoundedCornerShape(12.dp),
                enabled = !state.isSubmitting,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = outline,
                    unfocusedBorderColor = outline.copy(alpha = 0.5f),
                    focusedLabelColor = outline,
                    cursorColor = outline
                )
            )
        }
    }
}