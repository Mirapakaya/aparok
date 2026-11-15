package com.aparokio.aparok.di.feature.multiplayer

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_utils.di.scope.PerDialog
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.debugging.Logger
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.multiplayer.SpaceInviteResolver
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.notifications.SystemNotificationService
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.other.DefaultSpaceInviteResolver
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.multiplayer.RequestJoinSpaceViewModel
import com.aparokio.aparok.ui.multiplayer.RequestJoinSpaceFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(
    dependencies = [RequestJoinSpaceDependencies::class],
    modules = [
        RequestJoinSpaceModule::class,
        RequestJoinSpaceModule.Declarations::class
    ]
)
@PerDialog
interface RequestJoinSpaceComponent {

    @Component.Builder
    interface Builder {
        fun withDependencies(dependencies: RequestJoinSpaceDependencies): Builder
        @BindsInstance
        fun withParams(params: RequestJoinSpaceViewModel.Params): Builder
        fun build(): RequestJoinSpaceComponent
    }

    fun inject(fragment: RequestJoinSpaceFragment)
}

@Module
object RequestJoinSpaceModule {
    @Module
    interface Declarations {
        @PerDialog
        @Binds
        fun bindViewModelFactory(factory: RequestJoinSpaceViewModel.Factory): ViewModelProvider.Factory
    }

    @PerDialog
    @Provides
    fun provideSpaceInviteResolver() : SpaceInviteResolver = DefaultSpaceInviteResolver
}

interface RequestJoinSpaceDependencies : ComponentDependencies {
    fun blockRepository(): BlockRepository
    fun auth(): AuthRepository
    fun settings(): UserSettingsRepository
    fun urlBuilder(): UrlBuilder
    fun dispatchers(): AppCoroutineDispatchers
    fun spaceManager(): SpaceManager
    fun logger(): Logger
    fun analytics(): Analytics
    fun analyticSpaceHelper(): AnalyticSpaceHelperDelegate
    fun notificator(): SystemNotificationService
    fun configStorage(): ConfigStorage
    fun spaceViewContainer(): SpaceViewSubscriptionContainer
}