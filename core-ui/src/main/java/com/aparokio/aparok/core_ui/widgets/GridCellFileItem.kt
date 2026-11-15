package com.aparokio.aparok.core_ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.aparokio.aparok.core_ui.databinding.WidgetDvGridFileBinding
import com.aparokio.aparok.core_ui.extensions.getMimeIcon
import com.aparokio.aparok.core_utils.ext.gone
import com.aparokio.aparok.core_utils.ext.visible

class GridCellFileItem @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    val binding = WidgetDvGridFileBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun setup(name: String, mime: String?, extension: String?) = with(binding) {
        tvName.visible()
        tvName.text = name
        if (mime != null) {
            ivIcon.visible()
            val mimeIcon = mime.getMimeIcon(extension)
            ivIcon.setImageResource(mimeIcon)
        } else {
            ivIcon.gone()
        }
    }

    fun clear() {
        binding.tvName.text = null
        binding.tvName.gone()
        binding.ivIcon.gone()
    }
}