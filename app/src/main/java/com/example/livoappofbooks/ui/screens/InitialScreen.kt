package com.example.livoappofbooks.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.livoappofbooks.R
import com.example.livoappofbooks.ui.theme.*

@Composable
fun InitialScreen(
    title: String,
    subTitle: String,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.66f)
                .clip(RoundedCornerShape(bottomEnd = 60.dp, bottomStart = 60.dp))
                .background(PrincipalColor)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 30.dp, bottom = 50.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    color = BackgroundLight,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = subTitle,
                    fontSize = 17.sp,
                    color = BackgroundLight
                )
            }

            Image(
                painter = painterResource(R.drawable.initiallogo_svg),
                contentDescription = "Icone Livo",
                modifier = Modifier
                    .size(110.dp)
                    .align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp, vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = onLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .border(
                        width = 2.dp,
                        color = primary,
                        shape = buttonShape
                    ),
                shape = buttonShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = primary,
                )
            ) {
                Text(
                    text = "Entrar",
                    fontSize = 20.sp,
                    color = background
                )
            }

            Button(
                onClick = onRegisterClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .border(
                        width = 2.dp,
                        color = onBackground,
                        shape = buttonShape
                    ),
                shape = buttonShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = background,
                )
            ) {
                Text(
                    text = "Cadastrar",
                    fontSize = 20.sp,
                    color = onBackground
                )
            }
        }
    }
}
