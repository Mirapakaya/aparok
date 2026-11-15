package com.aparokio.aparok.domain.debugging

import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.workspace.SpaceManager
import javax.inject.Inject

class DebugSpace @Inject constructor(
    private val repo: BlockRepository,
    private val spaceManager: SpaceManager,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<Unit, String>(dispatchers.io) {

    override suspend fun doWork(params: Unit): String = repo.debugSpace(
        space = spaceManager.get().let { id ->
            if (id.isEmpty())
                throw IllegalStateException("Space not found")
            else
                SpaceId(id)
        }
    )
}