package com.aparokio.aparok.core_ui.features.editor.holders.other

import android.view.View
import android.view.ViewConfiguration
import android.widget.FrameLayout
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.RecyclerView
import com.aparokio.aparok.core_ui.BuildConfig
import com.aparokio.aparok.core_ui.R
import com.aparokio.aparok.core_ui.extensions.setBlockBackgroundColor
import com.aparokio.aparok.core_ui.features.editor.BlockViewHolder
import com.aparokio.aparok.core_ui.features.editor.EditorTouchProcessor
import com.aparokio.aparok.core_ui.features.editor.SupportCustomTouchProcessor
import com.aparokio.aparok.core_utils.ext.dimen
import com.aparokio.aparok.core_models.ThemeColor
import com.aparokio.aparok.presentation.editor.editor.listener.ListenerType
import com.aparokio.aparok.presentation.editor.editor.model.BlockView

abstract class Divider(view: View) : BlockViewHolder(view),
    BlockViewHolder.IndentableHolder,
    BlockViewHolder.DragAndDropHolder,
    SupportCustomTouchProcessor {

    val root : View get() = itemView
    val divider: View get() = itemView.findViewById(R.id.divider)
    abstract val container: View

    override val editorTouchProcessor = EditorTouchProcessor(
        touchSlop = ViewConfiguration.get(itemView.context).scaledTouchSlop,
        fallback = { e -> itemView.onTouchEvent(e) }
    )

    init {
        itemView.setOnTouchListener { v, e -> editorTouchProcessor.process(v, e) }
    }

    fun bind(
        id: String,
        item: BlockView.Indentable,
        isItemSelected: Boolean,
        clicked: (ListenerType) -> Unit,
        background: ThemeColor
    ) = with(itemView) {
        container.isSelected = isItemSelected
        setOnClickListener { clicked(ListenerType.DividerClick(id)) }
    }

    @Deprecated("Pre-nested-styling legacy.")
    override fun indentize(item: BlockView.Indentable) {
        // Do nothing.
    }
}