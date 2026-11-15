package com.aparokio.aparok.domain.block.interactor

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Key
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository

class SetObjectType(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<SetObjectType.Params, Payload>(dispatchers.io) {

    override suspend fun doWork(params: Params): Payload {
        return repo.setObjectTypeToObject(
            ctx = params.context,
            objectTypeKey = params.objectTypeKey
        )
    }

    class Params(
        val context: Id,
        val objectTypeKey: Key
    )
}