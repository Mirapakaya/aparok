package com.aparokio.aparok.domain.wallpaper

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Wallpaper
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.config.UserSettingsRepository
import javax.inject.Inject

class GetSpaceWallpapers @Inject constructor(
    private val repo: UserSettingsRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<Unit, Map<Id, Wallpaper>>(dispatchers.io) {

    override suspend fun doWork(params: Unit): Map<Id, Wallpaper> {
        return repo.getWallpapers()
    }
}