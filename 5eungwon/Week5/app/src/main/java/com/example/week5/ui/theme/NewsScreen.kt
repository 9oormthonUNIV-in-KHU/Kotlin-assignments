// ui/NewsScreen.kt
package com.example.week5.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.week5.viewmodel.NewsViewModel

@Composable
fun NewsScreen(viewModel: NewsViewModel = viewModel()) {
    val newsList = viewModel.newsList.collectAsState().value

    if (newsList.isEmpty()) {
        // 데이터가 없을 경우 안내 메시지 출력
        androidx.compose.material3.Text("뉴스를 불러오는 중이거나 결과가 없습니다.")
    } else {
        LazyColumn {
            items(newsList) { article ->
                NewsCard(article)
            }
        }
    }
}
