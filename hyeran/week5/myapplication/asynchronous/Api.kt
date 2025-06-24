package com.example.myapplication.asynchronous

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("getdata")
    suspend fun getMessage(): Response<MessageResponse>
}

data class MessageResponse(val message: String)

object RetrofitClient {
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}