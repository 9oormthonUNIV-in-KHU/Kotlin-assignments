package com.example.week5.data.api

import com.example.week5.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "kr",
        @Query("apiKey") apiKey: String
    ): NewsResponse
}