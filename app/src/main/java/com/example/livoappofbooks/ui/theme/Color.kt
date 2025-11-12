package com.example.livoappofbooks.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

// 🌈 Cores principais do design Livo
@Stable val PrincipalColor = Color(0xFF003D3A)   // Verde principal (cor de destaque)
@Stable val DarkColor = Color(0xFF001327)        // Fundo escuro / modo noturno
@Stable val SubtitlesColor = Color(0xFF121212)   // Cinza muito escuro para textos secundários
@Stable val PositiveActions = Color(0xFF2BA80C)  // Verde claro para ações positivas
@Stable val AlertColor = Color(0xFFB30808)       // Vermelho para alertas / erros
@Stable val BackgroundLight = Color(0xFFFDFBED)  // Fundo claro (amarelado suave)

// 🌑 Tons neutros e auxiliares
@Stable val Black = Color(0xFF000000)
@Stable val White = Color(0xFFFFFFFF)
@Stable val Gray = Color(0xFF808080)
@Stable val Transparent = Color(0x00000000)

// 💡 Aliases semânticos (uso opcional para clareza de código)
@Stable val VerdePrincipal = PrincipalColor
@Stable val VerdeEscuro = DarkColor
@Stable val TextoSecundario = SubtitlesColor
@Stable val VerdeClaro = PositiveActions
@Stable val Vermelho = AlertColor
@Stable val FundoClaro = BackgroundLight
@Stable val Preto = Black
@Stable val Branco = White
@Stable val Cinza = Gray
