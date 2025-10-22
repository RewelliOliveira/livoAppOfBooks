package com.example.livoappofbooks.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.livoappofbooks.R
import com.example.livoappofbooks.ui.theme.LightColor
import com.example.livoappofbooks.ui.theme.PrincipalColor
import com.example.livoappofbooks.ui.theme.buttonShape


@Composable
fun InitialScreen(title: String, subTitle: String, modifier: Modifier = Modifier){
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(LightColor)
    ) {
        Box(
            modifier = Modifier
                .weight(3f)
                .fillMaxWidth()
                .clip(RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomEnd = 60.dp,
                    bottomStart = 60.dp
                ))
                .background(PrincipalColor)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 30.dp)
                    .padding(bottom = 50.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    color = LightColor,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = subTitle,
                    fontSize = 17.sp,
                    color = LightColor
                )
            }

            Image(
                painter = painterResource(R.drawable.initiallogo_svg),
                contentDescription = "Icone Livo",
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(LightColor),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 60.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .border(
                            width = 2.dp,
                            color = PrincipalColor,
                            shape = buttonShape
                        ),
                    shape = buttonShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrincipalColor,
                    )
                ) {
                    Text(text = "Entrar",
                        fontSize = 20.sp,
                        color = LightColor)
                }

                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .border(
                            width = 2.dp,
                            color = PrincipalColor,
                            shape = buttonShape
                        ),
                    shape = buttonShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LightColor,
                    )
                ) {
                    Text(
                        text = "Cadastrar",
                        fontSize = 20.sp,
                        color = PrincipalColor)
                }
            }
        }

    }
}

@Preview
@Composable
private fun GreetingPreview() {
    InitialScreen(
        title = "Organize suas leituras com o Livo!",
        subTitle = "Selecione uma das opções para continuar"
    )
}