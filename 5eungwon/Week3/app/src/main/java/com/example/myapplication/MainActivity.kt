package com.example.kotlin_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.memo_search.Memo
import com.example.myapplication.memo_search.Memo_search
import com.example.myapplication.memo_search.Search
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // ✅ NavHost는 Scaffold 안에서만 사용
                    NavHost(
                        navController = navController,
                        startDestination = "memo_search",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("memo_search") {
                            Memo_search(navController = navController)
                        }
                        composable("memo/{label}") { backStackEntry ->
                            val label = backStackEntry.arguments?.getString("label") ?: ""
                            Memo(navController = navController,label=label)
                        }
                        composable("search/{label}") { backStackEntry ->
                            val label = backStackEntry.arguments?.getString("label") ?: ""
                            Search(navController = navController,label=label)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}