package com.aparokio.aparok.domain.history

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.history.Version
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class GetVersions @Inject constructor(
    dispatchers: AppCoroutineDispatchers,
    private val repo: BlockRepository
) : ResultInteractor<GetVersions.Params, List<Version>>(dispatchers.io) {

    override suspend fun doWork(params: Params): List<Version> {
        val command = Command.VersionHistory.GetVersions(
            objectId = params.objectId,
            lastVersion = params.lastVersion,
            limit = params.limit
        )
        return repo.getVersions(command)
    }

    data class Params(
        val objectId: Id,
        val lastVersion: Id? = null,
        val limit: Int = 300
    )
}