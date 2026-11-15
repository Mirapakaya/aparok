package com.aparokio.aparok.domain.`object`

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.InternalFlags
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository

class SetObjectInternalFlags(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<SetObjectInternalFlags.Params, Payload>(dispatchers.io) {

    override suspend fun doWork(params: Params): Payload {
        val command = Command.SetInternalFlags(
            ctx = params.ctx,
            flags = params.flags
        )
        return repo.setInternalFlags(command)
    }

    data class Params(
        val ctx: Id,
        val flags: List<InternalFlags>
    )
}