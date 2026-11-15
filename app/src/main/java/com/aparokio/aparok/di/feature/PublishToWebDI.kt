package com.aparokio.aparok.di.feature

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.core_utils.di.scope.PerDialog
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.feature_properties.space.SpacePropertiesViewModel
import com.aparokio.aparok.presentation.publishtoweb.PublishToWebViewModel
import com.aparokio.aparok.ui.publishtoweb.PublishToWebFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module

@Component(
    dependencies = [PublishToWebDependencies::class],
    modules = [
        PublishToWebModule::class,
        PublishToWebModule.Declarations::class
    ]
)
@PerDialog
interface PublishToWebComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance vmParams: PublishToWebViewModel.Params,
            dependency: PublishToWebDependencies
        ): PublishToWebComponent
    }

    fun inject(fragment: PublishToWebFragment)
}

@Module
object PublishToWebModule {

    @Module
    interface Declarations {
        @PerDialog
        @Binds
        fun factory(factory: PublishToWebViewModel.Factory): ViewModelProvider.Factory
    }
}

interface PublishToWebDependencies : ComponentDependencies {
    fun repo(): BlockRepository
    fun auth(): AuthRepository
    fun dispatchers(): AppCoroutineDispatchers
    fun spaces(): SpaceViewSubscriptionContainer
    fun urlBuilder(): UrlBuilder
}
