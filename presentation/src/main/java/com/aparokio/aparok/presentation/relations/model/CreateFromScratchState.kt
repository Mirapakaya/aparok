package com.aparokio.aparok.presentation.relations.model

import com.aparokio.aparok.core_models.RelationFormat
import kotlinx.coroutines.flow.MutableStateFlow

data class CreateFromScratchState(
    val format: RelationFormat,
    val limitObjectTypes: List<DefaultObjectTypeView>
)

class StateHolder<T>(initial: T) {
    val state = MutableStateFlow(initial)
}