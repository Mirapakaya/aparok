package com.aparokio.aparok.core_ui.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.aparokio.aparok.core_models.RelationFormat
import com.aparokio.aparok.core_ui.R
import com.aparokio.aparok.core_ui.extensions.simpleIcon

class RelationFormatIconWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatImageView(context, attrs) {

    fun bind(format: RelationFormat, isName: Boolean = false) {
        if (isName) {
            setImageResource(R.drawable.ic_relation_name)
        } else {
            setImageResource(format.simpleIcon())
        }
    }
}