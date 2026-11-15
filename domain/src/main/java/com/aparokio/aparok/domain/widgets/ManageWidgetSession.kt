package com.aparokio.aparok.domain.widgets

import com.aparokio.aparok.core_models.WidgetSession
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.config.UserSettingsRepository
import javax.inject.Inject

class GetWidgetSession @Inject constructor(
    private val repo: UserSettingsRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<Unit, WidgetSession>(dispatchers.io) {
    override suspend fun doWork(params: Unit): WidgetSession = repo.getWidgetSession()
}

class SaveWidgetSession @Inject constructor(
    private val repo: UserSettingsRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<SaveWidgetSession.Params, Unit>(dispatchers.io) {
    override suspend fun doWork(params: Params) {
        repo.saveWidgetSession(params.session)
    }

    data class Params(val session: WidgetSession)
}