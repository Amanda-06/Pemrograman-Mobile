package com.example.scrollablelistxml2.feature.cat.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.scrollablelistxml2.databinding.ItemCatBinding
import com.example.scrollablelistxml2.feature.cat.data.model.CatData

class CatAdapter(
    private val onDetailClick: (CatData) -> Unit,
    private val onWikiClick: (CatData) -> Unit
) : ListAdapter<CatData, CatAdapter.CatViewHolder>(DiffCallback) {

    inner class CatViewHolder(val binding: ItemCatBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cat: CatData) {
            binding.ivCatImage.setImageResource(cat.imageResId)
            binding.tvCatName.text = cat.name
            binding.tvCatOrigin.text = cat.origin
            binding.tvCatDesc.text = cat.description

            binding.btnWiki.setOnClickListener {
                onWikiClick(cat)
            }

            binding.btnDetail.setOnClickListener {
                onDetailClick(cat)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val binding = ItemCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CatData>() {
        override fun areItemsTheSame(oldItem: CatData, newItem: CatData) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: CatData, newItem: CatData) = oldItem == newItem
    }
}