package com.aparokio.aparok.domain.auth.interactor

import com.aparokio.aparok.core_models.Account
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import javax.inject.Inject

class GetAccount @Inject constructor(
    private val repo: AuthRepository,
    dispatcher: AppCoroutineDispatchers
) : ResultInteractor<Unit, Account>(dispatcher.io) {

    override suspend fun doWork(params: Unit): Account {
        return repo.getCurrentAccount()
    }
}