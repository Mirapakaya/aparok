package com.aparokio.aparok.presentation.relations.providers

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Struct
import kotlinx.coroutines.flow.Flow

interface ObjectValueProvider {
    suspend fun get(ctx: Id, target: Id): Struct
    suspend fun subscribe(ctx: Id, target: Id) : Flow<Struct>
}