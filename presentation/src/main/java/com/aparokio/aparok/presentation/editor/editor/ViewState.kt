package com.aparokio.aparok.presentation.editor.editor

import com.aparokio.aparok.presentation.editor.editor.model.BlockView

sealed class ViewState {
    object Loading : ViewState()
    object NotExist : ViewState()
    data class Success(val blocks: List<BlockView>) : ViewState()
}