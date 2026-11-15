package com.aparokio.aparok.domain.types

import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.core_models.primitives.TypeId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.config.UserSettingsRepository
import javax.inject.Inject

class SetPinnedObjectTypes @Inject constructor(
    private val repo: UserSettingsRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<SetPinnedObjectTypes.Params, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Params) {
        repo.setPinnedObjectTypes(
            space = params.space,
            types = params.types
        )
    }

    class Params(
        val space: SpaceId,
        val types: List<TypeId>
    )
}