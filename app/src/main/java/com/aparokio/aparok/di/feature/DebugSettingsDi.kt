package com.aparokio.aparok.di.feature

import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.config.GetDebugSettings
import com.aparokio.aparok.domain.config.InfrastructureRepository
import com.aparokio.aparok.domain.config.UseCustomContextMenu
import com.aparokio.aparok.domain.debugging.DebugLocalStore
import com.aparokio.aparok.domain.debugging.DebugSpace
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.ui.settings.DebugSettingsFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [DebugSettingsModule::class])
@PerScreen
interface DebugSettingsSubComponent {

    @Subcomponent.Builder
    interface Builder {
        fun module(module: DebugSettingsModule): Builder
        fun build(): DebugSettingsSubComponent
    }

    fun inject(fragment: DebugSettingsFragment)
}

@Module
class DebugSettingsModule {

    @Provides
    @PerScreen
    fun provideUseCustomContextMenu(repo: InfrastructureRepository): UseCustomContextMenu =
        UseCustomContextMenu(repo)

    @Provides
    @PerScreen
    fun provideGetDebugSettings(
        repo: InfrastructureRepository
    ): GetDebugSettings = GetDebugSettings(
        repo = repo
    )

    @Provides
    @PerScreen
    fun provideDebugSync(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers,
        spaceManager: SpaceManager
    ): DebugSpace = DebugSpace(
        repo = repo,
        dispatchers = dispatchers,
        spaceManager = spaceManager
    )

    @Provides
    @PerScreen
    fun provideDebugLocalStore(repo: BlockRepository) : DebugLocalStore = DebugLocalStore(repo)
}