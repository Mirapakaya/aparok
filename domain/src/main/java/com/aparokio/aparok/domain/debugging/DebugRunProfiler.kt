package com.aparokio.aparok.domain.debugging

import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import javax.inject.Inject

class DebugRunProfiler @Inject constructor(
    private val repo: AuthRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<DebugRunProfiler.Params, String>(dispatchers.io) {

    override suspend fun doWork(params: Params): String {
        return repo.debugRunProfiler(params.durationInSeconds)
    }

    data class Params(val durationInSeconds: Int)
}
