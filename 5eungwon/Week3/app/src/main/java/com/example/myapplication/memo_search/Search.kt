package com.example.myapplication.memo_search

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Search(modifier: Modifier = Modifier, navController: NavController, label: String){


    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(0.9f)
        ){
            Text(
                text = label,
                modifier = Modifier.align(Alignment.Center), // 텍스트를 박스의 가운데로 정렬
                color = Color.Black,
                fontSize = 30.sp
            )
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(0.1f)
        ){
            Button(
                modifier = Modifier.align(Alignment.Center),
                onClick = {
                    val searchUrl = "https://www.google.com/search?q=$label"
                    // 구글 검색 Intent를 실행
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(searchUrl))
                    context.startActivity(intent) // 현재 context에서 Intent 실행
                }
            ) {Text("찾기") }
        }
    }
}

