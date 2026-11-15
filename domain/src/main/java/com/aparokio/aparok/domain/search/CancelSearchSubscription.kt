package com.aparokio.aparok.domain.search

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.objects.ObjectStore

class CancelSearchSubscription(
    private val repo: BlockRepository,
    private val store: ObjectStore
) : BaseUseCase<Unit, CancelSearchSubscription.Params>() {

    override suspend fun run(params: Params) = safe {
        repo.cancelObjectSearchSubscription(params.subscriptions)
        store.unsubscribe(params.subscriptions)
    }

    class Params(val subscriptions: List<Id>)
}