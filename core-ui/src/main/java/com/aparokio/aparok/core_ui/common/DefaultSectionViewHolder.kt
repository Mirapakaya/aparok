package com.aparokio.aparok.core_ui.common

import androidx.recyclerview.widget.RecyclerView
import com.aparokio.aparok.core_ui.databinding.ItemDefaultListSectionBinding

class DefaultSectionViewHolder(
    val binding: ItemDefaultListSectionBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(name: String) {
        binding.tvSectionName.text = name
    }
}