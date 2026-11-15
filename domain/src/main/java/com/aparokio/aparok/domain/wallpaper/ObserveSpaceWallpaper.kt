package com.aparokio.aparok.domain.wallpaper

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Wallpaper
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.FlowInteractor
import com.aparokio.aparok.domain.config.UserSettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class ObserveSpaceWallpaper @Inject constructor(
    private val userSettingsRepository: UserSettingsRepository,
    dispatchers: AppCoroutineDispatchers
) : FlowInteractor<ObserveSpaceWallpaper.Params, Wallpaper>(dispatchers.io) {

    override fun build(): Flow<Wallpaper> {
        return userSettingsRepository.observeWallpaper("")
            .catch { 
                emit(Wallpaper.Default)
            }
    }

    override fun build(params: Params): Flow<Wallpaper> {
        return userSettingsRepository.observeWallpaper(params.space)
    }

    data class Params(
        val space: Id
    )
}