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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.livoappofbooks.R
import com.example.livoappofbooks.ui.components.Input
import com.example.livoappofbooks.ui.components.shapes.TopDiagonalShape
import com.example.livoappofbooks.ui.icons.Arrow_back_ios_new
import com.example.livoappofbooks.ui.theme.PrincipalColor
import com.example.livoappofbooks.ui.theme.buttonShape
import com.example.livoappofbooks.ui.viewModel.LoginUiState
import com.example.livoappofbooks.ui.viewModel.LoginViewModel
import kotlinx.coroutines.flow.collectLatest

class LoginViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current.applicationContext
    val factory = LoginViewModelFactory(context)
    val viewModel: LoginViewModel = viewModel(factory = factory)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    // Observa estado do ViewModel
    LaunchedEffect(viewModel) {
        viewModel.uiState.collectLatest { state ->
            when (state) {
                is LoginUiState.Loading -> {
                    isLoading = true
                    errorMessage = null
                }

                is LoginUiState.Success -> {
                    isLoading = false
                    errorMessage = null
                    onLoginSuccess()
                }

                is LoginUiState.Error -> {
                    isLoading = false
                    errorMessage = state.message
                }

                else -> {
                    isLoading = false
                    errorMessage = null
                }
            }
        }
    }

    Scaffold(
        topBar = { Header(onBackClick) },
        bottomBar = {
            Footer(
                onLoginClick = { viewModel.login(email, password) },
                isLoading = isLoading
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 30.dp)
        ) {

            Main(
                email = email,
                onEmailChange = { email = it },
                password = password,
                onPasswordChange = { password = it }
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
private fun Header(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
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
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .clickable { onBackClick() }
        )

        Image(
            painter = painterResource(R.drawable.livo),
            contentDescription = "Icone Livo",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun Main(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.height(30.dp))

    Text(
        "Seja Bem vindo",
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground
    )

    Spacer(modifier = Modifier.height(5.dp))

    Text(
        "Insira seus dados para acessar sua conta",
        fontSize = 15.sp,
        color = MaterialTheme.colorScheme.tertiary
    )

    Spacer(modifier = Modifier.height(30.dp))

    Input(
        label = "E-mail ou usuário",
        value = email,
        onValueChange = onEmailChange
    )

    Input(
        label = "Digite sua senha",
        value = password,
        onValueChange = onPasswordChange
    )
}

@Composable
fun Footer(
    onLoginClick: () -> Unit,
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
            onClick = onLoginClick,
            enabled = !isLoading,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(55.dp)
                .padding(horizontal = 60.dp)
                .offset(y = (-80).dp)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.background,
                    shape = buttonShape
                ),
            shape = buttonShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color(0xFF003D3A),
                    strokeWidth = 2.dp
                )
            } else {
                Text(
                    text = "Avançar",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
