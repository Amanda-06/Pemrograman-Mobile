package com.example.scrollablelistxml2.feature.cat.presentation.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scrollablelistxml2.R
import com.example.scrollablelistxml2.databinding.FragmentHomeBinding
import com.example.scrollablelistxml2.feature.cat.data.repository.CatRepository
import com.example.scrollablelistxml2.feature.cat.domain.CatInteractor
import com.example.scrollablelistxml2.feature.cat.presentation.adapters.CatAdapter
import com.example.scrollablelistxml2.feature.cat.presentation.viewModel.CatViewModel
import com.example.scrollablelistxml2.feature.cat.presentation.viewModel.CatViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: CatViewModel by activityViewModels {
        CatViewModelFactory(CatInteractor(CatRepository()), "Daftar Kucing")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)

        val onDetailAction: (com.example.scrollablelistxml2.feature.cat.data.model.CatData) -> Unit = { cat ->
            viewModel.onDetailClicked(cat)
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
        }

        val onWikiAction: (com.example.scrollablelistxml2.feature.cat.data.model.CatData) -> Unit = { cat ->
            viewModel.onWikiClicked()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(cat.wikiUrl))
            startActivity(intent)
        }

        val featuredAdapter = CatAdapter(onDetailClick = onDetailAction, onWikiClick = onWikiAction)
        featuredAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        binding.rvFeaturedCats.apply {
            adapter = featuredAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        val allCatsAdapter = CatAdapter(onDetailClick = onDetailAction, onWikiClick = onWikiAction)
        allCatsAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        binding.rvAllCats.apply {
            adapter = allCatsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            isNestedScrollingEnabled = false
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.catList.collectLatest { cats ->
                    if (cats.isNotEmpty()) {
                        featuredAdapter.submitList(cats)
                        allCatsAdapter.submitList(cats)
                    }
                }
            }
        }
    }
}