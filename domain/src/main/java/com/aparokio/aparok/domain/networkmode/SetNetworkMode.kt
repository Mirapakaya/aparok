package com.aparokio.aparok.domain.networkmode

import com.aparokio.aparok.core_models.NetworkModeConfig
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import javax.inject.Inject

class SetNetworkMode @Inject constructor(
    private val repository: AuthRepository,
    dispatchers: AppCoroutineDispatchers,
) : ResultInteractor<SetNetworkMode.Params, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Params) {
        repository.setNetworkMode(params.networkModeConfig)
    }

    data class Params(val networkModeConfig: NetworkModeConfig)
}