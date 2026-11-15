package com.aparokio.aparok.domain.publishing

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.core_models.publishing.Publishing
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class GetWebPublishingList @Inject constructor(
    private val repository: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<GetWebPublishingList.Params, List<Publishing.State>>(dispatchers.io) {

    override suspend fun doWork(params: Params): List<Publishing.State> {
        val command = Command.Publishing.GetList(
            space = params.space
        )
        return repository.publishingGetList(command)
    }

    data class Params(
        val space: SpaceId?
    )
}