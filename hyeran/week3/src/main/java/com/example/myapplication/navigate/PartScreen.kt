package com.example.myapplication.navigate

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PartScreen(navController: NavController, part: String, viewModel: ListViewModel) {
    val context = LocalContext.current
    var text by remember { mutableStateOf("") }
    val todoList by viewModel.getListFlow(part).collectAsState()
    var selectedIndices by remember { mutableStateOf(setOf<Int>()) }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(part, style = MaterialTheme.typography.titleLarge)
            Button(onClick = {
                viewModel.deleteChecked(part)
                selectedIndices = emptySet()
            }) {
                Text("삭제")
            }
        }

        LazyColumn(Modifier.weight(1f)) {
            itemsIndexed(todoList) { index, item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate("detail/${item.second}") }
                        .padding(8.dp)
                ) {
                    Checkbox(
                        checked = item.first,
                        onCheckedChange = {
                            viewModel.toggleCheck(part, index)
                        }
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(item.second)
                }
            }
        }

        Row(Modifier.fillMaxWidth()) {
            TextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    viewModel.addToList(part, text)
                    text = ""
                },
                enabled = text.isNotBlank(),
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("추가")
            }
        }

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("뒤로가기")
        }
    }
}