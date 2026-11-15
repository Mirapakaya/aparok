package com.aparokio.aparok.domain.auth.interactor

import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.base.Either

open class GetLibraryVersion(
    private val repo: AuthRepository
) : BaseUseCase<String, BaseUseCase.None>() {

    override suspend fun run(params: BaseUseCase.None): Either<Throwable, String> = safe {
        repo.getVersion().removePrefix(PREFIX)
    }

    companion object {
        const val PREFIX = "v"
    }
}