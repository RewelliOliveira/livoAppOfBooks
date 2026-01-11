package com.example.livoappofbooks.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.livoappofbooks.R
import com.example.livoappofbooks.data.model.Book
import com.example.livoappofbooks.ui.theme.AppTypography
import androidx.compose.ui.text.style.TextAlign

@Composable
fun PopularBookItem(
    book: Book,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .width(100.dp)
            .clickable { onClick(book.id) },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        AsyncImage(
            model = book.thumbnail ?: "",
            placeholder = androidx.compose.ui.res.painterResource(id = R.drawable.capa_default),
            contentDescription = book.title,
            contentScale = ContentScale.Crop,            modifier = Modifier
                .width(100.dp)
                .height(160.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Text(
            text = book.title,
            style = AppTypography.bodyMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}
