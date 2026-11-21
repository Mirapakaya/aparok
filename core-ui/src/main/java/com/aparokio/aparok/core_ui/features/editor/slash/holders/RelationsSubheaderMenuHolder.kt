package com.aparokio.aparok.core_ui.features.editor.slash.holders

import androidx.recyclerview.widget.RecyclerView
import com.aparokio.aparok.core_ui.R
import com.aparokio.aparok.core_ui.databinding.ItemSlashWidgetSubheaderBinding
import com.aparokio.aparok.core_ui.databinding.ItemSlashWidgetSubheaderLeftBinding
import com.aparokio.aparok.core_utils.ext.invisible
import com.aparokio.aparok.core_utils.ext.visible
import com.aparokio.aparok.presentation.editor.editor.slash.SlashPropertyView

class RelationsSubheaderMenuHolder(
    val binding: ItemSlashWidgetSubheaderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SlashPropertyView.Section) = with(binding) {
        val text = when (item) {
            SlashPropertyView.Section.Subheader -> {
                flBack.invisible()
                R.string.slash_widget_main_relations
            }
            SlashPropertyView.Section.SubheaderWithBack -> {
                flBack.visible()
                R.string.slash_widget_main_relations
            }
        }
        subheader.setText(text)
    }
}

class RelationsSubheaderOnlyMenuHolder(
    val binding: ItemSlashWidgetSubheaderLeftBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind() = with(binding) {
        subheader.setText(R.string.slash_widget_main_relations)
    }
}