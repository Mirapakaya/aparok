package com.aparokio.aparok.di.feature.chats

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.core_utils.notifications.NotificationPermissionManager
import com.aparokio.aparok.core_utils.tools.FeatureToggles
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.di.main.ConfigModule.DEFAULT_APP_COROUTINE_SCOPE
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.chats.ChatEventChannel
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.debugging.Logger
import com.aparokio.aparok.domain.misc.DeepLinkResolver
import com.aparokio.aparok.domain.event.interactor.EventChannel
import com.aparokio.aparok.domain.invite.SpaceInviteLinkStore
import com.aparokio.aparok.domain.library.StorelessSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.ActiveSpaceMemberSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.notifications.NotificationBuilder
import com.aparokio.aparok.domain.notifications.ResetSpaceChatNotification
import com.aparokio.aparok.domain.notifications.SetChatNotificationMode
import com.aparokio.aparok.domain.`object`.GetObject
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.domain.search.SearchObjects
import com.aparokio.aparok.domain.spaces.ClearLastOpenedSpace
import com.aparokio.aparok.domain.widgets.CreateWidget
import com.aparokio.aparok.domain.widgets.DeleteWidget
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.feature_chats.presentation.ChatViewModel
import com.aparokio.aparok.feature_chats.presentation.ChatViewModelFactory
import com.aparokio.aparok.middleware.EventProxy
import com.aparokio.aparok.presentation.common.PayloadDelegator
import com.aparokio.aparok.presentation.navigation.backstack.BackHistoryDelegate
import com.aparokio.aparok.presentation.navigation.backstack.NavigationBackStackInspector
import com.aparokio.aparok.presentation.util.CopyFileToCacheDirectory
import com.aparokio.aparok.presentation.util.defaultCopyFileToCacheDirectory
import com.aparokio.aparok.presentation.vault.ExitToVaultDelegate
import com.aparokio.aparok.presentation.widgets.PinObjectAsWidgetDelegate
import com.aparokio.aparok.ui.chats.ChatFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Named
import kotlinx.coroutines.CoroutineScope

@Component(
    dependencies = [ChatComponentDependencies::class],
    modules = [
        ChatContainerModule::class,
        ChatModule::class,
        ChatModule.Declarations::class
    ]
)
@PerScreen
interface ChatComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withParams(params: ChatViewModel.Params.Default): Builder
        fun withDependencies(dependencies: ChatComponentDependencies): Builder
        fun build(): ChatComponent
    }
    fun inject(fragment: ChatFragment)
}

@Module
object ChatModule {

    @JvmStatic
    @Provides
    @PerScreen
    fun provideCopyFileToCache(
        context: Context
    ): CopyFileToCacheDirectory = defaultCopyFileToCacheDirectory(context)

    @JvmStatic
    @PerScreen
    @Provides
    fun provideExitToVaultDelegate(
        spaceManager: SpaceManager,
        clearLastOpenedSpace: ClearLastOpenedSpace
    ) : ExitToVaultDelegate = ExitToVaultDelegate.Default(
        spaceManager = spaceManager,
        clearLastOpenedSpace = clearLastOpenedSpace
    )

    @JvmStatic
    @PerScreen
    @Provides
    fun providePinObjectAsWidgetDelegate(
        spaceManager: SpaceManager,
        createWidget: CreateWidget,
        deleteWidget: DeleteWidget,
        showObject: GetObject,
        payloadDelegator: PayloadDelegator
    ) : PinObjectAsWidgetDelegate = PinObjectAsWidgetDelegate.Default(
        spaceManager = spaceManager,
        createWidget = createWidget,
        deleteWidget = deleteWidget,
        showObject = showObject,
        payloadDelegator = payloadDelegator
    )

    @JvmStatic
    @PerScreen
    @Provides
    fun provideBackHistoryDelegate(
        inspector: NavigationBackStackInspector,
        searchObjects: SearchObjects,
        fieldParser: FieldParser,
        dispatchers: AppCoroutineDispatchers
    ): BackHistoryDelegate = BackHistoryDelegate.Default(
        inspector = inspector,
        searchObjects = searchObjects,
        fieldParser = fieldParser,
        dispatchers = dispatchers
    )

    @Module
    interface Declarations {
        @PerScreen
        @Binds
        fun bindViewModelFactory(
            factory: ChatViewModelFactory
        ): ViewModelProvider.Factory
    }
}

interface ChatComponentDependencies : ComponentDependencies {
    @Named(DEFAULT_APP_COROUTINE_SCOPE) fun scope(): CoroutineScope
    fun blockRepository(): BlockRepository
    fun authRepo(): AuthRepository
    fun appCoroutineDispatchers(): AppCoroutineDispatchers
    fun analytics(): Analytics
    fun urlBuilder(): UrlBuilder
    fun userPermissionProvider(): UserPermissionProvider
    fun eventProxy(): EventProxy
    fun featureToggles(): FeatureToggles
    fun userSettings(): UserSettingsRepository
    fun chatEventChannel(): ChatEventChannel
    fun logger(): Logger
    fun members(): ActiveSpaceMemberSubscriptionContainer
    fun spaceViewSubscriptionContainer(): SpaceViewSubscriptionContainer
    fun storeOfObjectTypes(): StoreOfObjectTypes
    fun context(): Context
    fun spaceManager(): SpaceManager
    fun notificationPermissionManager(): NotificationPermissionManager
    fun storelessSubscriptionContainer(): StorelessSubscriptionContainer
    fun notificationBuilder(): NotificationBuilder
    fun eventChannel(): EventChannel
    fun spaceLinkInfoStore(): SpaceInviteLinkStore
    fun setChatNotificationMode(): SetChatNotificationMode
    fun resetSpaceChatNotification(): ResetSpaceChatNotification
    fun payloadDelegator(): PayloadDelegator
    fun fieldParser(): FieldParser
    fun deepLinkResolver(): DeepLinkResolver
    fun navigationBackStackInspector(): NavigationBackStackInspector
}
