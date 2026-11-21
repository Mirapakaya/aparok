package com.aparokio.aparok.core_ui.features.editor.slash.holders

import androidx.recyclerview.widget.RecyclerView
import com.aparokio.aparok.core_ui.databinding.ItemSlashWidgetSelectDateBinding
import com.aparokio.aparok.core_models.ui.ObjectIcon

class DateSelectHolder(
    val binding: ItemSlashWidgetSelectDateBinding
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.ivIcon.setIcon(
            ObjectIcon.TypeIcon.Default.DATE
        )
    }
}