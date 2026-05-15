package com.example.scrollablelistcompose2.feature.cat.domain

import com.example.scrollablelistcompose2.feature.cat.data.model.Cat
import com.example.scrollablelistcompose2.feature.cat.data.repository.CatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

class CatInteractor(private val repository: CatRepository) {

    private val _cats = MutableStateFlow<List<Cat>>(emptyList())
    val cats: StateFlow<List<Cat>> = _cats.asStateFlow()

    private val _selectedCat = MutableStateFlow<Cat?>(null)
    val selectedCat: StateFlow<Cat?> = _selectedCat.asStateFlow()

    fun fetchCats() {
        val data = repository.getCats()
        _cats.value = data
        Timber.d("Log Domain: Data kucing berhasil masuk ke StateFlow. Jumlah: ${data.size}")
    }

    fun selectCat(cat: Cat) {
        _selectedCat.value = cat
        Timber.d("Log Domain: Event onClick dideteksi untuk ${cat.name}")
    }
}