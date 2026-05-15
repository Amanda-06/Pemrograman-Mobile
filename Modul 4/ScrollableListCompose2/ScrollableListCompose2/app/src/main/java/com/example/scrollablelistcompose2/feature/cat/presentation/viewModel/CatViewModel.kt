package com.example.scrollablelistcompose2.feature.cat.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.scrollablelistcompose2.feature.cat.data.model.Cat
import com.example.scrollablelistcompose2.feature.cat.domain.CatInteractor
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

class CatViewModel(
    private val interactor: CatInteractor,
    private val title: String
) : ViewModel() {
    val cats: StateFlow<List<Cat>> = interactor.cats
    val selectedCat: StateFlow<Cat?> = interactor.selectedCat

    init {
        interactor.fetchCats()
        Timber.d("Log ViewModel: Inisialisasi layar $title")
    }

    fun onDetailClicked(cat: Cat) {
        interactor.selectCat(cat)
        Timber.d("Log ViewModel: Navigasi ke detail dipicu")
    }

    fun onWikiClicked() {
        Timber.d("Log ViewModel: Tombol Wiki (Explicit Intent) ditekan")
    }
}