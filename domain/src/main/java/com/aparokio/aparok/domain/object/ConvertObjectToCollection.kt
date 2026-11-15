package com.aparokio.aparok.domain.`object`

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository

class ConvertObjectToCollection(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<ConvertObjectToCollection.Params, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Params) = repo.objectToCollection(ctx = params.ctx)

    data class Params(val ctx: Id)
}