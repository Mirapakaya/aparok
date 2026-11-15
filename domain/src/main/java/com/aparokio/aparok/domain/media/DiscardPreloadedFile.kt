package com.aparokio.aparok.domain.media

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

/**
 * Discards preloaded file by ID.
 */
class DiscardPreloadedFile @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<Id, Unit>(dispatchers.io) {
    override suspend fun doWork(params: Id) {
        repo.discardPreloadedFile(
            command = Command.DiscardPreloadedFile(params)
        )
    }
}