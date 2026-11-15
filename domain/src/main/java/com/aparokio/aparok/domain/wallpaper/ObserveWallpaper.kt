package com.aparokio.aparok.domain.wallpaper

import com.aparokio.aparok.core_models.Wallpaper
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.base.FlowUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ObserveWallpaper @Inject constructor () : FlowUseCase<Wallpaper, BaseUseCase.None>() {
    override fun build(params: BaseUseCase.None?): Flow<Wallpaper> {
        return WallpaperStore.Default.observe()
    }
}