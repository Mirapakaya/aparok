package com.aparokio.aparok.domain.auth.interactor

import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import javax.inject.Inject

class AppShutdown @Inject constructor(
    dispatchers: AppCoroutineDispatchers,
    private val repository: AuthRepository
) : ResultInteractor<Unit, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Unit) {
        repository.appShutdown()
    }
}