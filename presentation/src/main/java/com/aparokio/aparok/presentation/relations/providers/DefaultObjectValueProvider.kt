package com.aparokio.aparok.presentation.relations.providers

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Struct
import com.aparokio.aparok.presentation.editor.editor.Store
import kotlin.collections.orEmpty
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultObjectValueProvider(
    private val details: Store.Details
) : ObjectValueProvider {
    override suspend fun get(ctx: Id, target: Id): Struct {
        return details.current().details[target].orEmpty()
    }

    override suspend fun subscribe(ctx: Id, target: Id): Flow<Struct> {
        return details.stream().map { details ->
            details.details[target].orEmpty()
        }
    }
}