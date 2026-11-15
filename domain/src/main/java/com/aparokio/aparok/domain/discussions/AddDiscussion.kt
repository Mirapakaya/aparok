package com.aparokio.aparok.domain.discussions

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class AddDiscussion @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<Id, Id>(dispatchers.io) {

    override suspend fun doWork(params: Id): Id {
        return repo.addDiscussion(params)
    }
}
