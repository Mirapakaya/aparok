package com.aparokio.aparok.di.feature.gallery

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.data.auth.event.EventProcessImportDateChannel
import com.aparokio.aparok.data.auth.event.EventProcessImportRemoteChannel
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.workspace.EventProcessImportChannel
import com.aparokio.aparok.gallery_experience.viewmodel.GalleryInstallationViewModel
import com.aparokio.aparok.gallery_experience.viewmodel.GalleryInstallationViewModelFactory
import com.aparokio.aparok.middleware.EventProxy
import com.aparokio.aparok.middleware.interactor.EventProcessImportMiddlewareChannel
import com.aparokio.aparok.presentation.spaces.SpaceGradientProvider
import com.aparokio.aparok.ui.gallery.GalleryInstallationFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(
    dependencies = [GalleryInstallationComponentDependencies::class],
    modules = [
        GalleryInstallationModule::class,
        GalleryInstallationModule.Declarations::class
    ]
)
@PerScreen
interface GalleryInstallationComponent {

    @Component.Builder
    interface Builder {
        fun withDependencies(dependencies: GalleryInstallationComponentDependencies): Builder

        @BindsInstance
        fun withParams(params: GalleryInstallationViewModel.ViewModelParams): Builder
        fun build(): GalleryInstallationComponent
    }

    fun inject(fragment: GalleryInstallationFragment)
}

@Module
object GalleryInstallationModule {

    @Provides
    @PerScreen
    fun provideEventProcessRemoteChannel(
        proxy: EventProxy
    ): EventProcessImportRemoteChannel = EventProcessImportMiddlewareChannel(events = proxy)

    @Provides
    @PerScreen
    fun provideEventProcessDateChannel(
        channel: EventProcessImportRemoteChannel
    ): EventProcessImportChannel = EventProcessImportDateChannel(channel = channel)

    @Provides
    @PerScreen
    fun provideGradientProvider(): SpaceGradientProvider {
        return SpaceGradientProvider.Default
    }

    @Module
    interface Declarations {

        @PerScreen
        @Binds
        fun bindViewModelFactory(
            factory: GalleryInstallationViewModelFactory
        ): ViewModelProvider.Factory

    }
}

interface GalleryInstallationComponentDependencies : ComponentDependencies {
    fun blockRepository(): BlockRepository
    fun appCoroutineDispatchers(): AppCoroutineDispatchers
    fun analytics(): Analytics
    fun urlBuilder(): UrlBuilder
    fun userPermissionProvider(): UserPermissionProvider
    fun eventProxy(): EventProxy
    fun configStorage(): ConfigStorage
}