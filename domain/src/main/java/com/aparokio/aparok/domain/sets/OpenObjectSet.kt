package com.aparokio.aparok.domain.sets

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.base.Result
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.config.UserSettingsRepository

@Deprecated("Legacy. Need to migrate to OpenObject useCase")
class OpenObjectSet(
    private val repo: BlockRepository,
    private val settings: UserSettingsRepository
) : BaseUseCase<Result<Payload>, OpenObjectSet.Params>() {

    override suspend fun run(params: Params) = safe {
        repo.openObjectSet(
            id = params.obj,
            space = params.space
        ).also {
            settings.setLastOpenedObject(
                id = params.obj,
                space = params.space
            )
        }
    }

    data class Params(
        val space: SpaceId,
        val obj: Id
    )
}