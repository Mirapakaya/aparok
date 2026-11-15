package com.aparokio.aparok.di.feature.spaces

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.search.ProfileSubscriptionManager
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.spaces.CreateSpaceViewModel
import com.aparokio.aparok.ui.spaces.CreateSpaceFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module

@Component(
    dependencies = [CreateSpaceDependencies::class],
    modules = [
        CreateSpaceModule::class,
        CreateSpaceModule.Declarations::class
    ]
)
@PerScreen
interface CreateSpaceComponent {
    @Component.Factory
    interface Builder {
        fun create(
            @BindsInstance vmParams: CreateSpaceViewModel.VmParams,
            dependencies: CreateSpaceDependencies
        ): CreateSpaceComponent
    }

    fun inject(fragment: CreateSpaceFragment)
}

@Module
object CreateSpaceModule {
    @Module
    interface Declarations {
        @Binds
        @PerScreen
        fun bindViewModelFactory(factory: CreateSpaceViewModel.Factory): ViewModelProvider.Factory
    }
}

interface CreateSpaceDependencies : ComponentDependencies {
    fun repo(): BlockRepository
    fun analytics(): Analytics
    fun dispatchers(): AppCoroutineDispatchers
    fun spaceManager(): SpaceManager
    fun analyticSpaceHelper(): AnalyticSpaceHelperDelegate
    fun spaceViews(): SpaceViewSubscriptionContainer
    fun userSettingsRepo(): UserSettingsRepository
    fun permissions(): UserPermissionProvider
    fun profileContainer(): ProfileSubscriptionManager
    fun urlBuilder(): UrlBuilder
}