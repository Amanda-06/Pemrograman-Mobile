package com.example.scrollablelistxml2.feature.cat.domain

import com.example.scrollablelistxml2.feature.cat.data.model.CatData
import com.example.scrollablelistxml2.feature.cat.data.repository.CatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

class CatInteractor(private val repository: CatRepository) {

    private val _catList = MutableStateFlow<List<CatData>>(emptyList())
    val catList: StateFlow<List<CatData>> = _catList.asStateFlow()

    private val _selectedCat = MutableStateFlow<CatData?>(null)
    val selectedCat: StateFlow<CatData?> = _selectedCat.asStateFlow()

    fun fetchCats() {
        val data = repository.getAllCats()
        _catList.value = data
        Timber.d("Log Domain: Data kucing berhasil dimuat. Jumlah: ${data.size}")
    }

    fun selectCat(cat: CatData) {
        _selectedCat.value = cat
        Timber.d("Log Domain: Kucing dipilih untuk detail: ${cat.name}")
    }
}