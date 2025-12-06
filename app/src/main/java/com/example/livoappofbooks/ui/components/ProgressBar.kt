package com.example.livoappofbooks.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.livoappofbooks.ui.theme.*

@Composable
fun ProgressBarBook(
    currentPage: Int,       
    totalPages: Int,
    modifier: Modifier = Modifier
) {
    val progress = currentPage.toFloat() / totalPages.toFloat()
    val percentage = (progress * 100).toInt()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, primary, RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .background(background, RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .padding(horizontal = 10.dp, vertical = 20.dp)

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$percentage% do livro foi lido",
                style = TextStyle(
                    color = primary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            )
            Text(
                text = "$currentPage/$totalPages",
                style = TextStyle(
                    color = primary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }

        Spacer(Modifier.height(6.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(RoundedCornerShape(50))
                .background(surface) // trilho suave
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(progress)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(50))
                    .background(primary) // barra principal
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewProgressBarBook() {
    ProgressBarBook(
        currentPage = 108,
        totalPages = 364
    )
}
