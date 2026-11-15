package com.aparokio.aparok.di.feature.home

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.core_utils.notifications.NotificationPermissionManager
import com.aparokio.aparok.core_utils.tools.FeatureToggles
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.di.main.ConfigModule.DEFAULT_APP_COROUTINE_SCOPE
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.bin.EmptyBin
import com.aparokio.aparok.domain.block.interactor.Move
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.chats.ChatEventChannel
import com.aparokio.aparok.domain.chats.ChatPreviewContainer
import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.debugging.Logger
import com.aparokio.aparok.domain.device.FileSharer
import com.aparokio.aparok.domain.event.interactor.EventChannel
import com.aparokio.aparok.domain.event.interactor.InterceptEvents
import com.aparokio.aparok.domain.favorites.PersonalFavoritesRepository
import com.aparokio.aparok.domain.launch.GetDefaultObjectType
import com.aparokio.aparok.domain.library.StorelessSubscriptionContainer
import com.aparokio.aparok.domain.media.UploadFile
import com.aparokio.aparok.domain.misc.AppActionManager
import com.aparokio.aparok.domain.misc.DateProvider
import com.aparokio.aparok.domain.multiplayer.ActiveSpaceMemberSubscriptionContainer
import com.aparokio.aparok.domain.notifications.SetSpaceNotificationMode
import com.aparokio.aparok.domain.multiplayer.ParticipantSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.SpaceInviteResolver
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.`object`.GetObject
import com.aparokio.aparok.domain.`object`.OpenObject
import com.aparokio.aparok.domain.`object`.SetObjectDetails
import com.aparokio.aparok.domain.objects.ObjectStore
import com.aparokio.aparok.domain.objects.ObjectWatcher
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.domain.page.CloseObject
import com.aparokio.aparok.domain.page.CreateObject
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.domain.resources.StringResourceProvider
import com.aparokio.aparok.domain.search.HasInstanceOfObjectTypeSubscriptionContainer
import com.aparokio.aparok.domain.search.SubscriptionEventChannel
import com.aparokio.aparok.domain.spaces.ClearLastOpenedSpace
import com.aparokio.aparok.domain.spaces.SetHomepage
import com.aparokio.aparok.domain.templates.GetTemplates
import com.aparokio.aparok.domain.workspace.DeepLinkToObjectDelegate
import com.aparokio.aparok.domain.workspace.NotificationsChannel
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.other.DefaultSpaceInviteResolver
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.common.PayloadDelegator
import com.aparokio.aparok.presentation.editor.cover.CoverImageHashProvider
import com.aparokio.aparok.presentation.home.HomeScreenViewModel
import com.aparokio.aparok.presentation.home.HomeScreenVmParams
import com.aparokio.aparok.presentation.home.Unsubscriber
import com.aparokio.aparok.presentation.spaces.SpaceGradientProvider
import com.aparokio.aparok.presentation.util.Dispatcher
import com.aparokio.aparok.presentation.vault.ExitToVaultDelegate
import com.aparokio.aparok.presentation.widgets.DefaultObjectViewReducer
import com.aparokio.aparok.presentation.widgets.WidgetActiveViewStateHolder
import com.aparokio.aparok.presentation.widgets.WidgetDispatchEvent
import com.aparokio.aparok.presentation.widgets.WidgetSessionStateHolder
import com.aparokio.aparok.providers.DefaultCoverImageHashProvider
import com.aparokio.aparok.ui.home.WidgetOverlayFragment
import com.aparokio.aparok.ui.home.WidgetsScreenFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Named
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Component(
    dependencies = [HomeScreenDependencies::class],
    modules = [
        HomeScreenModule::class,
        HomeScreenModule.Declarations::class
    ]
)
@PerScreen
interface HomeScreenComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance vmParams: HomeScreenVmParams,
            dependencies: HomeScreenDependencies
        ): HomeScreenComponent
    }

    fun inject(fragment: WidgetsScreenFragment)
    fun inject(fragment: WidgetOverlayFragment)
}

@Module
object HomeScreenModule {

    @Provides
    fun provideUnqualifiedScope(
        @Named(DEFAULT_APP_COROUTINE_SCOPE) scope: CoroutineScope
    ): CoroutineScope = scope

