package com.aparokio.aparok.di.main

import com.aparokio.aparok.core_ai.MlKitTypeSuggestionEngine
import com.aparokio.aparok.domain.ai.FlagGatedTypeSuggestionEngine
import com.aparokio.aparok.domain.ai.TypeSuggestionEngine
import com.aparokio.aparok.domain.config.UserSettingsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * On-device AI (quick capture type suggestions, spec §9). The ML Kit engine holds its
 * Gemini Nano client behind `by lazy`, and [FlagGatedTypeSuggestionEngine] checks the
 * feature flag before touching the delegate — together they keep AICore completely cold
 * for every user with the flag off, even though this singleton is constructed on the
 * first editor open.
 */
@Module
object AiModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideTypeSuggestionEngine(
        userSettingsRepository: UserSettingsRepository
    ): TypeSuggestionEngine = FlagGatedTypeSuggestionEngine(
        settings = userSettingsRepository,
        delegate = MlKitTypeSuggestionEngine()
    )
}
