package com.aparokio.aparok.domain.auth.interactor

import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.config.UserSettingsRepository
import javax.inject.Inject

/**
 * Use case for clearing last open object's id from user session.
 * @see SaveLastOpenedObject
 */
class ClearLastOpenedObject @Inject constructor(
    private val repo: UserSettingsRepository
) : BaseUseCase<Unit, ClearLastOpenedObject.Params>() {

    override suspend fun run(params: Params) = safe {
        repo.clearLastOpenedObject(params.space)
    }

    data class Params(val space: SpaceId)
}