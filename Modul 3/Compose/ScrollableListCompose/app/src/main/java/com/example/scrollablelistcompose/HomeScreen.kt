package com.example.scrollablelistcompose

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.scrollablelistcompose.ui.theme.DarkMocha

@Composable
fun HomeScreen(navController: NavController) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val exactCardWidth = screenWidth - 32.dp

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {

        item {
            Text(
                text = stringResource(id = R.string.kucing_spesial),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = DarkMocha,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 8.dp)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(catList) { cat ->
                    CatItem(
                        cat = cat,
                        navController = navController,
                        modifier = Modifier.width(exactCardWidth)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(id = R.string.semua_ras),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = DarkMocha,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 8.dp)
            )
        }

        items(catList) { cat ->
            CatItem(
                cat = cat,
                navController = navController,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun CatItem(cat: Cat, navController: NavController, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = modifier.padding(vertical = 8.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            Image(
                painter = painterResource(id = cat.imageResId),
                contentDescription = stringResource(id = R.string.desc_gambar_kucing, cat.name),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(110.dp)
                    .height(140.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = cat.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = DarkMocha,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = cat.origin,
                        fontSize = 14.sp,
                        color = DarkMocha,
                        maxLines = 1
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = cat.description,
                    color = Color.Black,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    OutlinedButton(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(cat.wikiUrl))
                            context.startActivity(intent)
                        },
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = DarkMocha),
                        border = androidx.compose.foundation.BorderStroke(1.dp, DarkMocha),
                        shape = RoundedCornerShape(50.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp)
                    ) {
                        Text(stringResource(id = R.string.btn_wiki))
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Button(
                        onClick = {
                            navController.navigate("detail_screen/${cat.id}")
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = DarkMocha),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp)
                    ) {
                        Text(stringResource(id = R.string.btn_detail))
                    }
                }
            }
        }
    }
}