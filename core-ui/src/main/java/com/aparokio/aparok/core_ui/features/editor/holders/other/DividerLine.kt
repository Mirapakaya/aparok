package com.aparokio.aparok.core_ui.features.editor.holders.other

import android.view.View
import android.widget.FrameLayout
import androidx.core.view.updateLayoutParams
import com.aparokio.aparok.core_ui.BuildConfig
import com.aparokio.aparok.core_ui.R
import com.aparokio.aparok.core_ui.databinding.ItemBlockDividerLineBinding
import com.aparokio.aparok.core_ui.features.editor.decoration.DecoratableViewHolder
import com.aparokio.aparok.core_utils.ext.dimen
import com.aparokio.aparok.presentation.editor.editor.listener.ListenerType
import com.aparokio.aparok.presentation.editor.editor.model.BlockView

class DividerLine(
    binding: ItemBlockDividerLineBinding
) : Divider(binding.root), DecoratableViewHolder {

    override val decoratableContainer = binding.decorationContainer
    override val container: View = binding.container

    fun bind(item: BlockView.DividerLine, clicked: (ListenerType) -> Unit) {
        super.bind(
            id = item.id,
            item = item,
            isItemSelected = item.isSelected,
            clicked = clicked,
            background = item.background
        )
    }

    override fun applyDecorations(decorations: List<BlockView.Decoration>) {
        decoratableContainer.decorate(decorations) { rect ->
            val defaultIndentOffset = dimen(R.dimen.default_indent)
            container.updateLayoutParams<FrameLayout.LayoutParams> {
                marginStart = rect.left + defaultIndentOffset
                marginEnd = rect.right + defaultIndentOffset
                bottomMargin = rect.bottom + dimen(R.dimen.divider_extra_space_bottom)
            }
        }
    }
}