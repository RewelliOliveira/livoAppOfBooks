package com.example.livoappofbooks.ui.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.livoappofbooks.R
import com.example.livoappofbooks.ui.components.Input
import com.example.livoappofbooks.ui.components.shapes.TopDiagonalShape
import com.example.livoappofbooks.ui.icons.Arrow_back_ios_new
import com.example.livoappofbooks.ui.theme.*
import com.example.livoappofbooks.ui.viewModel.RegisterUiState
import com.example.livoappofbooks.ui.viewModel.RegisterViewModel

class RegisterViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegisterViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

@Composable
fun RegisterScreen(
    onBackClick: () -> Unit,
    onRegisterComplete: () -> Unit
) {
    val context = LocalContext.current.applicationContext
    val factory = RegisterViewModelFactory(context)
    val viewModel: RegisterViewModel = viewModel(factory = factory)

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState) {
        when (val state = uiState) {
            is RegisterUiState.Success -> {
                onRegisterComplete()
                viewModel.resetState() // Reseta o estado para evitar re-navegação
            }
            is RegisterUiState.Error -> {
                errorMessage = state.message
            }
            else -> {}
        }
    }

    Scaffold(
        topBar = { Header(onBackClick) },
        bottomBar = {
            CadastroFooter(
                onRegisterClick = {
                    if (password != confirmPassword) {
                        errorMessage = "As senhas não coincidem"
                    } else {
                        errorMessage = null
                        viewModel.register(name, email, password)
                    }
                },
                isLoading = (uiState is RegisterUiState.Loading)
            )
        },
        containerColor = background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 30.dp)
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                "Seja Bem vindo",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                "Insira seus dados para criar sua conta",
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(30.dp))

            Input(
                label = "Nome e sobrenome",
                value = name,
                onValueChange = { name = it }
            )

            Input(
                label = "E-mail",
                value = email,
                onValueChange = { email = it }
            )

            Input(
                label = "Digite sua senha",
                value = password,
                onValueChange = { password = it }
            )

            Input(
                label = "Confirme sua senha",
                value = confirmPassword,
                onValueChange = { confirmPassword = it }
            )

            if (errorMessage != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = errorMessage ?: "",
                    color = Color.Red,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
private fun Header(onBackClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .height(50.dp)
    ) {
        Icon(
            imageVector = Arrow_back_ios_new,
            contentDescription = "Seta de voltar",
            tint = primary,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .clickable { onBackClick() }
        )

        Image(
            painter = painterResource(R.drawable.livo),
            contentDescription = "Icone Livo",
            colorFilter = ColorFilter.tint(primary),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun CadastroFooter(
    onRegisterClick: () -> Unit,
    isLoading: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .clip(TopDiagonalShape(280f))
            .background(PrincipalColor)
    ) {
        Button(
            onClick = onRegisterClick,
            enabled = !isLoading,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(55.dp)
                .padding(horizontal = 60.dp)
                .offset(y = (-80).dp)
                .border(
                    width = 2.dp,
                    color = background,
                    shape = buttonShape
                ),
            shape = buttonShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = background
            )
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = primary,
                    strokeWidth = 2.dp
                )
            } else {
                Text(
                    text = "Avançar",
                    fontSize = 20.sp,
                    color = primary
                )
            }
        }
    }
}
