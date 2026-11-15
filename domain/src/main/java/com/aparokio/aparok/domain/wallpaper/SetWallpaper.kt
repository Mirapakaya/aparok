package com.aparokio.aparok.domain.wallpaper

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Wallpaper
import com.aparokio.aparok.core_models.Wallpaper.Color
import com.aparokio.aparok.core_models.Wallpaper.Gradient
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.config.UserSettingsRepository
import javax.inject.Inject

class SetWallpaper @Inject constructor(
    private val repo: UserSettingsRepository,
    private val store: WallpaperStore,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<SetWallpaper.Params, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Params) {
        when (params) {
            is Params.Gradient -> {
                repo.setWallpaper(
                    space = params.space,
                    wallpaper = Gradient(params.code)
                )
                store.set(Gradient(params.code))
            }

            is Params.SolidColor -> {
                repo.setWallpaper(
                    space = params.space,
                    wallpaper = Color(params.code)
                )
                store.set(Color(params.code))
            }

            is Params.Clear -> {
                repo.setWallpaper(
                    space = params.space,
                    wallpaper = Wallpaper.Default
                )
                store.set(Wallpaper.Default)
            }
        }
    }

    sealed class Params {
        data class Clear(val space: Id) : Params()
        data class SolidColor(val space: Id, val code: String) : Params()
        data class Gradient(val space: Id, val code: String) : Params()
    }
}