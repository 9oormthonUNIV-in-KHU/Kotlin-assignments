package com.example.week5.data.repository

import com.example.week5.data.api.NewsApiService
import com.example.week5.data.model.NewsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsRepository {
    private val api: NewsApiService

    init {
        api = Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }

    suspend fun getNews(apiKey: String): NewsResponse {
        val response = api.getTopHeadlines(country = "us", apiKey = apiKey)
        println("✅ API 응답 개수: ${response.articles.size}")
        return response
    }
}