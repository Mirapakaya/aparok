package com.aparokio.aparok.domain.download

import com.aparokio.aparok.core_models.FileDownloadLimit
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.config.UserSettingsRepository
import javax.inject.Inject

class SetFileDownloadLimit @Inject constructor(
    private val settings: UserSettingsRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<FileDownloadLimit, Unit>(dispatchers.io) {

    override suspend fun doWork(params: FileDownloadLimit) {
        settings.setFileDownloadLimit(params)
    }
}
