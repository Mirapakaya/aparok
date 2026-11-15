package com.aparokio.aparok.core_ui.features.editor.holders.upload

import com.aparokio.aparok.core_ui.R
import com.aparokio.aparok.core_ui.databinding.ItemBlockMediaPlaceholderBinding
import com.aparokio.aparok.core_ui.extensions.drawable
import com.aparokio.aparok.core_ui.features.editor.decoration.DecoratableCardViewHolder
import com.aparokio.aparok.presentation.editor.editor.listener.ListenerType
import com.aparokio.aparok.presentation.editor.editor.model.BlockView

class FileUpload(
    binding: ItemBlockMediaPlaceholderBinding
) : MediaUpload(binding), DecoratableCardViewHolder {

    override fun uploadClick(target: String, clicked: (ListenerType) -> Unit) {
        clicked(ListenerType.File.Upload(target))
    }

    override fun bind(item: BlockView.Upload, clicked: (ListenerType) -> Unit) {
        super.bind(item, clicked)
        binding.fileIcon.setImageDrawable(itemView.context.drawable(R.drawable.ic_file_light_inactive))
    }
}