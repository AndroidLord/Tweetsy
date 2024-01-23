package com.example.tweetsy

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tweetsy.api.TweetsyAPI
import com.example.tweetsy.screens.CategoryScreen
import com.example.tweetsy.screens.DetailScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = "Tweetsy")},
                        backgroundColor = Color.Black,
                        contentColor = Color.White
                    )
                }
            ) {
                Box(modifier = Modifier.padding(it)){
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {

    val NavController = rememberNavController()
    NavHost(navController = NavController, startDestination = "category") {
        composable(route = "category") {
            CategoryScreen(){
                NavController.navigate("detail/${it}")
            }
        }
        composable(
            route = "detail/{category}",
            arguments = listOf(
                navArgument("category") { type = NavType.StringType }
            )
        ) {
            val category = it.arguments?.getString("category")
            DetailScreen(category!!)
        }
    }


}
