package com.aparokio.aparok.di.feature.spaces

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.core_utils.notifications.NotificationPermissionManager
import com.aparokio.aparok.device.DefaultGradientCollectionProvider
import com.aparokio.aparok.device.share.debug.DebugSpaceDeviceFileContentSaver
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.auth.interactor.GetAccount
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.clipboard.Clipboard
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.cover.GetCoverGradientCollection
import com.aparokio.aparok.domain.debugging.DebugSpaceContentSaver
import com.aparokio.aparok.domain.debugging.Logger
import com.aparokio.aparok.domain.device.DeviceTokenStoringService
import com.aparokio.aparok.domain.invite.SpaceInviteLinkStore
import com.aparokio.aparok.domain.library.StorelessSubscriptionContainer
import com.aparokio.aparok.domain.misc.AppActionManager
import com.aparokio.aparok.domain.multiplayer.ActiveSpaceMemberSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.notifications.SetSpaceNotificationMode
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.domain.search.SearchObjects
import com.aparokio.aparok.domain.resources.StringResourceProvider
import com.aparokio.aparok.domain.search.ProfileSubscriptionManager
import com.aparokio.aparok.domain.wallpaper.SetWallpaper
import com.aparokio.aparok.domain.wallpaper.WallpaperStore
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.spaces.SpaceGradientProvider
import com.aparokio.aparok.presentation.spaces.SpaceSettingsViewModel
import com.aparokio.aparok.presentation.util.downloader.UriFileProvider
import com.aparokio.aparok.ui.settings.space.SpaceSettingsFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(
    dependencies = [SpaceSettingsDependencies::class],
    modules = [
        SpaceSettingsModule::class,
        SpaceSettingsModule.Bindings::class
    ]
)
@PerScreen
interface SpaceSettingsComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance vmParams: SpaceSettingsViewModel.VmParams,
            dependencies: SpaceSettingsDependencies
        ) : SpaceSettingsComponent
    }

    fun inject(fragment: SpaceSettingsFragment)
}

@Module
object SpaceSettingsModule {

    @JvmStatic
    @Provides
    @PerScreen
    fun provideSpaceGradientProvider(): SpaceGradientProvider = SpaceGradientProvider.Default

    @JvmStatic
    @Provides
    @PerScreen
    fun provideGetAccountUseCase(
        repo: AuthRepository,
        dispatchers: AppCoroutineDispatchers
    ): GetAccount = GetAccount(repo = repo, dispatcher = dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideSetSpaceNotificationModeUseCase(
        repository: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetSpaceNotificationMode = SetSpaceNotificationMode(repository, dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideSetWallpaper(
        repo: UserSettingsRepository,
        dispatchers: AppCoroutineDispatchers
    ) : SetWallpaper = SetWallpaper(
        repo = repo,
        store = WallpaperStore.Default,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideGetCoverGradientCollectionUseCase(
    ): GetCoverGradientCollection = GetCoverGradientCollection(DefaultGradientCollectionProvider)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideSearchObjects(
        repository: BlockRepository
    ): SearchObjects = SearchObjects(repo = repository)

    @Module
    interface Bindings {

        @PerScreen
        @Binds
        fun bindSpaceDebugDeviceSharer(
            saver: DebugSpaceDeviceFileContentSaver
        ): DebugSpaceContentSaver
        @PerScreen
        @Binds
        fun factory(factory: SpaceSettingsViewModel.Factory): ViewModelProvider.Factory
    }
}

interface SpaceSettingsDependencies : ComponentDependencies {
    fun blockRepo(): BlockRepository
    fun auth(): AuthRepository
    fun appActions(): AppActionManager
    fun storeOfObjectTypes(): StoreOfObjectTypes
    fun settings(): UserSettingsRepository
    fun urlBuilder(): UrlBuilder
    fun analytics(): Analytics
    fun dispatchers(): AppCoroutineDispatchers
    fun spaceManager(): SpaceManager
    fun container(): StorelessSubscriptionContainer
    fun context(): Context
    fun userPermission(): UserPermissionProvider
    fun spaceViewSubscriptionContainer(): SpaceViewSubscriptionContainer
    fun activeSpaceMemberSubscriptionContainer(): ActiveSpaceMemberSubscriptionContainer
    fun analyticSpaceHelper(): AnalyticSpaceHelperDelegate
    fun profileContainer(): ProfileSubscriptionManager
    fun uriFileProvider(): UriFileProvider
    fun notificationsPermissionManager(): NotificationPermissionManager
    fun deviceTokenStoreService(): DeviceTokenStoringService
    fun clipboard() : Clipboard
    fun logger(): Logger
    fun spaceInviteLinkStore() : SpaceInviteLinkStore
    fun stringResourceProvider(): StringResourceProvider
    fun fieldParser(): FieldParser
}