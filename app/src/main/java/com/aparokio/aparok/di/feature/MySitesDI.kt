package com.aparokio.aparok.di.feature

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.core_utils.date.DateFormatter
import com.aparokio.aparok.core_utils.di.scope.PerDialog
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.presentation.publishtoweb.MySitesViewModel
import com.aparokio.aparok.ui.publishtoweb.MySitesFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module

@Component(
    dependencies = [MySitesDependencies::class],
    modules = [
        MySitesModule::class,
        MySitesModule.Declarations::class
    ]
)
@PerDialog
interface MySitesComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance vmParams: MySitesViewModel.VmParams,
            dependency: MySitesDependencies
        ): MySitesComponent
    }

    fun inject(fragment: MySitesFragment)
}

@Module
object MySitesModule {

    @Module
    interface Declarations {
        @PerDialog
        @Binds
        fun factory(factory: MySitesViewModel.Factory): ViewModelProvider.Factory

        @PerDialog
        @Binds
        fun dateFormatter(formatter: DateFormatter.Basic): DateFormatter
    }
}

interface MySitesDependencies : ComponentDependencies {
    fun repo(): BlockRepository
    fun auth(): AuthRepository
    fun dispatchers(): AppCoroutineDispatchers
    fun urlBuilder(): UrlBuilder
    fun spaceViews(): SpaceViewSubscriptionContainer
    fun spaceManager(): SpaceManager
}