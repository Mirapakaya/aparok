package com.aparokio.aparok.presentation.templates

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.ObjectType
import com.aparokio.aparok.core_models.ObjectWrapper
import com.aparokio.aparok.core_models.Url
import com.aparokio.aparok.core_models.primitives.TypeId
import com.aparokio.aparok.core_models.primitives.TypeKey
import com.aparokio.aparok.presentation.editor.cover.CoverColor
import com.aparokio.aparok.core_models.ui.ObjectIcon

sealed class TemplateView {

    abstract val isDefault: Boolean
    abstract val targetTypeId: TypeId
    abstract val targetTypeKey: TypeKey

    data class Blank(
        val id: Id,
        override val targetTypeId: TypeId,
        override val targetTypeKey: TypeKey,
        val typeName: String = "",
        val layout: Int,
        override val isDefault: Boolean = false
    ) : TemplateView()

    data class New(
        override val targetTypeId: TypeId,
        override val targetTypeKey: TypeKey,
    ) : TemplateView() {
        override val isDefault: Boolean = false
    }

    data class Template(
        val id: Id,
        val name: String,
        override val targetTypeId: TypeId,
        override val targetTypeKey: TypeKey,
        val layout: ObjectType.Layout = ObjectType.Layout.BASIC,
        val emoji: String? = null,
        val image: String? = null,
        val coverColor: CoverColor? = null,
        val coverImage: Url? = null,
        val coverGradient: String? = null,
        override val isDefault: Boolean = false
    ) : TemplateView() {

        fun isCoverPresent(): Boolean {
            return coverColor != null || coverImage != null || coverGradient != null
        }

        fun isImageOrEmojiPresent(): Boolean {
            return image != null || emoji != null
        }
    }

    companion object {
        const val DEFAULT_TEMPLATE_ID_BLANK = "blank"
    }
}

sealed class TemplateMenuClick {
    data class Default(val template: Id) : TemplateMenuClick()
    data class Edit(val template: Id) : TemplateMenuClick()
    data class Duplicate(val template: Id) : TemplateMenuClick()
    data class Delete(val template: Id) : TemplateMenuClick()
}

sealed class TemplateObjectTypeView {

    data class Item(
        val type: ObjectWrapper.Type,
        val icon: ObjectIcon,
        val isSelected: Boolean = false
    ) : TemplateObjectTypeView()

    object Search : TemplateObjectTypeView()
}