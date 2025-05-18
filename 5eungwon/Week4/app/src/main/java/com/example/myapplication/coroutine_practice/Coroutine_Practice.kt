package com.example.myapplication.coroutine_practice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Coroutine_Practice(modifier : Modifier = Modifier){

    var data by remember { mutableStateOf("데이터 요청 전") }
    val scope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .fillMaxHeight(0.15f)
        ){
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f),
                contentAlignment = Alignment.Center
            ){
                Text(if (isLoading) "데이터 요청중..." else data)
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f),
                contentAlignment = Alignment.Center

            ){
                Button(
                    onClick = {

                        scope.launch {
                            isLoading = true
                            delay(2000)
                            try {
                                val response = RetrofitClient.api.getMessage()
                                if (response.isSuccessful) {
                                    data = response.body()?.message ?: "응답 없음"
                                } else {
                                    data = "오류: ${response.code()}"
                                }
                            } catch (e: Exception) {
                                data = "예외 발생: ${e.message}"
                            }
                            isLoading = false
                        }

                    }
                ) { Text("요청")}
            }
        }

    }
}