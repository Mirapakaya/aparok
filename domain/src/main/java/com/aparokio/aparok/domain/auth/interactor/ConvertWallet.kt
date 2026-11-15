package com.aparokio.aparok.domain.auth.interactor

import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.base.Either
import javax.inject.Inject

class ConvertWallet @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<String, ConvertWallet.Request>() {

    override suspend fun run(params: Request): Either<Throwable, String> = safe {
        val mnemonic = authRepository.convertWallet(entropy = params.entropy)
        authRepository.saveMnemonic(mnemonic)
        mnemonic
    }

    data class Request(val entropy: String)
}