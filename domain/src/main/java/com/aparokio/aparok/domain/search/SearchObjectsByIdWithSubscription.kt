package com.aparokio.aparok.domain.search

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.SearchResult
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.ResultatInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository

class SearchObjectsByIdWithSubscription(
    private val repo: BlockRepository
) : ResultatInteractor<SearchObjectsByIdWithSubscription.Params, SearchResult>() {

    override suspend fun execute(params: Params): SearchResult {
        return repo.searchObjectsByIdWithSubscription(
            space = params.space,
            subscription = params.subscription,
            ids = params.ids,
            keys = params.keys
        )
    }

    class Params(
        val space: SpaceId,
        val subscription: Id,
        val ids: List<Id>,
        val keys: List<String>
    )
}