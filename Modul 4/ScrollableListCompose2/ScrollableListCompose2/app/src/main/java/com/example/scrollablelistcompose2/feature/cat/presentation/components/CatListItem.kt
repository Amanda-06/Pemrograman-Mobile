package com.example.scrollablelistcompose2.feature.cat.presentation.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scrollablelistcompose2.R
import com.example.scrollablelistcompose2.feature.cat.data.model.Cat
import com.example.scrollablelistcompose2.ui.theme.DarkMocha
import androidx.core.net.toUri

@Composable
fun CatListItem(
    cat: Cat,
    onDetailClick: () -> Unit,
    onWikiClick: () -> Unit,
    modifier: Modifier = Modifier
) {
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
                modifier = Modifier.size(110.dp, 140.dp).clip(RoundedCornerShape(12.dp))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = cat.name, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = DarkMocha)
                Text(text = cat.description, color = Color.Black, maxLines = 3, overflow = TextOverflow.Ellipsis)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    OutlinedButton(onClick = {
                        onWikiClick()
                        context.startActivity(Intent(Intent.ACTION_VIEW, cat.wikiUrl.toUri()))
                    }) { Text(stringResource(id = R.string.btn_wiki)) }
                    Spacer(modifier = Modifier.width(6.dp))
                    Button(onClick = onDetailClick, colors = ButtonDefaults.buttonColors(containerColor = DarkMocha)) {
                        Text(stringResource(id = R.string.btn_detail))
                    }
                }
            }
        }
    }
}