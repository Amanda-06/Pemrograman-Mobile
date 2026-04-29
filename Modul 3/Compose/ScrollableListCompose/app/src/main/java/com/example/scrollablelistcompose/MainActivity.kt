package com.example.scrollablelistcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scrollablelistcompose.ui.theme.LightMocha
import com.example.scrollablelistcompose.ui.theme.ScrollableListComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScrollableListComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = LightMocha
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "home_screen", modifier = Modifier.systemBarsPadding()) {

                        composable("home_screen") {
                            HomeScreen(navController = navController)
                        }

                        composable("detail_screen/{catId}") { backStackEntry ->
                            val catId = backStackEntry.arguments?.getString("catId")
                            DetailScreen(catId = catId)
                        }

                    }
                }
            }
        }
    }
}