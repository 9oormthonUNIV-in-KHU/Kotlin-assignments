package com.example.week5.data.model

import com.google.gson.annotations.SerializedName

data class NewsArticle(
    val title: String?,
    val description: String?,
    @SerializedName("urlToImage") val imageUrl: String?
)