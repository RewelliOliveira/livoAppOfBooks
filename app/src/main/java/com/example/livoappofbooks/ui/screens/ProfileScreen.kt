package com.example.livoappofbooks.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import com.example.livoappofbooks.R
import com.example.livoappofbooks.ui.components.ConfigSection
import com.example.livoappofbooks.ui.components.CustomSwitch
import com.example.livoappofbooks.ui.components.InfoCard
import com.example.livoappofbooks.ui.theme.AlertColor
import com.example.livoappofbooks.ui.theme.rememberThemeState

@Composable
fun ProfileScreen(onNavigate: () -> Unit) {
    val themeState = rememberThemeState()
    val isDark = themeState.isDarkTheme

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(horizontal = 25.dp)
        ) {
            // Header igual ao da Library
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(
                        id = if (isDark) R.drawable.livo_dark else R.drawable.livo
                    ),
                    contentDescription = "LIVO Logo",
                    modifier = Modifier
                        .height(30.dp)
                        .width(100.dp)
                )

            }


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "Nome do usuário",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                    Text(
                        "example@mail.com",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f)
                    )
                }

                IconButton(
                    onClick = { /* TODO: Implementar edição de perfil */ }
                ) {
                    val editIconRes = if (isDark) {
                        R.drawable.ic_edit_dark
                    } else {
                        R.drawable.ic_edit_light
                    }

                    Image(
                        painter = painterResource(id = editIconRes),
                        contentDescription = "Editar perfil",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                InfoCard(
                    modifier = Modifier.weight(1f),
                    numero = "14",
                    texto = "Livros em leitura",
                    isDark = isDark
                )
                InfoCard(
                    modifier = Modifier.weight(1f),
                    numero = "31",
                    texto = "Livros Lidos",
                    isDark = isDark
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                ConfigSection(
                    titulo = "Aparência",
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val iconRes = if (isDark) {
                                R.drawable.ic_theme_dark
                            } else {
                                R.drawable.ic_theme_light
                            }

                            Image(
                                painter = painterResource(id = iconRes),
                                contentDescription = if (isDark) {
                                    "Modo escuro ativado"
                                } else {
                                    "Modo claro ativado"
                                },
                                modifier = Modifier.size(24.dp)
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Column {
                                Text(
                                    "Modo escuro",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.tertiary
                                )
                                Text(
                                    "Reduz o cansaço visual",
                                    fontSize = 13.sp,
                                    color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f)
                                )
                            }
                        }

                        CustomSwitch(
                            checked = isDark,
                            onCheckedChange = { themeState.toggleTheme() }
                        )
                    }
                }

                ConfigSection(
                    titulo = "Notificações",
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val notiIconRes = if (isDark) {
                                R.drawable.ic_noti_light
                            } else {
                                R.drawable.ic_noti_dark
                            }

                            Image(
                                painter = painterResource(id = notiIconRes),
                                contentDescription = if (isDark) {
                                    "Ícone de notificações modo escuro"
                                } else {
                                    "Ícone de notificações modo claro"
                                },
                                modifier = Modifier.size(24.dp)
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Column {
                                Text(
                                    "Ativar as notificações",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.tertiary
                                )
                                Text(
                                    "Receba lembretes todos os dias",
                                    fontSize = 13.sp,
                                    color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f)
                                )
                            }
                        }
                        CustomSwitch(
                            checked = false,
                            onCheckedChange = { /* TODO: Implementar notificações */ }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            OutlinedButton(
                onClick = { /* TODO: Implementar logout */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = AlertColor
                ),
                border = BorderStroke(1.dp, AlertColor)
            ) {
                Text("Sair da conta", fontWeight = FontWeight.Bold)
            }
        }
    }
}