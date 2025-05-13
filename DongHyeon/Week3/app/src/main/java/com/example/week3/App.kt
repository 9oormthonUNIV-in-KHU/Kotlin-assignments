package com.example.week3

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun App(viewModel: ListViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "MainLayout") {
        composable("MainLayout") {
            MainScreen(navController, viewModel)
        }
        composable(
            route = "PartLayout/{part}",
            arguments = listOf(navArgument("part") { type = NavType.StringType })
        ) {
            val part = it.arguments?.getString("part").toString()

            PartScreen(navController, part, viewModel)
        }
        composable(
            route = "DetailLayout/{text}",
            arguments = listOf(navArgument("text") { type = NavType.StringType })
        ) {
            val text = it.arguments?.getString("text").toString()

            DetailScreen(navController, text)
        }
    }
}