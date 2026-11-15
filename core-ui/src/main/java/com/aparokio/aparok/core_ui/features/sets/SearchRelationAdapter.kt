package com.aparokio.aparok.core_ui.features.sets

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aparokio.aparok.core_ui.common.AbstractAdapter
import com.aparokio.aparok.core_ui.common.AbstractViewHolder
import com.aparokio.aparok.core_ui.databinding.ItemSearchRelationBinding
import com.aparokio.aparok.core_ui.extensions.icon
import com.aparokio.aparok.presentation.sets.model.SimpleRelationView

class SearchRelationAdapter(
    private val onRelationClicked: (SimpleRelationView) -> Unit
) : AbstractAdapter<SimpleRelationView>(emptyList()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        binding = ItemSearchRelationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    ).apply {
        itemView.setOnClickListener { onRelationClicked(items[bindingAdapterPosition]) }
    }

    class ViewHolder(
        val binding: ItemSearchRelationBinding
    ) : AbstractViewHolder<SimpleRelationView>(binding.root) {
        override fun bind(item: SimpleRelationView) = with(binding) {
            val icon: Int? = item.icon()
            if (icon != null) {
                ivRelation.setImageResource(icon)
            } else {
                ivRelation.setImageDrawable(null)
            }
            tvRelationName.text = item.title
        }
    }
}