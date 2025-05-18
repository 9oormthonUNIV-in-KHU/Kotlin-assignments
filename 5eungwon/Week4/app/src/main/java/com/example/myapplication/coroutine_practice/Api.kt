package com.example.myapplication.coroutine_practice

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
            .baseUrl("http://121.136.146.225:3000/") // 실제 주소로 변경
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}