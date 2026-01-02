package com.example.livoappofbooks.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.livoappofbooks.R
import com.example.livoappofbooks.domain.model.BookStatus
import com.example.livoappofbooks.ui.theme.rememberThemeState

@Composable
fun Book(
    status: BookStatus,
    progress: Int,
    evaluate: Int,
    imageUrl: String,
    onClick: (() -> Unit)? = null
) {
    val isDark = rememberThemeState().isDarkTheme

    Column(
        modifier = Modifier
            .width(120.dp)
            .padding(4.dp)
            .clickable { onClick?.invoke() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f)
                .clip(RoundedCornerShape(10.dp))
        ) {
            AsyncImage(
                model = imageUrl.takeIf { it.isNotBlank() },
                placeholder = painterResource(id = R.drawable.capa_default),
                error = painterResource(id = R.drawable.capa_default),
                fallback = painterResource(id = R.drawable.capa_default),
                contentDescription = "Capa do livro",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            val corFundo = status.color
            val corTexto = Color.White

            val statusModifier =
                if (status == BookStatus.QUERO_LER) {
                    Modifier
                        .weight(1f)
                        .height(20.dp)
                } else {
                    Modifier
                        .width(75.dp)
                        .height(20.dp)
                }

            Box(
                modifier = statusModifier
                    .clip(RoundedCornerShape(15.dp))
                    .background(corFundo),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = status.displayName,
                    color = corTexto,
                    fontSize = 9.47.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 9.47.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            
            if (status != BookStatus.QUERO_LER) {
                val infoColor = if (isDark) Color.White else corFundo

                Box(
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .height(24.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    if (status == BookStatus.LIDO) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.height(14.dp)
                        ) {
                            Text(
                                text = evaluate.toString(),
                                color = infoColor,
                                fontSize = 9.47.sp,
                                fontWeight = FontWeight.Medium,
                                lineHeight = 9.47.sp
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Avaliação",
                                tint = Color(0xFFFFC107),
                                modifier = Modifier.size(12.dp)
                            )
                        }
                    } else {
                        Text(
                            text = "$progress%",
                            color = infoColor,
                            fontSize = 9.47.sp,
                            fontWeight = FontWeight.Medium,
                            lineHeight = 9.47.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun BookPreview() {
    Book(
        status = BookStatus.QUERO_LER,
        progress = 0,
        evaluate = 0,
        imageUrl = "",
        onClick = {}
    )
}
