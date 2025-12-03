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
import com.example.livoappofbooks.viewmodel.ThemeViewModel

@Composable
fun ProfileScreen(
    onNavigate: () -> Unit,
    themeViewModel: ThemeViewModel
) {
    val isDark by themeViewModel.isDarkTheme.collectAsState()

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

            // --- Logo ---
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.livo),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                    contentDescription = "LIVO Logo",
                    modifier = Modifier
                        .height(30.dp)
                        .width(100.dp)
                )
            }

            // --- Perfil ---
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

                IconButton(onClick = { }) {
                    Image(
                        painter = painterResource(R.drawable.ic_edit),
                        contentDescription = "Editar perfil",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            // --- Info Cards ---
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

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                // --- Aparência ---
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
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
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
                            onCheckedChange = { themeViewModel.toggleTheme() }
                        )
                    }
                }

                // --- Notificações ---
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
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
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
                            onCheckedChange = { }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            OutlinedButton(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.error
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.error)
            ) {
                Text("Sair da conta", fontWeight = FontWeight.Bold)
            }
        }
    }
}
