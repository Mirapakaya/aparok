package com.aparokio.aparok.di.feature.vault

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.core_utils.notifications.NotificationPermissionManager
import com.aparokio.aparok.core_utils.tools.AppInfo
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.di.main.ConfigModule.DEFAULT_APP_COROUTINE_SCOPE
import com.aparokio.aparok.domain.account.AwaitAccountStartManager
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.chats.ChatEventChannel
import com.aparokio.aparok.domain.chats.ChatPreviewContainer
import com.aparokio.aparok.domain.chats.ChatsDetailsSubscriptionContainer
import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.debugging.Logger
import com.aparokio.aparok.domain.deeplink.PendingIntentStore
import com.aparokio.aparok.domain.library.StorelessSubscriptionContainer
import com.aparokio.aparok.domain.misc.AppActionManager
import com.aparokio.aparok.domain.misc.DateProvider
import com.aparokio.aparok.domain.misc.DeepLinkResolver
import com.aparokio.aparok.domain.multiplayer.ParticipantSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.SearchOneToOneChatByIdentity
import com.aparokio.aparok.domain.multiplayer.SpaceInviteResolver
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.notifications.SetSpaceNotificationMode
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.domain.resources.StringResourceProvider
import com.aparokio.aparok.domain.search.ProfileSubscriptionManager
import com.aparokio.aparok.domain.search.SearchObjects
import com.aparokio.aparok.domain.spaces.CreateSpace
import com.aparokio.aparok.domain.wallpaper.GetSpaceWallpapers
import com.aparokio.aparok.domain.network.NetworkModeProvider
import com.aparokio.aparok.domain.payments.GetMembershipFeatures
import com.aparokio.aparok.domain.widgets.OsWidgetDataViewSync
import com.aparokio.aparok.domain.widgets.OsWidgetSpacesSync
import com.aparokio.aparok.domain.workspace.DeepLinkToObjectDelegate
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.feature_vault.presentation.VaultViewModelFactory
import com.aparokio.aparok.other.DefaultSpaceInviteResolver
import com.aparokio.aparok.ui.vault.VaultFragment
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Named
import kotlinx.coroutines.CoroutineScope

@Component(
    dependencies = [VaultComponentDependencies::class],
    modules = [
        VaultModule::class,
        VaultModule.Declarations::class
    ]
)
@PerScreen
interface VaultComponent {

    fun inject(fragment: VaultFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: VaultComponentDependencies): VaultComponent
    }
}

@Module
object VaultModule {
    @Module
    interface Declarations {
        @PerScreen
        @Binds
        fun bindViewModelFactory(
            factory: VaultViewModelFactory
        ): ViewModelProvider.Factory

        @PerScreen
        @Binds
        fun deepLinkToObjectDelegate(
            default: DeepLinkToObjectDelegate.Default
        ): DeepLinkToObjectDelegate
    }

    @PerScreen
    @Provides
    fun provideSpaceInviteResolver(): SpaceInviteResolver = DefaultSpaceInviteResolver

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
    fun provideGetSpaceWallpapers(
        userSettingsRepository: UserSettingsRepository,
        dispatchers: AppCoroutineDispatchers
    ): GetSpaceWallpapers = GetSpaceWallpapers(userSettingsRepository, dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideSearchOneToOneChatByIdentity(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SearchOneToOneChatByIdentity = SearchOneToOneChatByIdentity(
        repo = repo,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideCreateSpace(
        repository: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): CreateSpace = CreateSpace(repo = repository, dispatchers = dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideGetMembershipFeatures(
        repository: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): GetMembershipFeatures = GetMembershipFeatures(repo = repository, dispatchers = dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideSearchObjects(
        repository: BlockRepository
    ): SearchObjects = SearchObjects(repo = repository)
}

interface VaultComponentDependencies : ComponentDependencies {
    fun blockRepository(): BlockRepository
    fun appCoroutineDispatchers(): AppCoroutineDispatchers
    fun analytics(): Analytics
    fun urlBuilder(): UrlBuilder
    fun spaceViewSubscriptionContainer(): SpaceViewSubscriptionContainer
    fun userSettingsRepository(): UserSettingsRepository
    fun spaceManager(): SpaceManager
    fun userPermissionProvider(): UserPermissionProvider
    fun auth(): AuthRepository
    fun appActionManager(): AppActionManager
    fun logger(): Logger
    fun awaitAccount(): AwaitAccountStartManager
    fun profileContainer(): ProfileSubscriptionManager
    fun pendingIntentStore(): PendingIntentStore
    fun stringResourceProvider(): StringResourceProvider
    fun dateProvider(): DateProvider
    fun fieldParser(): FieldParser
    fun storeOfObjectTypes(): StoreOfObjectTypes
    fun notificationPermissionManager(): NotificationPermissionManager
    fun provideChatEventChannel(): ChatEventChannel
    fun provideStorelessSubscriptionContainer(): StorelessSubscriptionContainer
    fun provideVaultChatPreviewContainer(): ChatPreviewContainer
    fun appInfo(): AppInfo
    @Named(DEFAULT_APP_COROUTINE_SCOPE) fun scope(): CoroutineScope
    fun chatSubscriptionContainer(): ChatsDetailsSubscriptionContainer
    fun participantSubscriptionContainer(): ParticipantSubscriptionContainer
    fun deepLinkResolver(): DeepLinkResolver
    fun configStorage(): ConfigStorage
    fun osWidgetSpacesSync(): OsWidgetSpacesSync
    fun osWidgetDataViewSync(): OsWidgetDataViewSync
    fun networkModeProvider(): NetworkModeProvider
}
