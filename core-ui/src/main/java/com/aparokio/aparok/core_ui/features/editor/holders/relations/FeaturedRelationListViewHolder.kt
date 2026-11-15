package com.aparokio.aparok.core_ui.features.editor.holders.relations

import com.aparokio.aparok.core_ui.R
import com.aparokio.aparok.core_ui.databinding.ItemBlockFeaturedRelationsBinding
import com.aparokio.aparok.core_ui.features.editor.BlockViewHolder
import com.aparokio.aparok.core_utils.ext.dimen
import com.aparokio.aparok.presentation.editor.editor.listener.ListenerType
import com.aparokio.aparok.presentation.editor.editor.model.BlockView

class FeaturedRelationListViewHolder(
    val binding: ItemBlockFeaturedRelationsBinding
) : BlockViewHolder(binding.root) {

    private val root = binding.featuredRelationRoot

    fun bind(item: BlockView.FeaturedRelation, click: (ListenerType) -> Unit) {
        if (item.isTodoLayout) {
            val lr = itemView.context.dimen(R.dimen.dp_60).toInt()
            binding.featuredRelationRoot.setPadding(lr, 0, 0, 0)
        } else {
            val lr = itemView.context.dimen(R.dimen.dp_20).toInt()
            binding.featuredRelationRoot.setPadding(lr, 0, 0, 0)
        }
        root.set(item, click)
    }
}