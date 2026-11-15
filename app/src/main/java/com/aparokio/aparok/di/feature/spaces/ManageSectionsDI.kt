package com.aparokio.aparok.di.feature.spaces

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.presentation.spaces.ManageSectionsViewModel
import com.aparokio.aparok.ui.settings.space.ManageSectionsFragment
import dagger.Binds
import dagger.Component
import dagger.Module

@Component(
    dependencies = [ManageSectionsDependencies::class],
    modules = [ManageSectionsModule.Bindings::class]
)
@PerScreen
interface ManageSectionsComponent {
    @Component.Factory
    interface Factory {
        fun create(
            dependencies: ManageSectionsDependencies
        ): ManageSectionsComponent
    }

    fun inject(fragment: ManageSectionsFragment)
}

@Module
object ManageSectionsModule {

    @Module
    interface Bindings {
        @PerScreen
        @Binds
        fun factory(factory: ManageSectionsViewModel.Factory): ViewModelProvider.Factory
    }
}

interface ManageSectionsDependencies : ComponentDependencies {
    fun spaceManager(): SpaceManager
    fun settings(): UserSettingsRepository
    fun dispatchers(): AppCoroutineDispatchers
}
