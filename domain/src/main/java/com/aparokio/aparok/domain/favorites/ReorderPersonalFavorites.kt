package com.aparokio.aparok.domain.favorites

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import javax.inject.Inject

class ReorderPersonalFavorites @Inject constructor(
    private val repo: PersonalFavoritesRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<ReorderPersonalFavorites.Params, List<Payload>>(dispatchers.io) {

    data class Params(
        val space: SpaceId,
        val order: List<Id>
    )

    override suspend fun doWork(params: Params): List<Payload> =
        repo.reorder(params.space, params.order)
}
