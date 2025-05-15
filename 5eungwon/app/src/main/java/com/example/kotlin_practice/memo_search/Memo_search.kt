package com.example.kotlin_practice.memo_search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


val items = listOf("DevRel", "Frontend", "Backend", "AI", "Mobile")

@Composable
fun Memo_search(modifier: Modifier = Modifier, navController: NavController){


    Column(modifier = modifier
        .padding(30.dp)
        .background(Color.White,shape = RoundedCornerShape(16.dp),)
        .fillMaxSize()
        .border(2.dp, Color.Gray, shape = RoundedCornerShape(12.dp))
        )
    {
        items.forEach { label ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clickable{
                        navController.navigate("memo/${label}")
                    }
                    .padding(vertical = 8.dp,horizontal = 12.dp)
                    .border(2.dp, Color.Gray, shape = RoundedCornerShape(12.dp))
                    .background(Color.White, shape = RoundedCornerShape(12.dp))
            ) {
                Text(
                    text = label,
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Black
                )
            }
        }
    }
}