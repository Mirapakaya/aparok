package com.aparokio.aparok.domain.`object`

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_models.Struct
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class SetObjectDetails @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<SetObjectDetails.Params, Payload>(dispatchers.io) {

    override suspend fun doWork(params: Params): Payload = repo.setObjectDetails(
        ctx = params.ctx,
        details = params.details
    )

    data class Params(
        val ctx: Id,
        val details: Struct
    )
}