package com.aparokio.aparok.domain.relations

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Key
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository

/**
 * Use-case for adding a relation to an object.
 */
class AddRelationToObject(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<AddRelationToObject.Params, Payload?>(dispatchers.io) {

    override suspend fun doWork(params: Params): Payload? {
        return repo.addRelationToObject(
            ctx = params.ctx,
            relation = params.relationKey
        )
    }

    /**
     * @param ctx id of the object
     * @param relationKey relation key
     */
    class Params(
        val ctx: Id,
        val relationKey: Key
    )
}