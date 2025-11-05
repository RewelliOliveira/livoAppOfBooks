package com.example.livoappofbooks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.livoappofbooks.ui.screens.LibraryScreen
import com.example.livoappofbooks.ui.theme.ThemeProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThemeProvider {
                LibraryScreen()
            }
        }
    }
}