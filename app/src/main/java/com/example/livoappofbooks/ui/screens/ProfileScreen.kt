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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.livoappofbooks.R
import com.example.livoappofbooks.ui.components.ConfigSection
import com.example.livoappofbooks.ui.components.CustomSwitch
import com.example.livoappofbooks.ui.components.InfoCard
import com.example.livoappofbooks.ui.viewModel.ThemeViewModel
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.statusBars
import com.example.livoappofbooks.ui.theme.*
import androidx.compose.foundation.isSystemInDarkTheme
import com.example.livoappofbooks.ui.theme.rememberThemeState

@Composable
fun ProfileScreen(
    onNavigate: () -> Unit,
    themeViewModel: ThemeViewModel
) {
    val isDark by themeViewModel.isDarkTheme.collectAsState()

    val isDarkTheme = runCatching { rememberThemeState().isDarkTheme }
        .getOrElse { isSystemInDarkTheme() }

    val resolvedDark = runCatching { isDark }.getOrElse { isDarkTheme }
    val logoRes = if (resolvedDark) R.drawable.livo_white else R.drawable.livo

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = background
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.statusBars)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(logoRes),
                        contentDescription = "LIVO Logo",
                        modifier = Modifier
                            .height(30.dp)
                            .width(100.dp)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
                // Seção de perfil do usuário
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .clip(CircleShape)
                            .background(primary.copy(alpha = 0.2f))
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "Nome do usuário",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = tertiary
                        )
                        Text(
                            "example@mail.com",
                            fontSize = 14.sp,
                            color = tertiary.copy(alpha = 0.7f)
                        )
                    }

                    IconButton(onClick = { }) {
                        Image(
                            painter = painterResource(R.drawable.ic_edit),
                            contentDescription = "Editar perfil",
                            colorFilter = ColorFilter.tint(outline),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Cards de estatísticas
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

                // Seções de configuração
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

                            Row(verticalAlignment = Alignment.CenterVertically) {

                                Image(
                                    painter = painterResource(R.drawable.ic_theme),
                                    contentDescription = "Tema",
                                    colorFilter = ColorFilter.tint(outline),
                                    modifier = Modifier.size(24.dp)
                                )

                                Spacer(modifier = Modifier.width(12.dp))

                                Column {
                                    Text(
                                        "Modo escuro",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = tertiary
                                    )
                                    Text(
                                        "Reduz o cansaço visual",
                                        fontSize = 13.sp,
                                        color = tertiary.copy(alpha = 0.7f)
                                    )
                                }
                            }

                            CustomSwitch(
                                checked = isDark,
                                onCheckedChange = { themeViewModel.toggleTheme() }
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

                            Row(verticalAlignment = Alignment.CenterVertically) {

                                Image(
                                    painter = painterResource(R.drawable.ic_noti),
                                    contentDescription = "Notificações",
                                    colorFilter = ColorFilter.tint(outline),
                                    modifier = Modifier.size(24.dp)
                                )

                                Spacer(modifier = Modifier.width(12.dp))

                                Column {
                                    Text(
                                        "Ativar as notificações",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = tertiary
                                    )
                                    Text(
                                        "Receba lembretes todos os dias",
                                        fontSize = 13.sp,
                                        color = tertiary.copy(alpha = 0.7f)
                                    )
                                }
                            }

                            CustomSwitch(
                                checked = false,
                                onCheckedChange = { }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                // Botão de sair
                OutlinedButton(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = error
                    ),
                    border = BorderStroke(1.dp, error)
                ) {
                    Text("Sair da conta", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}