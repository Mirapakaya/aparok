package com.aparokio.aparok.domain.auth.interactor

import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.base.Either
import javax.inject.Inject

/**
 * Use case for starting loading remote accounts associated with current wallet.
 */
class StartLoadingAccounts @Inject constructor(
    private val repository: AuthRepository
) : BaseUseCase<Unit, StartLoadingAccounts.Params>() {

    override suspend fun run(params: Params) = try {
        repository.startLoadingAccounts().let {
            Either.Right(it)
        }
    } catch (e: Throwable) {
        Either.Left(e)
    }

    class Params
}