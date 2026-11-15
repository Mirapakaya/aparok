package com.aparokio.aparok.presentation.templates

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.ObjectType

sealed interface TemplateSelectView {
    data class Blank(
        val typeId: Id,
        val typeName: String = "",
        val layout: Int,
    ) : TemplateSelectView

    data class Template(
        val id: Id,
        val space: Id,
        val typeId: Id,
        val typeKey: Id,
        val layout: ObjectType.Layout,
    ) : TemplateSelectView
}