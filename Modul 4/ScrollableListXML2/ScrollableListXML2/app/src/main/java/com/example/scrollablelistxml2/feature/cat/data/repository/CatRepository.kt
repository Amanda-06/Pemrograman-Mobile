package com.example.scrollablelistxml2.feature.cat.data.repository

import com.example.scrollablelistxml2.feature.cat.data.model.CatData
import com.example.scrollablelistxml2.feature.cat.data.source.DummyCatData

class CatRepository {
    fun getAllCats(): List<CatData> = DummyCatData.dummyCats
}