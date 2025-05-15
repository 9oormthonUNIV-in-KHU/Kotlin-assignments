package com.example.kotlin_practice.memo_search

import android.R.attr.value
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kotlin_practice.viewmodel.MemoViewModel


@Composable
fun Memo(
    modifier: Modifier = Modifier,
    navController: NavController,
    label: String,
    viewModel: MemoViewModel = viewModel()
) {
    val memoList by viewModel.memoList.collectAsState()
    var text by remember { mutableStateOf("") }


    Column(modifier = modifier
        .padding(30.dp)
        .fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.15f)

        ){
            Box(modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.8f)
                .align(Alignment.Center) // 부모 기준 정렬
                .padding(16.dp)
            ){
                Text(
                    text = "MOBILE",
                    fontSize = 32.sp,
                    modifier = Modifier
                        .align(Alignment.Center) // 부모 기준 정렬
                )
            }
            Button(
                onClick = { viewModel.deleteChecked() },
                modifier = Modifier
                    .align(Alignment.BottomEnd) // 부모 기준 정렬
                    .padding(16.dp),

                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "휴지통",
                tint = Color.White // 아이콘 색상
            ) }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.75f)
                .background(color = Color.White)
                .border(2.dp, Color.Gray, shape = RoundedCornerShape(12.dp))

        ) {
            itemsIndexed(memoList) { index, item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .border(2.dp, Color.Gray, shape = RoundedCornerShape(12.dp))
                ) {
                    androidx.compose.material3.Checkbox(
                        checked = item.second,
                        onCheckedChange = { checked -> viewModel.updateMemo(index, checked) }
                    )
                    Box(
                        modifier = Modifier.clickable{
                            navController.navigate("search/${item.first}")
                        }
                    ){
                        Text(text = item.first)
                    }

                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
                .background(color = Color.White)
                .padding(10.dp)
        ) {
            TextField(
                value = text,
                onValueChange = {text = it},
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight(0.8f)
                    .border(2.dp, Color.Gray, shape = RoundedCornerShape(12.dp))
            )
            Button(
                onClick = {
                    if (text.isNotBlank()) {
                        viewModel.addMemo(text)
                        text = ""
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(5.dp)
            ) { Text("추가") }
        }
    }
}


