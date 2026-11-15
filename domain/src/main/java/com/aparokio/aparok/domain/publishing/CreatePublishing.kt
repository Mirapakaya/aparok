package com.aparokio.aparok.domain.publishing

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class CreatePublishing @Inject constructor(
    private val repository: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<CreatePublishing.Params, String>(dispatchers.io) {

    override suspend fun doWork(params: Params): String {
        val command = Command.Publishing.Create(
            space = params.space,
            objectId = params.objectId,
            uri = params.uri,
            showJoinSpaceBanner = params.showJoinSpaceBanner
        )
        return repository.publishingCreate(command)
    }

    data class Params(
        val space: SpaceId,
        val objectId: Id,
        val uri: String,
        val showJoinSpaceBanner: Boolean = false
    )
}
