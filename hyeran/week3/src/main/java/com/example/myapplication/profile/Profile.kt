package com.example.myapplication.profile

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun Profile(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var school by remember { mutableStateOf("") }
    var major by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(Color.Blue),
            contentAlignment = Alignment.Center
        ) {
            Text("프로필 이미지", color = Color.White)
        }

        ProfileInputField("이름", name) { name = it }
        ProfileInputField("나이", age) { age = it }
        ProfileInputField("학교", school) { school = it }
        ProfileInputField("학과", major) { major = it }

        Button(
            onClick = {
            Toast.makeText(context, "$name, $age, $school, $major", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text("저장")
        }
    }
}

@Composable
fun ProfileInputField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth()
    )
}