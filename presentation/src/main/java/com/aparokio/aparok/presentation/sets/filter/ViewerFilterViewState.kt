package com.aparokio.aparok.presentation.sets.filter

import com.aparokio.aparok.presentation.sets.model.FilterView

sealed class ViewerFilterViewState {

    object Init : ViewerFilterViewState()
    object NoFields : ViewerFilterViewState()
    data class Success(val items: List<FilterView>) : ViewerFilterViewState()
    data class Error(val msg: String) : ViewerFilterViewState()
}