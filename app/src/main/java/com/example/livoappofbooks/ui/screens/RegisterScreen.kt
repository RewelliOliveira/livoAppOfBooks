package com.example.livoappofbooks.ui.screens

import com.example.livoappofbooks.R
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.livoappofbooks.ui.icons.Arrow_back_ios_new
import com.example.livoappofbooks.ui.components.Input
import com.example.livoappofbooks.ui.components.shapes.TopDiagonalShape
import com.example.livoappofbooks.ui.theme.PrincipalColor
import com.example.livoappofbooks.ui.theme.buttonShape

@Composable
fun RegisterScreen(
    onBackClick: () -> Unit,
    onRegisterComplete: (name: String, email: String, password: String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = { Header(onBackClick) },
        bottomBar = {
            CadastroFooter(
                onRegisterClick = {
                    if (password != confirmPassword) {
                        errorMessage = "As senhas não coincidem"
                    } else {
                        errorMessage = null
                        onRegisterComplete(name, email, password)
                    }
                }
            )
        }
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
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .height(50.dp)
    ) {
        Icon(
            imageVector = Arrow_back_ios_new,
            contentDescription = "Seta de voltar",
            tint = Color(0xFF003D3A),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .clickable { onBackClick() }
        )

        Image(
            painter = painterResource(R.drawable.livo),
            contentDescription = "Icone Livo",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun CadastroFooter(onRegisterClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .clip(TopDiagonalShape(280f))
            .background(Color(0xFF003D3A))
    ) {
        Button(
            onClick = onRegisterClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(55.dp)
                .padding(horizontal = 60.dp)
                .offset(y = (-80).dp)
                .border(
                    width = 2.dp,
                    color = PrincipalColor,
                    shape = buttonShape
                ),
            shape = buttonShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            )
        ) {
            Text(
                text = "Avançar",
                fontSize = 20.sp,
                color = Color(0xFF003D3A)
            )
        }
    }
}
