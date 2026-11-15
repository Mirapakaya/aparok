package com.aparokio.aparok.domain.account

import com.aparokio.aparok.core_models.AccountStatus
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.BaseUseCase

class RestoreAccount(
    private val repo: AuthRepository,
    dispatchers: AppCoroutineDispatchers,
) : BaseUseCase<AccountStatus, BaseUseCase.None>(context = dispatchers.io) {

    override suspend fun run(params: None) = safe {
        repo.restoreAccount()
    }
}