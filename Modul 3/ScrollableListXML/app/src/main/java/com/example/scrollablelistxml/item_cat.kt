package com.example.scrollablelistxml

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scrollablelistxml.databinding.ItemCatBinding

class CatAdapter(
    private val catList: List<CatBreed>,
    private val onDetailClick: (Int) -> Unit
) : RecyclerView.Adapter<CatAdapter.CatViewHolder>() {

    inner class CatViewHolder(val binding: ItemCatBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val binding = ItemCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val cat = catList[position]
        with(holder.binding) {
            ivCatImage.setImageResource(cat.imageResId)
            tvCatName.text = cat.name
            tvCatOrigin.text = cat.origin
            tvCatDesc.text = cat.description

            btnWiki.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(cat.wikiUrl))
                holder.itemView.context.startActivity(intent)
            }

            btnDetail.setOnClickListener {
                onDetailClick(cat.id)
            }
        }
    }

    override fun getItemCount(): Int = catList.size
}