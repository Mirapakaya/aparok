package com.aparokio.aparok.core_ui.features.editor.holders.media

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewConfiguration
import com.aparokio.aparok.core_ui.extensions.setBlockBackgroundColor
import com.aparokio.aparok.core_ui.features.editor.BlockViewDiffUtil
import com.aparokio.aparok.core_ui.features.editor.BlockViewHolder
import com.aparokio.aparok.core_ui.features.editor.EditorTouchProcessor
import com.aparokio.aparok.core_ui.features.editor.SupportCustomTouchProcessor
import com.aparokio.aparok.core_models.ThemeColor
import com.aparokio.aparok.presentation.editor.editor.listener.ListenerType
import com.aparokio.aparok.presentation.editor.editor.model.BlockView

@SuppressLint("ClickableViewAccessibility")
abstract class Media(view: View) : BlockViewHolder(view),
    BlockViewHolder.IndentableHolder,
    BlockViewHolder.DragAndDropHolder,
    SupportCustomTouchProcessor {

    abstract val root: View
    abstract val clickContainer: View
    abstract val container: View
    abstract fun onMediaBlockClicked(item: BlockView.Media, clicked: (ListenerType) -> Unit)
    abstract override fun indentize(item: BlockView.Indentable)
    abstract fun select(isSelected: Boolean)

    override val editorTouchProcessor = EditorTouchProcessor(
        touchSlop = ViewConfiguration.get(itemView.context).scaledTouchSlop,
        fallback = { e -> clickContainer.onTouchEvent(e) }
    )

    fun bind(
        item: BlockView.Media,
        clicked: (ListenerType) -> Unit
    ) {
        indentize(item)
        select(item.isSelected)
        with(clickContainer) {
            setOnClickListener { onMediaBlockClicked(item, clicked) }
        }
    }

    open fun processChangePayload(payloads: List<BlockViewDiffUtil.Payload>, item: BlockView) {
        check(item is BlockView.Media) { "Expected a media, but was: $item" }
        payloads.forEach { payload ->
            if (payload.isSelectionChanged) {
                select(item.isSelected)
            }
        }
    }

    protected fun applyBackground(background: ThemeColor) {
        container.setBlockBackgroundColor(background)
    }
}