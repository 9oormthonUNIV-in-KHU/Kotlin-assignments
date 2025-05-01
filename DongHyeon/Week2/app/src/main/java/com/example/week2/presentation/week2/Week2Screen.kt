package com.example.week2.presentation.week2

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.week2.R
import com.example.week2.presentation.week2.component.InfoRow

@Composable
fun Week2Screen() {
    var isRotated by remember { mutableStateOf(true) }
    val rotation by animateFloatAsState(
        targetValue = if (isRotated) 0f else 180f,
        animationSpec = tween(durationMillis = 1000), label = ""
    )
    val infoList = listOf("이름", "나이", "학교", "별명", "mbti")
    val context = LocalContext.current
    var infoTexts by remember { mutableStateOf(List(infoList.size) { "" }) }

    Box(modifier = Modifier.fillMaxSize()) {
        Card(
            onClick = { isRotated = !isRotated },
            modifier = Modifier
                .fillMaxSize(0.8f)
                .align(Alignment.Center)
                .shadow(
                    if (2f < rotation && rotation < 178f) 0.dp else 10.dp,
                    RoundedCornerShape(16.dp)
                )
                .graphicsLayer {
                    rotationY = rotation
                    cameraDistance = 16 * density
                }
        ) {
            if (rotation < 90f) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "클릭하여 자세히 보기",
                        color = Color.Gray,
                    )
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 30.dp)
                        .graphicsLayer { rotationY = 180f },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_launcher_foreground),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .background(Color.Blue)
                    )

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
                    ) {
                        infoList.forEachIndexed { index, info ->
                            InfoRow(info) {
                                infoTexts = infoTexts.toMutableList().apply { this[index] = it }
                            }
                        }
                    }

                    Button(
                        onClick = {
                            Toast.makeText(
                                context,
                                infoTexts.joinToString(", "),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    ) {
                        Text(text = "저장")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Week2Preview() {
    Week2Screen()
}