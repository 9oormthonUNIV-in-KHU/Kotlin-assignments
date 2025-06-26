package com.example.week5.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week5.network.RetrofitInstance
import com.example.week5.network.model.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _articles = MutableStateFlow(emptyList<Article>())
    val articles: StateFlow<List<Article>> = _articles

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    fun fetchNews(apiKey: String) {
        viewModelScope.launch {
            _isRefreshing.value = true
            try {
                val response = RetrofitInstance.api.getTopHeadlines("us", apiKey)
                _articles.value = response.articles
            } catch (e: Exception) {
                _errorMessage.value = e.message.toString()
            }
        }
        _isRefreshing.value = false
        _isLoading.value = false
    }
}