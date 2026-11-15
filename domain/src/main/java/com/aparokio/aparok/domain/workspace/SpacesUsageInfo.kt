package com.aparokio.aparok.domain.workspace

import com.aparokio.aparok.core_models.NodeUsageInfo
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository

class SpacesUsageInfo(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<Unit, NodeUsageInfo>(dispatchers.io) {

    override suspend fun doWork(params: Unit): NodeUsageInfo {
        return repo.nodeUsage()
    }
}