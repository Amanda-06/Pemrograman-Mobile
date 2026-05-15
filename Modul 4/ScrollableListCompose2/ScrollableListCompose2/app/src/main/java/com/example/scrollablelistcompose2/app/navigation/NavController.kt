package com.example.scrollablelistcompose2.app.navigation

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.scrollablelistcompose2.feature.cat.presentation.screens.DetailScreen
import com.example.scrollablelistcompose2.feature.cat.presentation.screens.HomeScreen
import com.example.scrollablelistcompose2.feature.cat.presentation.viewModel.CatViewModel

@Composable
fun NavController(navController: NavHostController, viewModel: CatViewModel) {
    NavHost(
        navController = navController,
        startDestination = "home_screen",
        modifier = Modifier.systemBarsPadding()
    ) {
        composable("home_screen") {
            HomeScreen(navController = navController, viewModel = viewModel)
        }
        // Di dalam NavHost pada NavController.kt
        composable("detail_screen/{catId}") {
            DetailScreen(viewModel = viewModel)
        }
    }
}