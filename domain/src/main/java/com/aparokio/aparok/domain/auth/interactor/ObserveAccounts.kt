package com.aparokio.aparok.domain.auth.interactor

import com.aparokio.aparok.core_models.Account
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.FlowUseCase
import javax.inject.Inject

class ObserveAccounts @Inject constructor(
    private val repository: AuthRepository
) : FlowUseCase<Account, Unit>() {

    override fun build(
        params: Unit?
    ) = repository.observeAccounts()
}