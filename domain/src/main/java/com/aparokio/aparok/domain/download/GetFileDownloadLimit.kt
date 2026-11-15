package com.aparokio.aparok.domain.download

import com.aparokio.aparok.core_models.FileDownloadLimit
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.config.UserSettingsRepository
import javax.inject.Inject

class GetFileDownloadLimit @Inject constructor(
    private val settings: UserSettingsRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<Unit, FileDownloadLimit>(dispatchers.io) {

    override suspend fun doWork(params: Unit): FileDownloadLimit {
        return settings.getFileDownloadLimit()
    }
}
