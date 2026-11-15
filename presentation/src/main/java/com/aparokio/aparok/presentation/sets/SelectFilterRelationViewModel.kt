package com.aparokio.aparok.presentation.sets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.presentation.sets.state.ObjectState
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

class SelectFilterRelationViewModel(
    private val objectState: StateFlow<ObjectState>,
    private val storeOfRelations: StoreOfRelations
) : SearchRelationViewModel(
    objectState = objectState,
    storeOfRelations = storeOfRelations
) {

    init {
        Timber.d("SelectFilterRelationViewModel, init")
    }

    class Factory(
        private val objectState: StateFlow<ObjectState>,
        private val storeOfRelations: StoreOfRelations
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SelectFilterRelationViewModel(
                objectState = objectState,
                storeOfRelations = storeOfRelations
            ) as T
        }
    }
}