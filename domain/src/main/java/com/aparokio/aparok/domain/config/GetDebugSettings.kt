package com.aparokio.aparok.domain.config

import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.base.Either

class GetDebugSettings(private val repo: InfrastructureRepository) :
    BaseUseCase<DebugSettings, Unit>() {

    override suspend fun run(params: Unit): Either<Throwable, DebugSettings> = safe {
        DebugSettings(isAparokContextMenuEnabled = false)
    }
}