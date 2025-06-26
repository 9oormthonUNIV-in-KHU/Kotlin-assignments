package com.example.myapplication.asynchronous

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.ui.unit.dp

@Composable
fun Coroutine(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf("idle") }
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(result)
            Button(onClick = {
                scope.launch {
                    result = "loading"
                    delay(2000)
                    try {
                        val response = RetrofitClient.api.getMessage()
                        result = if (response.isSuccessful) {
                            response.body()?.message ?: "no response"
                        } else {
                            "error: ${response.code()}"
                        }
                    } catch (e: Exception) {
                        result = "exception: ${e.message}"
                    }
                }
            }) {
                Text("Start Coroutine")
            }
        }
    }
}