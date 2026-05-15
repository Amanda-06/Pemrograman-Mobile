package com.example.scrollablelistxml2.feature.cat.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.scrollablelistxml2.feature.cat.data.model.CatData
import com.example.scrollablelistxml2.feature.cat.domain.CatInteractor
import timber.log.Timber

class CatViewModel(private val interactor: CatInteractor, private val title: String) : ViewModel() {

    val catList = interactor.catList
    val selectedCat = interactor.selectedCat

    init {
        interactor.fetchCats()
        Timber.d("Log ViewModel: $title diinisialisasi")
    }

    fun onDetailClicked(cat: CatData) {
        Timber.d("Log ViewModel: Navigasi ke detail dipicu untuk ${cat.name}")
        interactor.selectCat(cat)
    }

    fun onWikiClicked() {
        Timber.d("Log ViewModel: Tombol Wiki (Explicit Intent) ditekan")
    }
}