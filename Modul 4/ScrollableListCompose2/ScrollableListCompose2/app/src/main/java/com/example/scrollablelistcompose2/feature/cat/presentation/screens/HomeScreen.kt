package com.example.scrollablelistcompose2.feature.cat.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.scrollablelistcompose2.R
import com.example.scrollablelistcompose2.feature.cat.presentation.components.CatListItem
import com.example.scrollablelistcompose2.feature.cat.presentation.viewModel.CatViewModel
import com.example.scrollablelistcompose2.ui.theme.DarkMocha

@Composable
fun HomeScreen(navController: NavController, viewModel: CatViewModel) {
    val cats by viewModel.cats.collectAsState()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val cardWidth = screenWidth - 32.dp

    LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(vertical = 16.dp)) {
        item {
            Text(
                text = stringResource(id = R.string.kucing_spesial),
                fontWeight = FontWeight.Bold, fontSize = 22.sp, color = DarkMocha,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 8.dp)
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(cats) { cat ->
                    CatListItem(
                        cat = cat,
                        onDetailClick = {
                            viewModel.onDetailClicked(cat)
                            navController.navigate("detail_screen/${cat.id}")
                        },
                        onWikiClick = { viewModel.onWikiClicked() },
                        modifier = Modifier.width(cardWidth)
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Semua Ras Kucing",
                fontWeight = FontWeight.Bold, fontSize = 22.sp, color = DarkMocha,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 8.dp)
            )
        }

        items(cats) { cat ->
            CatListItem(
                cat = cat,
                onDetailClick = {
                    viewModel.onDetailClicked(cat)
                    navController.navigate("detail_screen/${cat.id}")
                },
                onWikiClick = { viewModel.onWikiClicked() },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )
        }
    }
}