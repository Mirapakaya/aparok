package com.aparokio.aparok.di.feature.spaces

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.presentation.spaces.SpaceListViewModel
import com.aparokio.aparok.ui.spaces.SpaceListFragment
import dagger.Binds
import dagger.Component
import dagger.Module

@Component(
    dependencies = [SpaceListDependencies::class],
    modules = [
        SpaceListModule::class,
        SpaceListModule.Declarations::class
    ]
)
@PerScreen
interface SpaceListComponent {
    @Component.Factory
    interface Builder {
        fun create(dependencies: SpaceListDependencies): SpaceListComponent
    }
    fun inject(fragment: SpaceListFragment)
}

@Module
object SpaceListModule {
    @Module
    interface Declarations {
        @Binds
        @PerScreen
        fun bindViewModelFactory(factory: SpaceListViewModel.Factory): ViewModelProvider.Factory
    }
}

interface SpaceListDependencies : ComponentDependencies {
    fun spaceViewContainer(): SpaceViewSubscriptionContainer
    fun permissions(): UserPermissionProvider
    fun urlBuilder(): UrlBuilder
    fun repo(): BlockRepository
    fun dispatchers(): AppCoroutineDispatchers
}