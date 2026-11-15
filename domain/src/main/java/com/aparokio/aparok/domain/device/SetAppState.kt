package com.aparokio.aparok.domain.device

import com.aparokio.aparok.core_models.AppState
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class SetAppState @Inject constructor(
    private val repository: BlockRepository,
    dispatchers: AppCoroutineDispatchers,
) : ResultInteractor<SetAppState.Params, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Params) {
        repository.setAppState(params.state)
    }

    data class Params(val state: AppState)
} 