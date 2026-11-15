package com.aparokio.aparok.domain.account

import com.aparokio.aparok.core_models.AccountStatus
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.BaseUseCase

class DeleteAccount(private val repo: AuthRepository) : BaseUseCase<AccountStatus, BaseUseCase.None>() {
    override suspend fun run(params: None) = safe { repo.deleteAccount() }
}