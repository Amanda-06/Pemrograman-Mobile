package com.example.scrollablelistcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scrollablelistcompose.ui.theme.DarkMocha

@Composable
fun DetailScreen(catId: String?) {
    val cat = catList.find { it.id == catId }

    if (cat != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = cat.imageResId),
                contentDescription = stringResource(id = R.string.desc_gambar_full_kucing, cat.name),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = cat.name,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = DarkMocha
            )

            Text(
                text = stringResource(id = R.string.label_asal_kucing, cat.origin),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = DarkMocha
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = cat.description,
                fontSize = 16.sp
            )
        }
    }
}