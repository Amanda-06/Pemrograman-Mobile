package com.example.scrollablelistxml2.feature.cat.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.scrollablelistxml2.R
import com.example.scrollablelistxml2.databinding.FragmentDetailBinding
import com.example.scrollablelistxml2.feature.cat.presentation.viewModel.CatViewModel
import kotlinx.coroutines.flow.collectLatest

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val viewModel: CatViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.selectedCat.collectLatest { cat ->
                cat?.let {
                    binding.ivDetailImage.setImageResource(it.imageResId)
                    binding.tvDetailName.text = it.name
                    binding.tvDetailOrigin.text = getString(R.string.asal, it.origin)
                    binding.tvDetailDescription.text = it.description
                }
            }
        }
    }
}