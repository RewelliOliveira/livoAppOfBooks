package com.example.livoappofbooks.ui.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.livoappofbooks.R
import com.example.livoappofbooks.ui.components.ConfigSection
import com.example.livoappofbooks.ui.components.CustomSwitch
import com.example.livoappofbooks.ui.components.InfoCard
import com.example.livoappofbooks.ui.theme.*
import com.example.livoappofbooks.ui.viewModel.ProfileViewModel
import com.example.livoappofbooks.ui.viewModel.ThemeViewModel

@Composable
fun ProfileScreen(
    themeViewModel: ThemeViewModel,
    viewModel: ProfileViewModel,
    onLogoutSuccess: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val isDark by themeViewModel.isDarkTheme.collectAsState()
    val profileImagePath by viewModel.profileImagePath.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchUserProfile()
    }
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                viewModel.updateProfileImage(context, uri)
            }
        }
    )

    val isDarkTheme = isSystemInDarkTheme()
    val isDarkThemeVal = runCatching { rememberThemeState().isDarkTheme }
        .getOrElse { isDarkTheme }
    val logoRes = if (isDarkThemeVal) R.drawable.livo_white else R.drawable.livo

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = background
    ) {
        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = primary)
            }
        } else {
            val userProfile = state.userProfile

            Box(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.statusBars)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(logoRes),
                            contentDescription = "LIVO Logo",
                            modifier = Modifier.height(30.dp).width(100.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(70.dp)
                                .clip(CircleShape)
                                .background(primary.copy(alpha = 0.2f))
                                .clickable {
                                    photoPickerLauncher.launch(
                                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                    )
                                }
                        ) {
                            if (profileImagePath != null) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(profileImagePath)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            } else {
                                Image(
                                    painter = painterResource(logoRes),
                                    contentDescription = null,
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = userProfile?.username ?: "Usuário",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = tertiary
                            )
                            Text(
                                text = userProfile?.email ?: "",
                                fontSize = 14.sp,
                                color = tertiary.copy(alpha = 0.7f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        InfoCard(
                            modifier = Modifier.weight(1f),
                            numero = userProfile?.reading?.toString() ?: "-",
                            texto = "Livros em leitura",
                            isDark = isDark
                        )
                        InfoCard(
                            modifier = Modifier.weight(1f),
                            numero = userProfile?.read?.toString() ?: "-",
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
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Image(
                                        painter = painterResource(R.drawable.ic_theme),
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(primary),
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
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(primary),
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

                    OutlinedButton(
                        onClick = {
                            viewModel.logout()
                            onLogoutSuccess()
                        },
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
}