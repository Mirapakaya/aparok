package com.aparokio.aparok.domain.debugging

import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import javax.inject.Inject

class DebugExportLogs @Inject constructor(
    private val repo: AuthRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<DebugExportLogs.Params, String>(dispatchers.io) {

    override suspend fun doWork(params: Params): String {
        return repo.debugExportLogs(params.dir)
    }

    data class Params(val dir: String)
}