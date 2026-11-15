package com.aparokio.aparok.domain.relations

import com.aparokio.aparok.core_models.DVFilter
import com.aparokio.aparok.core_models.DVSort
import com.aparokio.aparok.core_models.Key
import com.aparokio.aparok.core_models.ObjectWrapper
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.block.repo.BlockRepository

class GetRelations(
    private val repo: BlockRepository
) : BaseUseCase<List<ObjectWrapper.Relation>, GetRelations.Params>() {

    override suspend fun run(params: Params) = safe { proceedWithUseCase(params) }

    suspend fun execute(params: Params) : List<ObjectWrapper.Relation> {
        return proceedWithUseCase(params)
    }

    private suspend fun proceedWithUseCase(params: Params) = repo.searchObjects(
        space = params.space,
        keys = params.keys,
        filters = params.filters,
        sorts = params.sorts,
        limit = params.limit,
        offset = params.offset,
        fulltext = params.query
    ).map { struct ->
        ObjectWrapper.Relation(struct)
    }

    data class Params(
        val space: SpaceId,
        val sorts: List<DVSort> = emptyList(),
        val filters: List<DVFilter> = emptyList(),
        val keys: List<Key> = emptyList(),
        val offset: Int = 0,
        val limit: Int = 0,
        val query: String = ""
    )
}