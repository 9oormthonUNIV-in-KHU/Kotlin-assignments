package com.example.myapplication.navigate

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController, viewModel: ListViewModel) {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        viewModel.partList.forEach { part ->
            Button(
                onClick = { navController.navigate("part/$part") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(part)
            }
        }
    }
}