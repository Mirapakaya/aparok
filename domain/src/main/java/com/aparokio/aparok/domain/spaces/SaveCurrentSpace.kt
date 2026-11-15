package com.aparokio.aparok.domain.spaces

import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.config.UserSettingsRepository
import javax.inject.Inject

class SaveCurrentSpace @Inject constructor(
    dispatchers: AppCoroutineDispatchers,
    private val repo: UserSettingsRepository
) : ResultInteractor<SaveCurrentSpace.Params, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Params) {
        repo.setCurrentSpace(params.space)
    }

    class Params(val space: SpaceId)
}