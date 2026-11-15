package com.aparokio.aparok.presentation.moving

import com.aparokio.aparok.presentation.navigation.DefaultObjectView

sealed class MoveToView {
    object Init : MoveToView()
    object Loading : MoveToView()
    object EmptyPages : MoveToView()
    data class Success(val objects: List<DefaultObjectView>) : MoveToView()
    data class NoResults(val searchText: String) : MoveToView()
    data class Error(val error: String) : MoveToView()
}