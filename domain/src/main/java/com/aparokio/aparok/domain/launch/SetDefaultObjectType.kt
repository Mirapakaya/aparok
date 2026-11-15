package com.aparokio.aparok.domain.launch

import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.core_models.primitives.TypeId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.config.UserSettingsRepository
import javax.inject.Inject

class SetDefaultObjectType @Inject constructor(
    private val repo: UserSettingsRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<SetDefaultObjectType.Params, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Params) {
        repo.setDefaultObjectType(
            space = params.space,
            type = params.type
        )
    }

    data class Params(
        val space: SpaceId,
        val type: TypeId,
    )
}