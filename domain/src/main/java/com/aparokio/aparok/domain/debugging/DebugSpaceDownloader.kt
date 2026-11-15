package com.aparokio.aparok.domain.debugging

import com.aparokio.aparok.core_models.Filepath
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import javax.inject.Inject

class DebugSpaceShareDownloader @Inject constructor(
    private val debugSpace: DebugSpace,
    private val debugSpaceContentSaver: DebugSpaceContentSaver,
    dispatchers: AppCoroutineDispatchers,
) : ResultInteractor<Unit, Filepath>(dispatchers.io) {
    override suspend fun doWork(params: Unit): Filepath {
        val content = debugSpace.run(Unit)
        val file = debugSpaceContentSaver.save(content = content)
        return file.path
    }
}