package com.example.livoappofbooks.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.livoappofbooks.ui.theme.*

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit = {},
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .height(41.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(BackgroundLight)
            .border(
                width = 2.dp,
                color = PrincipalColor,
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = query,
                onValueChange = onQueryChange,
                textStyle = TextStyle(
                    color = Black,
                    fontSize = 16.sp
                ),
                cursorBrush = SolidColor(PrincipalColor),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (query.isEmpty()) {
                            Text(
                                text = "Pesquisar na minha biblioteca",
                                style = TextStyle(
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 10.sp,
                                    lineHeight = 10.sp,
                                    color = Black.copy(alpha = 0.6f)
                                )
                            )
                        }
                        innerTextField()
                    }
                }
            )

            Spacer(modifier = Modifier.width(12.dp))

            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Pesquisar",
                tint = PrincipalColor,
                modifier = Modifier.size(28.dp).clickable { onSearch() }
            )
        }
    }
}