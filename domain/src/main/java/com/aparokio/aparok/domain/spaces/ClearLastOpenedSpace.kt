package com.aparokio.aparok.domain.spaces

import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.config.UserSettingsRepository
import javax.inject.Inject

class ClearLastOpenedSpace @Inject constructor(
    private val repo: UserSettingsRepository,
    dispatchers: AppCoroutineDispatchers
): ResultInteractor<Unit, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Unit) {
        return repo.clearCurrentSpace()
    }
}