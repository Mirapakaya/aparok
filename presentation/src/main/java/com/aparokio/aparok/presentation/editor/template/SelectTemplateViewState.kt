package com.aparokio.aparok.presentation.editor.template

import com.aparokio.aparok.core_models.Id

sealed class SelectTemplateViewState {
    object Idle : SelectTemplateViewState()
    data class Active(val count: Int, val typeId: Id) : SelectTemplateViewState()
}