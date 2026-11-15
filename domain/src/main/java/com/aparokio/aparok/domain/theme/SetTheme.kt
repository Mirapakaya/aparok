package com.aparokio.aparok.domain.theme

import com.aparokio.aparok.core_models.ThemeMode
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.config.UserSettingsRepository

class SetTheme(
    private val repo: UserSettingsRepository
) : BaseUseCase<Unit, ThemeMode>() {
    override suspend fun run(params: ThemeMode) = safe {
        repo.setThemeMode(params)
    }
}