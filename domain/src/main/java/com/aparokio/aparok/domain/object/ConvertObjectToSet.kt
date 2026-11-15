package com.aparokio.aparok.domain.`object`

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository

class ConvertObjectToSet(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<ConvertObjectToSet.Params, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Params) =
        repo.objectToSet(
            ctx = params.ctx,
            source = params.sources
        )

    data class Params(
        val ctx: Id,
        val sources: List<String>
    )
}