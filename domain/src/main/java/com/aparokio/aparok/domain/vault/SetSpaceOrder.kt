package com.aparokio.aparok.domain.vault

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class SetSpaceOrder @Inject constructor(
    private val repository: BlockRepository,
    dispatchers: AppCoroutineDispatchers
): ResultInteractor<SetSpaceOrder.Params, List<Id>>(dispatchers.io) {

    override suspend fun doWork(params: Params): List<Id> {
        return repository.spaceSetOrder(
            spaceViewId = params.spaceViewId,
            spaceViewOrder = params.spaceViewOrder
        )
    }

    data class Params(
        val spaceViewId: Id,
        val spaceViewOrder: List<Id>
    )
}