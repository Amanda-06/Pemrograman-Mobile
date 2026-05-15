package com.example.scrollablelistcompose2.feature.cat.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scrollablelistcompose2.feature.cat.presentation.viewModel.CatViewModel
import com.example.scrollablelistcompose2.ui.theme.DarkMocha

@Composable
fun DetailScreen(viewModel: CatViewModel) {
    val cat by viewModel.selectedCat.collectAsState()

    cat?.let { dataKucing ->
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Image(
                painter = painterResource(id = dataKucing.imageResId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().height(300.dp).clip(RoundedCornerShape(16.dp))
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = dataKucing.name, fontWeight = FontWeight.Bold, fontSize = 28.sp, color = DarkMocha)
            Text(text = "Asal: ${dataKucing.origin}", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = DarkMocha)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = dataKucing.description, fontSize = 16.sp)
        }
    }
}