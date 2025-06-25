// ui/NewsCard.kt
package com.example.week5.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.week5.data.model.NewsArticle

@Composable
fun NewsCard(article: NewsArticle) {
    Column(modifier = Modifier.padding(16.dp)) {
        article.imageUrl?.let {
            AsyncImage(
                model = it,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Text(
            text = article.title ?: "(제목 없음)",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = article.description ?: "(내용 없음)",
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}

