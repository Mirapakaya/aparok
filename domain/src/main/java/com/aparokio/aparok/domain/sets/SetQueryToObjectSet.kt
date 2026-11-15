package com.aparokio.aparok.domain.sets

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository

class SetQueryToObjectSet(private val repo: BlockRepository, dispatchers: AppCoroutineDispatchers) :
    ResultInteractor<SetQueryToObjectSet.Params, Payload>(dispatchers.io) {

    override suspend fun doWork(params: Params): Payload {
        val command = Command.SetQueryToSet(
            ctx = params.ctx,
            query = params.query
        )
        return repo.setQueryToSet(command)
    }

    class Params(
        val ctx: Id,
        val query: Id
    )
}