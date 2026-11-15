package com.aparokio.aparok.domain.theme

import com.aparokio.aparok.core_models.ThemeMode
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.config.UserSettingsRepository

class GetTheme(
    private val repo: UserSettingsRepository
) : BaseUseCase<ThemeMode, BaseUseCase.None>() {
    override suspend fun run(params: None) = safe {
        repo.getThemeMode()
    }
}