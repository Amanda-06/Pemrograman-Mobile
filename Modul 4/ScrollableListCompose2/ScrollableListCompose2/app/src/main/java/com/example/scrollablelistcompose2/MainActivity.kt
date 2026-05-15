package com.example.scrollablelistcompose2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.scrollablelistcompose2.app.navigation.NavController
import com.example.scrollablelistcompose2.feature.cat.data.repository.CatRepository
import com.example.scrollablelistcompose2.feature.cat.domain.CatInteractor
import com.example.scrollablelistcompose2.feature.cat.presentation.viewModel.CatViewModel
import com.example.scrollablelistcompose2.feature.cat.presentation.viewModel.CatViewModelFactory
import com.example.scrollablelistcompose2.ui.theme.LightMocha
import com.example.scrollablelistcompose2.ui.theme.ScrollableListCompose2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScrollableListCompose2Theme {
                Surface(color = LightMocha) {
                    val navController = rememberNavController()

                    val repository = CatRepository()
                    val catInteractor = CatInteractor(repository)
                    val catViewModel: CatViewModel = viewModel(
                        factory = CatViewModelFactory(catInteractor, "Koleksi Kucing")
                    )

                    NavController(navController = navController, viewModel = catViewModel)
                }
            }
        }
    }
}