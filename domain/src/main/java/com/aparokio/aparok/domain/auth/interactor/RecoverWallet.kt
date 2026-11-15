package com.aparokio.aparok.domain.auth.interactor

import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.base.Either
import javax.inject.Inject

/**
 * Use case for recovering wallet via recovery (aka keychain) phrase (mnemonic).
 */
class RecoverWallet @Inject constructor(
    private val repository: AuthRepository
) : BaseUseCase<Unit, RecoverWallet.Params>() {

    override suspend fun run(params: Params) = try {
        repository.recoverWallet(
            path = params.path,
            mnemonic = params.mnemonic
        ).let {
            Either.Right(it)
        }
    } catch (e: Throwable) {
        Either.Left(e)
    }

    class Params(val path: String, val mnemonic: String)
}