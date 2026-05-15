package com.example.scrollablelistcompose2.feature.cat.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.scrollablelistcompose2.feature.cat.domain.CatInteractor

class CatViewModelFactory(
    private val interactor: CatInteractor,
    private val title: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CatViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CatViewModel(interactor, title) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}