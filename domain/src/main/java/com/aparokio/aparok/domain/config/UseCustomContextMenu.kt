package com.aparokio.aparok.domain.config

import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.base.Either

class UseCustomContextMenu(private val repository: InfrastructureRepository) :
    BaseUseCase<Unit, UseCustomContextMenu.Params>() {

    override suspend fun run(params: Params): Either<Throwable, Unit> = safe {
        if (params.use) {
            repository.enableAparokContextMenu()
        } else {
            repository.disableAparokContextMenu()
        }
    }

    data class Params(val use: Boolean)
}