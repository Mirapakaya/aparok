package com.aparokio.aparok.domain.relations

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Key
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository

/**
 * Use-case for deleting a relation from an object.
 */
class DeleteRelationFromObject(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<DeleteRelationFromObject.Params, Payload>(dispatchers.io) {

    override suspend fun doWork(params: Params): Payload {
        return repo.deleteRelationFromObject(
            ctx = params.ctx,
            relations = params.relations
        )
    }

    /**
     * @param ctx id of the object
     * @param relations relation keys
     */
    class Params(
        val ctx: Id,
        val relations: List<Key>
    )
}