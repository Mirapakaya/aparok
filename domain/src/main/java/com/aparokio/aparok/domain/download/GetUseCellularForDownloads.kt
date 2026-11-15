package com.aparokio.aparok.domain.download

import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.config.UserSettingsRepository
import javax.inject.Inject

class GetUseCellularForDownloads @Inject constructor(
    private val settings: UserSettingsRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<Unit, Boolean>(dispatchers.io) {

    override suspend fun doWork(params: Unit): Boolean {
        return settings.getUseCellularForDownloads()
    }
}
