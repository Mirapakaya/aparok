package com.aparokio.aparok.presentation.editor

import com.aparokio.aparok.core_models.Id

sealed class Snack {
    data class UndoRedo(val message: String) : Snack()
}