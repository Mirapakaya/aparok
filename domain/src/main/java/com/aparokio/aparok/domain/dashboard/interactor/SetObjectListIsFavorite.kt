package com.aparokio.aparok.domain.dashboard.interactor

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class SetObjectListIsFavorite @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<SetObjectListIsFavorite.Params, Unit>(dispatchers.io) {

    data class Params(
        val objectIds: List<Id>,
        val isFavorite: Boolean
    )

    override suspend fun doWork(params: Params) =
        repo.setObjectListIsFavorite(params.objectIds, params.isFavorite)
}