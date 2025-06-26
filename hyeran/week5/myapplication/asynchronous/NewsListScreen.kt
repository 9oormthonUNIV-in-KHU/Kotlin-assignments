package com.example.myapplication.asynchronous

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.BuildConfig
import kotlinx.coroutines.launch

// NewsListScreen.kt
@Composable
fun NewsListScreen() {
    var articles by remember { mutableStateOf<List<Article>>(emptyList()) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            val apiKey = BuildConfig.NEWS_API_KEY
            val response = NewsRetrofitClient.api.getTopHeadlines(apiKey)
            if (response.isSuccessful) {
                articles = response.body()?.articles ?: emptyList()
                Log.d("NEWS_DEBUG", "Success: ${articles.size} articles")
            } else {
                error = "Error ${response.code()}"
                Log.e("NEWS_DEBUG", "Error response: ${response.code()}")
            }
        } catch (e: Exception) {
            error = "Exception: ${e.localizedMessage}"
            Log.e("NEWS_DEBUG", "Exception: ${e.localizedMessage}")
        }
    }

    if (error != null) {
        Text("에러: $error", modifier = Modifier.padding(16.dp))
    } else {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items = articles, key = { it.title }) { article: Article ->
                NewsItem(article)
            }
        }
    }
}

@Composable
fun NewsItem(article: Article) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)
    ) {
        article.urlToImage?.let { imageUrl ->
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )
        }
        Text(text = article.title, style = MaterialTheme.typography.titleMedium)
        article.description?.let {
            Text(text = it, style = MaterialTheme.typography.bodySmall)
        }
    }
}