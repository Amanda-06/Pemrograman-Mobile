package com.example.scrollablelistcompose2.feature.cat.data.repository

import com.example.scrollablelistcompose2.feature.cat.data.model.Cat
import com.example.scrollablelistcompose2.feature.cat.data.source.DummyCatData

class CatRepository {
    fun getCats(): List<Cat> {
        return DummyCatData.catList
    }
}