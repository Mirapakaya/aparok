package com.aparokio.aparok.domain.search

import com.aparokio.aparok.core_models.GlobalSearchHistory
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.config.UserSettingsRepository
import javax.inject.Inject

class UpdateGlobalSearchHistory @Inject constructor(
    private val settings: UserSettingsRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<UpdateGlobalSearchHistory.Params, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Params) {
        settings.setGlobalSearchHistory(
            globalSearchHistory = GlobalSearchHistory(
                query = params.query,
                relatedObject = params.relatedObjectId
            ),
            space = params.spaceId
        )
    }

    data class Params(
        val spaceId: SpaceId,
        val query: String,
        val relatedObjectId: Id?
    )
}