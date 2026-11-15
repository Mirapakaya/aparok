package com.aparokio.aparok.core_ui.features.editor.holders.other

import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.aparokio.aparok.core_ui.BuildConfig
import com.aparokio.aparok.core_ui.R
import com.aparokio.aparok.core_ui.databinding.ItemBlockObjectLinkLoadingBinding
import com.aparokio.aparok.core_ui.features.editor.BlockViewDiffUtil
import com.aparokio.aparok.core_ui.features.editor.BlockViewHolder
import com.aparokio.aparok.core_utils.ext.dimen
import com.aparokio.aparok.presentation.editor.editor.listener.ListenerType
import com.aparokio.aparok.presentation.editor.editor.model.BlockView

class LinkToObjectLoading(binding: ItemBlockObjectLinkLoadingBinding) :
    BlockViewHolder(binding.root),
    BlockViewHolder.IndentableHolder {

    private val container = binding.container

    fun bind(
        item: BlockView.LinkToObject.Loading,
        clicked: (ListenerType) -> Unit
    ) {

        applySelectedState(item)

        itemView.setOnClickListener { clicked(ListenerType.LinkToObjectLoading(item.id)) }
    }

    private fun applySelectedState(item: BlockView.LinkToObject.Loading) {
        container.isSelected = item.isSelected
    }

    fun processChangePayload(payloads: List<BlockViewDiffUtil.Payload>, item: BlockView) {
        check(item is BlockView.LinkToObject.Loading) { "Expected a link to object block, but was: $item" }
        payloads.forEach { payload ->
            if (payload.isSelectionChanged) {
                applySelectedState(item)
            }
        }
    }

    @Deprecated("Pre-nested-styling legacy.")
    override fun indentize(item: BlockView.Indentable) {
        // Do nothing.
    }
}