package com.example.week2.presentation.week2.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoRow(infoValue: String, infoText: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = infoValue,
            fontSize = 20.sp
        )
        Spacer(Modifier.width(10.dp))
        BasicTextField(
            value = text,
            onValueChange = {
                if (it.length <= 10) {
                    text = it
                    infoText(it)
                }
            },
            textStyle = TextStyle(fontSize = 18.sp),
            decorationBox = { innerTextField ->
                if (text.isEmpty()) {
                    Text(
                        text = "${infoValue}을(를) 입력하세요.",
                        style = TextStyle(fontSize = 18.sp),
                        color = Color.Gray
                    )
                }
                innerTextField()
            },
            singleLine = true,
            maxLines = 1,
        )
    }
}