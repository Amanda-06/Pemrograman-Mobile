package com.example.scrollablelistcompose2.feature.cat.data.model

import androidx.annotation.DrawableRes

data class Cat(
    val id: String,
    val name: String,
    val origin: String,
    val description: String,
    @DrawableRes val imageResId: Int,
    val wikiUrl: String
)