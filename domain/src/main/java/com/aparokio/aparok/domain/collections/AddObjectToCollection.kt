package com.aparokio.aparok.domain.collections

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class AddObjectToCollection @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<AddObjectToCollection.Params, Payload>(dispatchers.io) {

    override suspend fun doWork(params: Params): Payload {
        val command = Command.AddObjectToCollection(
            ctx = params.ctx,
            afterId = params.after,
            ids = params.targets
        )
        return repo.addObjectToCollection(command)
    }

    class Params(
        val ctx: Id,
        val targets: List<Id>,
        val after: Id = ""
    )
}