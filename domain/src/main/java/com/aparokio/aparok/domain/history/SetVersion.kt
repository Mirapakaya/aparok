package com.aparokio.aparok.domain.history

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class SetVersion @Inject constructor(
    dispatchers: AppCoroutineDispatchers,
    private val repo: BlockRepository
) : ResultInteractor<SetVersion.Params, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Params) {
        val command = Command.VersionHistory.SetVersion(
            objectId = params.objectId,
            versionId = params.versionId
        )
        repo.setVersion(command)
    }

    data class Params(
        val objectId: Id,
        val versionId: Id
    )
}