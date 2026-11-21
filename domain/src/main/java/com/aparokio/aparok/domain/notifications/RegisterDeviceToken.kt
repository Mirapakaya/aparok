package com.aparokio.aparok.domain.notifications

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import javax.inject.Inject

class RegisterDeviceToken @Inject constructor(
    private val repository: AuthRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<RegisterDeviceToken.Params, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Params) {
        val command = Command.RegisterDeviceToken(
            token = params.token,
        )
        repository.registerDeviceToken(command = command)
    }

    data class Params(
        val token: String
    )
}