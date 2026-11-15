package com.aparokio.aparok.domain.workspace

import com.aparokio.aparok.core_models.FileLimitsEvent
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import kotlinx.coroutines.flow.Flow

class InterceptFileLimitEvents(
    private val fileLimitsEventChannel: FileLimitsEventChannel,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<Unit, Flow<List<FileLimitsEvent>>>(dispatchers.io) {

    override suspend fun doWork(params: Unit): Flow<List<FileLimitsEvent>> {
        return fileLimitsEventChannel.observe()
    }
}