    @JvmStatic
    @Provides
    @PerScreen
    fun openObject(
        repo: BlockRepository,
        settings: UserSettingsRepository,
        dispatchers: AppCoroutineDispatchers
    ): OpenObject = OpenObject(
        repo = repo,
        dispatchers = dispatchers,
        settings = settings
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun closeObject(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): CloseObject = CloseObject(
        repo = repo,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun setObjectDetails(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetObjectDetails = SetObjectDetails(
        repo = repo,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun move(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ) : Move = Move(
        repo = repo,
        appCoroutineDispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun emptyBin(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers,
        spaceManager: SpaceManager
    ) : EmptyBin = EmptyBin(
        repo = repo,
        dispatchers = dispatchers,
        spaceManager = spaceManager
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun getObject(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers,
        settings: UserSettingsRepository,
    ): GetObject = GetObject(
        repo = repo,
        dispatchers = dispatchers,
        settings = settings
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun createObject(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers,
        getDefaultEditorType: GetDefaultObjectType,
        spaceManager: SpaceManager
    ): CreateObject = CreateObject(
        repo = repo,
        dispatchers = dispatchers,
        getDefaultObjectType = getDefaultEditorType
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun getDefaultPageType(
        userSettingsRepository: UserSettingsRepository,
        blockRepository: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ) : GetDefaultObjectType = GetDefaultObjectType(
        userSettingsRepository = userSettingsRepository,
        blockRepository = blockRepository,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun getTemplates(
        repo: BlockRepository,
        spaceManager: SpaceManager,
        dispatchers: AppCoroutineDispatchers,
    ) : GetTemplates = GetTemplates(
        repo = repo,
        spaceManager = spaceManager,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun interceptEvents(channel: EventChannel): InterceptEvents = InterceptEvents(
        context = Dispatchers.IO,
        channel = channel
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun gradientProvider(): SpaceGradientProvider = SpaceGradientProvider.Default

    @JvmStatic
    @Provides
    @PerScreen
    fun coverHashProvider() : CoverImageHashProvider = DefaultCoverImageHashProvider()

    @JvmStatic
    @PerScreen
    @Provides
    fun provideSpaceInviteResolver(): SpaceInviteResolver = DefaultSpaceInviteResolver

    @JvmStatic
    @Provides
    @PerScreen
    fun setHomepage(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetHomepage = SetHomepage(
        repo = repo,
        dispatchers = dispatchers
    )

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
    @Provides
    @PerScreen
    fun provideUploadFile(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): UploadFile = UploadFile(repo = repo, dispatchers = dispatchers)

    @Module
    interface Declarations {
        @PerScreen
        @Binds
        fun factory(factory: HomeScreenViewModel.Factory): ViewModelProvider.Factory

        @PerScreen
        @Binds
        fun container(container: StorelessSubscriptionContainer.Impl): StorelessSubscriptionContainer

        @PerScreen
        @Binds
        fun holder(holder: WidgetActiveViewStateHolder.Impl): WidgetActiveViewStateHolder

        @PerScreen
        @Binds
        fun unsubscriber(impl: Unsubscriber.Impl) : Unsubscriber

        @PerScreen
        @Binds
        fun widgetSessionStateHolder(
            holder: WidgetSessionStateHolder.Impl
        ): WidgetSessionStateHolder

        @PerScreen
        @Binds
        fun objectWatcherReducer(
            default: DefaultObjectViewReducer
        ): ObjectWatcher.Reducer

        @PerScreen
        @Binds
        fun deepLinkToObjectDelegate(
            default: DeepLinkToObjectDelegate.Default
        ) : DeepLinkToObjectDelegate
    }
}

interface HomeScreenDependencies : ComponentDependencies {
    fun dispatcherWidgets(): Dispatcher<WidgetDispatchEvent>
    fun dispatcherPayload(): Dispatcher<Payload>
    fun blockRepo(): BlockRepository
    fun personalFavoritesRepo(): PersonalFavoritesRepository
    fun authRepo(): AuthRepository
    fun userRepo(): UserSettingsRepository
    fun config(): ConfigStorage
    fun urlBuilder(): UrlBuilder
    fun objectStore(): ObjectStore
    fun subscriptionEventChannel(): SubscriptionEventChannel
    fun analytics(): Analytics
    fun eventChannel(): EventChannel
    fun dispatchers(): AppCoroutineDispatchers
    fun appActionManager(): AppActionManager
    fun storeOfObjectTypes(): StoreOfObjectTypes
    fun hasInstanceOfObjectTypeSubscriptionContainer(): HasInstanceOfObjectTypeSubscriptionContainer
    fun dateProvider(): DateProvider
    fun logger(): Logger
    fun spaceManager(): SpaceManager
    fun userPermissionProvider(): UserPermissionProvider
    fun notificationChannel(): NotificationsChannel
    fun activeSpaceMembers() : ActiveSpaceMemberSubscriptionContainer
    fun analyticSpaceHelperDelegate(): AnalyticSpaceHelperDelegate
    fun storeOfRelations(): StoreOfRelations
    fun spaceViewSubscriptionContainer(): SpaceViewSubscriptionContainer
    fun participantSubscriptionContainer(): ParticipantSubscriptionContainer
    fun featureToggles(): FeatureToggles
    fun payloadDelegator(): PayloadDelegator
    fun fieldParser(): FieldParser
    fun notificationPermissionManager(): NotificationPermissionManager
    fun provideChatEventChannel(): ChatEventChannel
    fun provideChatPreviewContainer(): ChatPreviewContainer
    fun clipboard(): com.aparokio.aparok.domain.clipboard.Clipboard
    @Named(DEFAULT_APP_COROUTINE_SCOPE) fun scope(): CoroutineScope
    fun stringResProvider() : StringResourceProvider
    fun setSpaceNotificationMode(): SetSpaceNotificationMode
    fun fileSharer(): FileSharer
}
