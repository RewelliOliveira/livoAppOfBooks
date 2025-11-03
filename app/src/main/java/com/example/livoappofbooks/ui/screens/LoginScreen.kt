package com.example.livoappofbooks.ui.screens

import com.example.livoappofbooks.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.livoappofbooks.ui.icons.Arrow_back_ios_new
import com.example.livoappofbooks.ui.components.Input

@Composable
fun LoginScreen() {
    Scaffold(
        topBar = { Header() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 30.dp)
        ) {

            Main()
        }
    }
}

@Composable
private fun Header(modifier: Modifier = Modifier){
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
            modifier = Modifier.align(Alignment.CenterStart)
        )

        Image(
            painter = painterResource(R.drawable.livo),
            contentDescription = "Icone Livo",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun Main(modifier: Modifier = Modifier){
    var text by remember { mutableStateOf("") }
    Spacer(modifier = Modifier.height(30.dp))
    Text(
        "Seja Bem vindo",
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(5.dp))
    Text(
        "Insira seus dados para acessar sua conta",
        fontSize = 15.sp
    )
    Spacer(modifier = Modifier.height(30.dp))
    Input("E-mail ou usuario")
    Input("Digite sua senha")

}


@Composable
private fun Footer(){

}

@Preview(showBackground = true)
@Composable
private fun LoginPreview(){
    LoginScreen()
}
