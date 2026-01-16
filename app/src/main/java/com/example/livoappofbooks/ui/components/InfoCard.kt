package com.example.livoappofbooks.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.livoappofbooks.R
import com.example.livoappofbooks.ui.theme.*
@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    numero: String,
    texto: String,
    isDark: Boolean
) {
    Surface(
        modifier = modifier.height(100.dp),
        shape = RoundedCornerShape(12.dp),
        color = background,
        border = BorderStroke(1.dp, onBackground)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                numero,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = onBackground
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_book),
                    contentDescription = "Ícone de livro",
                    colorFilter = ColorFilter.tint(outline),
                    modifier = Modifier.size(16.dp)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    texto,
                    fontSize = 13.sp,
                    color = onBackground
                )
            }
        }
    }
}