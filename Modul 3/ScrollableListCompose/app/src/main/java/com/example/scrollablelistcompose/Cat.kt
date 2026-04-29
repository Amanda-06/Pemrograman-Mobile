package com.example.scrollablelistcompose

import androidx.annotation.DrawableRes

data class Cat(
    val id: String,
    val name: String,
    val origin: String,
    val description: String,
    @DrawableRes val imageResId: Int,
    val wikiUrl: String
)

val catList = listOf(
    Cat("1", "Persian", "Iran", "Kucing berbulu panjang dengan wajah bulat dan moncong pendek yang menawan.", R.drawable.cat1, "https://id.wikipedia.org/wiki/Kucing_persia"),
    Cat("2", "Maine Coon", "Amerika", "Salah satu ras terbesar, sangat jinak, dan sering disebut 'raksasa lembut'.", R.drawable.cat2, "https://id.wikipedia.org/wiki/Maine_Coon"),
    Cat("3", "Sphynx", "Kanada", "Dikenal karena tidak memiliki bulu lebat, terlihat sangat unik dan elegan.", R.drawable.cat3, "https://id.wikipedia.org/wiki/Kucing_sfinks"),
    Cat("4", "Siamese", "Thailand", "Kucing elegan bermata biru dengan pola warna bulu yang khas.", R.drawable.cat4, "https://id.wikipedia.org/wiki/Kucing_siam"),
    Cat("5", "Scottish Fold", "Skotlandia", "Dikenal dengan telinganya yang melipat ke depan, wajahnya seperti burung hantu.", R.drawable.cat5, "https://id.wikipedia.org/wiki/Kucing_lipat_skotlandia")
)