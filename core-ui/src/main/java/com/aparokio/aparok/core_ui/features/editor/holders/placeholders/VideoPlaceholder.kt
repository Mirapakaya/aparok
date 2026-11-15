package com.aparokio.aparok.core_ui.features.editor.holders.placeholders

import com.aparokio.aparok.core_ui.R
import com.aparokio.aparok.core_ui.databinding.ItemBlockMediaPlaceholderBinding
import com.aparokio.aparok.core_ui.extensions.drawable
import com.aparokio.aparok.presentation.editor.editor.listener.ListenerType

class VideoPlaceholder(binding: ItemBlockMediaPlaceholderBinding) : MediaPlaceholder(binding) {

    override fun placeholderClick(target: String, clicked: (ListenerType) -> Unit) {
        clicked(ListenerType.Video.Placeholder(target))
    }

    override fun setup() {
        title.text = itemView.resources.getString(R.string.hint_upload_video)
        binding.fileIcon.setImageDrawable(itemView.context.drawable(R.drawable.ic_video))
    }
}