package com.aparokio.aparok.di.feature.objects

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.objects.SelectObjectTypeViewModel
import com.aparokio.aparok.ui.objects.creation.SelectObjectTypeBaseFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module

@Component(
    dependencies = [SelectObjectTypeDependencies::class],
    modules = [
        SelectObjectTypeModule::class,
        SelectObjectTypeModule.Declarations::class
    ]
)
@PerScreen
interface SelectObjectTypeComponent {
    fun inject(fragment: SelectObjectTypeBaseFragment)
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance params: SelectObjectTypeViewModel.Params,
            dependencies: SelectObjectTypeDependencies
        ): SelectObjectTypeComponent
    }
}

@Module
object SelectObjectTypeModule {
    @Module
    interface Declarations {
        @Binds
        @PerScreen
        fun bindViewModelFactory(factory: SelectObjectTypeViewModel.Factory): ViewModelProvider.Factory
    }
}

interface SelectObjectTypeDependencies : ComponentDependencies {
    fun repo(): BlockRepository
    fun analytics(): Analytics
    fun dispatchers(): AppCoroutineDispatchers
    fun configStorage(): ConfigStorage
    fun analyticSpaceHelper(): AnalyticSpaceHelperDelegate
    fun userSettingsRepository(): UserSettingsRepository
    fun provideSpaceManger(): SpaceManager
    fun provideUrlBuilder(): UrlBuilder
    fun spaceViews(): SpaceViewSubscriptionContainer
}
