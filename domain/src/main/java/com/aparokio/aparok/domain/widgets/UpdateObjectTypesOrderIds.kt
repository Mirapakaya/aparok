package com.aparokio.aparok.domain.widgets

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class UpdateObjectTypesOrderIds @Inject constructor(
    private val repository: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<UpdateObjectTypesOrderIds.Params, List<Id>>(dispatchers.io) {

    override suspend fun doWork(params: Params): List<Id> {
        val command = Command.ObjectTypesSetOrder(
            spaceId = params.spaceId.id,
            orderedIds = params.orderedIds
        )
        return repository.objectTypesSetOrder(command)
    }

    data class Params(
        val spaceId: SpaceId,
        val orderedIds: List<String>
    )
}