package com.aparokio.aparok.core_ui.features.dataview.modals

import android.widget.ImageView
import android.widget.TextView
import com.aparokio.aparok.core_ui.R
import com.aparokio.aparok.core_ui.databinding.ItemDvViewerFilterObjectBinding
import com.aparokio.aparok.core_ui.widgets.ObjectIconWidget
import com.aparokio.aparok.core_ui.widgets.RelationFormatIconWidget
import com.aparokio.aparok.core_utils.ext.gone
import com.aparokio.aparok.core_utils.ext.visible
import com.aparokio.aparok.presentation.extension.hasValue
import com.aparokio.aparok.core_models.ui.ObjectIcon
import com.aparokio.aparok.presentation.sets.model.FilterView
import com.aparokio.aparok.presentation.sets.model.ObjectView

class FilterObjectViewHolder(val binding: ItemDvViewerFilterObjectBinding) :
    FilterViewHolder(binding.root) {

    override val textTitle: TextView get() = binding.tvTitle
    override val textCondition: TextView get() = binding.tvCondition
    override val iconFormat: RelationFormatIconWidget get() = binding.iconFormat
    override val iconArrow: ImageView get() = binding.iconArrow
    override val iconRemove: ImageView get() = binding.iconRemoveObject

    private val objectIcon: ObjectIconWidget get() = binding.objectIcon
    private val number: TextView get() = binding.number
    private val objectName: TextView get() = binding.objectName

    fun bind(item: FilterView.Expression.Object) = with(itemView) {
        setup(
            relationKey = item.relation,
            isEditMode = item.isInEditMode,
            title = item.title,
            condition = item.condition.title,
            format = item.format
        )

        if (item.condition.hasValue() && item.filterValue.value.isNotEmpty()) {
            item.filterValue.value.forEachIndexed { index, objectView ->
                if (objectView is ObjectView.Default) {
                    if (index == 0) {
                        objectName.text = objectView.name.ifBlank {
                            context.resources.getString(R.string.untitled)
                        }
                        when (objectView.icon) {
                            ObjectIcon.None -> objectIcon.gone()
                            else -> {
                                objectIcon.visible()
                                objectIcon.setIcon(objectView.icon)
                            }
                        }
                    }
                }
            }
            val valuesSize = item.filterValue.value.size

            if (valuesSize > MAX_ITEMS) {
                number.visible()
                number.text = "+${valuesSize - MAX_ITEMS}"
            } else {
                number.gone()
            }
        } else {
            objectIcon.gone()
            number.gone()
            objectName.text = null
        }
    }

    companion object {
        private const val MAX_ITEMS = 1
    }
}