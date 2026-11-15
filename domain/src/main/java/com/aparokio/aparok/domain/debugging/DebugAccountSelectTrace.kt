package com.aparokio.aparok.domain.debugging

import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class DebugAccountSelectTrace @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<DebugAccountSelectTrace.Params, String>(dispatchers.io) {

    override suspend fun doWork(params: Params): String {
        return repo.debugAccountSelectTrace(params.dir)
    }

    data class Params(val dir: String)
}