package com.example.scrollablelistxml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.scrollablelistxml.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val catId = arguments?.getInt("catId") ?: 0
        val cat = CatDataSource.dummyCats.find { it.id == catId }

        cat?.let {
            binding.ivDetailImage.setImageResource(it.imageResId)
            binding.tvDetailTitle.text = it.name
            binding.tvDetailOrigin.text = "Asal: ${it.origin}"
            binding.tvDetailDesc.text = it.description
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}