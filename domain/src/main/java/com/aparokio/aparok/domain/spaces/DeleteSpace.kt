package com.aparokio.aparok.domain.spaces

import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class DeleteSpace @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<SpaceId, Unit>(dispatchers.io)  {

    override suspend fun doWork(params: SpaceId) {
        repo.deleteSpace(space = params)
    }
}