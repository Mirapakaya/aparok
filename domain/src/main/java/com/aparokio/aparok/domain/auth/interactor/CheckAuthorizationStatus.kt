package com.aparokio.aparok.domain.auth.interactor

import com.aparokio.aparok.core_models.Account
import com.aparokio.aparok.domain.auth.model.AuthStatus
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import javax.inject.Inject

/**
 * Use case for checking authorisation status.
 * User can be either authorized or unauthorized based on number of available accounts.
 * @param repository repository containing information about available accounts
 */
class CheckAuthorizationStatus @Inject constructor(
    private val repository: AuthRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<Unit, Pair<AuthStatus, Account?>>(dispatchers.io) {

    override suspend fun doWork(params: Unit): Pair<AuthStatus, Account?> {
        return repository.getAccounts().let { accounts ->
            val mnemonic = repository.getMnemonic()
            if (accounts.isNotEmpty() && !mnemonic.isNullOrBlank())
                AuthStatus.AUTHORIZED to accounts.firstOrNull()
            else
                AuthStatus.UNAUTHORIZED to null
        }
    }
}