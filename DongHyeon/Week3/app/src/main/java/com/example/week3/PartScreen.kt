package com.example.week3

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun PartScreen(
    navController: NavController,
    part: String,
    viewModel: ListViewModel
) {
    val context = LocalContext.current
    val todoList = when (part) {
        "DevRel" -> viewModel.devrelList.collectAsState().value
        "FRONTEND" -> viewModel.frontendList.collectAsState().value
        "BACKEND" -> viewModel.backendList.collectAsState().value
        "AI" -> viewModel.aiList.collectAsState().value
        "MOBILE" -> viewModel.mobileList.collectAsState().value
        else -> emptyList()
    }
    var todoCheckedList by remember { mutableStateOf(listOf<Int>()) }
    var text by remember { mutableStateOf("") }
    var isButton by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 40.dp, top = 20.dp, end = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = part,
                modifier = Modifier.align(Alignment.TopCenter),
                style = TextStyle(fontSize = 30.sp),
            )

            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "ArrowBack"
                )
            }

            IconButton(
                onClick = {
                    if (todoCheckedList.isNotEmpty()) {
                        val newTodoList = todoList.filter { !it.first }
                        deleteDialog(context) {
                            viewModel.filterList(part, newTodoList)
                            todoCheckedList = emptyList()
                        }
                    } else {
                        Toast.makeText(context, "선택한 항목이 없습니다.", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(top = 20.dp, end = 10.dp, bottom = 10.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    Color(100, 60, 180, 255)
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "",
                    modifier = Modifier.align(Alignment.Center),
                    tint = Color.White
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .border(1.dp, Color.Gray, RoundedCornerShape(16.dp))
                .padding(10.dp)
        ) {
            itemsIndexed(todoList) { index, todo ->
                Row(
                    modifier = Modifier.padding(end = 20.dp)
                ) {
                    Checkbox(
                        checked = todo.first,
                        onCheckedChange = {
                            if (!todo.first) todoCheckedList += index
                            else todoCheckedList -= index
                            viewModel.updateTodoItem(part, index)
                        }
                    )
                    Text(
                        text = todo.second,
                        modifier = Modifier
                            .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                            .fillMaxSize()
                            .padding(10.dp)
                            .clickable { navController.navigate("DetailLayout/${todo.second}") },
                        style = TextStyle(fontSize = 20.sp)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        Row(
            modifier = Modifier.padding(top = 10.dp, bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val keyboardController = LocalSoftwareKeyboardController.current

            BasicTextField(
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, Color.Black, RoundedCornerShape(16.dp))
                    .padding(10.dp),
                value = text,
                onValueChange = {
                    if (it.length <= 10) {
                        text = it
                        isButton = it.isNotEmpty()
                    }
                },
                textStyle = TextStyle(fontSize = 20.sp),
                maxLines = 1,
                singleLine = true,
                decorationBox = {
                    if (text.isEmpty())
                        Text(
                            text = "내용을 입력하세요",
                            style = TextStyle(fontSize = 20.sp),
                            color = Color.Gray,
                        )
                    it()
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        viewModel.addToList(part, text)
                        text = ""
                        isButton = false
                        keyboardController?.hide()
                    }
                )
            )

            Button(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 5.dp),
                onClick = {
                    viewModel.addToList(part, text)
                    text = ""
                    isButton = false
                    keyboardController?.hide()
                },
                enabled = isButton
            ) {
                Text(text = "추가")
            }
        }
    }
}

fun deleteDialog(context: Context, onPositiveButton: () -> Unit) {
    val builder = AlertDialog.Builder(context)
    builder.setTitle("삭제 확인")
        .setMessage("선택하신 항목을 삭제 하시겠습니까?")
        .setPositiveButton("확인") { _, _ -> onPositiveButton() }
        .setNegativeButton("취소") { dialog, _ -> dialog.dismiss() }
        .show()
}

@Preview(showBackground = true)
@Composable
fun PreviewPart() {
    PartScreen(rememberNavController(), "part", viewModel())
}