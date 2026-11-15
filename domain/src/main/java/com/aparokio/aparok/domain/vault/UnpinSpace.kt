package com.aparokio.aparok.domain.vault

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class UnpinSpace @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<UnpinSpace.Params, Unit>(dispatchers.io) {

    data class Params(
        val spaceId: Id
    )

    override suspend fun doWork(params: Params): Unit {
        repo.spaceUnsetOrder(
            spaceViewId = params.spaceId
        )
    }
} 