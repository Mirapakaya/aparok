package com.aparokio.aparok.domain.publishing

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.core_models.publishing.Publishing
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class GetPublishingState @Inject constructor(
    private val repository: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<GetPublishingState.Params, Publishing.State?>(dispatchers.io) {

    override suspend fun doWork(params: Params): Publishing.State? {
        val command = Command.Publishing.GetStatus(
            space = params.space,
            objectId = params.objectId
        )
        return repository.publishingGetStatus(command)
    }

    data class Params(
        val space: SpaceId,
        val objectId: Id
    )
}
