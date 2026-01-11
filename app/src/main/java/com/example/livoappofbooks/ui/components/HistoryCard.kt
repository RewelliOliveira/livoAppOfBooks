package com.example.livoappofbooks.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.livoappofbooks.ui.theme.*
import com.example.livoappofbooks.ui.theme.onBackground

@Composable
fun HistoryCard(
    title: String?,
    date: String,
    time: String,
    progressLabel: String,
    body: String?
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 140.dp)
            .border(
                width = 1.dp,
                color = outline,
                shape = RoundedCornerShape(14.dp)
            ),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = background
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {

        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(modifier = Modifier.weight(1f)) {
                    if (!title.isNullOrEmpty()) {
                        Text(
                            text = title,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = onBackground
                        )
                    } else {
                        Text(
                            text = "Progresso",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = onBackground
                        )
                    }
                }

                Text(
                    date,
                    fontSize = 13.sp,
                    color = onBackground
                )
            }

            HorizontalDivider(color = tertiary)

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.MenuBook,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = primary
                )
                Spacer(Modifier.width(6.dp))
                Text(progressLabel, fontSize = 14.sp)
            }

            if (!body.isNullOrEmpty()) {
                Text(
                    text = body,
                    fontSize = 14.sp,
                    color = onBackground
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = time,
                    fontSize = 12.sp,
                    color = onBackground
                )
            }
        }
    }
}
