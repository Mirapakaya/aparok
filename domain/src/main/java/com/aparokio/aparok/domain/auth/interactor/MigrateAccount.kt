package com.aparokio.aparok.domain.auth.interactor

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.device.PathProvider
import javax.inject.Inject

class MigrateAccount @Inject constructor(
    private val repo: AuthRepository,
    private val path: PathProvider,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<MigrateAccount.Params, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Params) {
        when(params) {
            is Params.Current -> {
                val acc = repo.getCurrentAccount()
                val path = path.providePath()
                repo.migrateAccount(
                    account = acc.id,
                    path = path
                )
            }
            is Params.Other -> {
                val path = path.providePath()
                repo.migrateAccount(
                    account = params.acc,
                    path = path
                )
            }
        }
    }

    sealed class Params {
        data object Current : Params()
        data class Other(val acc: Id) : Params()
    }
}

