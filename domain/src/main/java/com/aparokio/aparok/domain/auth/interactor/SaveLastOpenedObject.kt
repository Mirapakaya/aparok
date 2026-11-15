package com.aparokio.aparok.domain.auth.interactor

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.config.UserSettingsRepository

/**
 * Use case for saving last open object's id for restoring user session.
 * @see GetLastOpenedObject
 * @see ClearLastOpenedObject
 */
class SaveLastOpenedObject(
    private val repo: UserSettingsRepository
) : BaseUseCase<Unit, SaveLastOpenedObject.Params>() {


    override suspend fun run(params: Params) = safe {
        repo.setLastOpenedObject(
            id = params.obj,
            space = params.space
        )
    }

    data class Params(val obj: Id, val space: SpaceId)
}