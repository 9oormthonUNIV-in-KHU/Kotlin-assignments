// viewmodel/NewsViewModel.kt
package com.example.week5.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week5.data.model.NewsArticle
import com.example.week5.data.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val repository = NewsRepository()
    private val _newsList = MutableStateFlow<List<NewsArticle>>(emptyList())
    val newsList: StateFlow<List<NewsArticle>> = _newsList

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            try {
                val response = repository.getNews("6805dde942d14ea4aa16f9242f8904fe")
                _newsList.value = response.articles
            } catch (e: Exception) {
                e.printStackTrace()
                println("🔥 뉴스 불러오기 실패: ${e.message}")
            }
        }
    }
}
