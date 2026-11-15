package com.aparokio.aparok.core_ui.features.editor.holders.other

import android.widget.LinearLayout
import androidx.core.view.updateLayoutParams
import com.aparokio.aparok.core_ui.R
import com.aparokio.aparok.core_ui.databinding.ItemBlockUnsupportedBinding
import com.aparokio.aparok.core_ui.features.editor.BlockViewHolder
import com.aparokio.aparok.core_utils.ext.dimen
import com.aparokio.aparok.presentation.editor.editor.model.BlockView

class Unsupported(
    val binding: ItemBlockUnsupportedBinding
) : BlockViewHolder(binding.root), BlockViewHolder.IndentableHolder {
    fun bind(item: BlockView.Unsupported) {
        indentize(item)
        itemView.isSelected = item.isSelected
    }

    override fun indentize(item: BlockView.Indentable) {
        binding.indentator.updateLayoutParams<LinearLayout.LayoutParams> {
            width = item.indent * dimen(R.dimen.indent)
        }
    }
}