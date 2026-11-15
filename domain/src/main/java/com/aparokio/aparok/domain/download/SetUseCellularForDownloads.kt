package com.aparokio.aparok.domain.download

import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.config.UserSettingsRepository
import javax.inject.Inject

class SetUseCellularForDownloads @Inject constructor(
    private val settings: UserSettingsRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<Boolean, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Boolean) {
        settings.setUseCellularForDownloads(params)
    }
}
