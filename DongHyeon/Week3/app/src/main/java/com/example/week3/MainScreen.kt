package com.example.week3

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: ListViewModel
) {
    val backStackEntry = navController.currentBackStackEntry
    val partList = viewModel.partList.collectAsState()

    LaunchedEffect(backStackEntry) {
        viewModel.refreshListBoolean()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(16.dp))
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        partList.value.forEach {
            ListRow(it, Modifier.weight(1f)) { part ->
                navController.navigate("PartLayout/$part")
            }
        }
    }
}

@Composable
fun ListRow(
    part: String,
    modifier: Modifier,
    onPartScreen: (String) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
            .border(1.dp, Color.Black, RoundedCornerShape(16.dp))
            .padding(10.dp)
            .clickable { onPartScreen(part) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = part,
            style = TextStyle(fontSize = 15.sp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMain() {
    MainScreen(rememberNavController(), viewModel())
}