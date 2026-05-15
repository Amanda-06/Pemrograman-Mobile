package com.example.scrollablelistxml2.feature.cat.data.source

import com.example.scrollablelistxml2.R
import com.example.scrollablelistxml2.feature.cat.data.model.CatData

object DummyCatData {
    val dummyCats = listOf(
        CatData(1, "Persian", "Iran", "Kucing berbulu panjang dengan wajah bulat dan hidung pesek yang menggemaskan.", R.drawable.cat1, "https://id.wikipedia.org/wiki/Kucing_persia"),
        CatData(2, "Maine Coon", "Amerika", "Salah satu ras kucing domestik terbesar yang pandai berburu.", R.drawable.cat2, "https://id.wikipedia.org/wiki/Maine_Coon"),
        CatData(3, "Sphynx", "Kanada", "Kucing unik tanpa bulu yang hangat, sangat penyayang dan aktif.", R.drawable.cat3, "https://id.wikipedia.org/wiki/Kucing_sfing"),
        CatData(4, "Siamese", "Thailand", "Kucing elegan bermata biru dengan pola warna bulu yang khas.", R.drawable.cat4, "https://id.wikipedia.org/wiki/Kucing_siam"),
        CatData(5, "Scottish Fold", "Skotlandia", "Dikenal karena telinganya yang melipat ke depan, terlihat seperti burung hantu.", R.drawable.cat5, "https://id.wikipedia.org/wiki/Scottish_Fold")
    )
}