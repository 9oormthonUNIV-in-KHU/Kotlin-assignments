package com.example.myapplication.myprofile

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun Myprofile(modifier: Modifier = Modifier) {
    // 1) 상태 변수 선언
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var school by remember { mutableStateOf("") }
    var major by remember { mutableStateOf("") }

    // 2) 토스트를 띄우기 위한 context
    val context = LocalContext.current

    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(30.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White, shape = RoundedCornerShape(16.dp)),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 프로필 영역
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .background(Color.Blue, shape = RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("프로필 이미지", color = Color.White)
            }

            // 입력 Row
            ProfileInputRow(label = "이름", value = name, onValueChange = { name = it })
            ProfileInputRow(label = "나이", value = age, onValueChange = { age = it })
            ProfileInputRow(label = "학교", value = school, onValueChange = { school = it })
            ProfileInputRow(label = "학과", value = major, onValueChange = { major = it })

            Spacer(modifier = Modifier.height(50.dp))

            // 전송 버튼: 클릭 시 토스트로 네 값 출력
            Button(
                onClick = {
                    val message ="$name, $age, $school, $major"

                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                },
                modifier = Modifier
                    .width(150.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("전송", color = Color.White)
            }
        }
    }
}

@Composable
private fun ProfileInputRow(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .width(300.dp)
            .height(56.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, color = Color.Black, modifier = Modifier.width(60.dp))
        Spacer(modifier = Modifier.width(8.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text("$label 입력", color = Color.White.copy(alpha = 0.6f)) },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor   = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                disabledBorderColor  = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )
    }
}