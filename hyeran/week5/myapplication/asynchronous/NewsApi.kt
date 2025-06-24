package com.example.myapplication.asynchronous

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines?country=us")
    suspend fun getTopHeadlines(
        @Query("apiKey") apiKey: String
    ): Response<NewsResponse>
}

data class NewsResponse(
    val articles: List<Article>
)

data class Article(
    val title: String,
    val description: String?,
    val urlToImage: String?
)

object NewsRetrofitClient {
    val api: NewsApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }
}