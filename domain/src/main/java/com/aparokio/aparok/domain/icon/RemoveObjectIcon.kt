package com.aparokio.aparok.domain.icon

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.icon.RemoveObjectIcon.Params

class RemoveObjectIcon(
    private val repository: BlockRepository,
    dispatchers: AppCoroutineDispatchers
): ResultInteractor<Params, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Params) {
        repository.removeDocumentIcon(ctx = params.objectId)
    }

    data class Params(val objectId: Id)
}