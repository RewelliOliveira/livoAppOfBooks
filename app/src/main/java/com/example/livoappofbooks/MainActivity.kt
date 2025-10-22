package com.example.livoappofbooks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.livoappofbooks.ui.screens.InitialScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InitialScreen(
                title = "Organize suas leituras com o Livo!",
                subTitle = "Selecione uma das opções para continuar"
            )
        }
    }
}
