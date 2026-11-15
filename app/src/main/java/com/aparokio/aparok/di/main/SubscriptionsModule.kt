package com.aparokio.aparok.di.main

import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.device.DeviceTokenStoringServiceImpl
import com.aparokio.aparok.di.main.ConfigModule.DEFAULT_APP_COROUTINE_SCOPE
import com.aparokio.aparok.domain.account.AwaitAccountStartManager
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.interactor.sets.GetObjectTypes
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.chats.ChatEventChannel
import com.aparokio.aparok.domain.chats.ChatPreviewContainer
import com.aparokio.aparok.domain.chats.ChatsDetailsSubscriptionContainer
import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.domain.debugging.DebugAccountSelectTrace
import com.aparokio.aparok.domain.debugging.Logger
import com.aparokio.aparok.domain.deeplink.PendingIntentStore
import com.aparokio.aparok.domain.device.DeviceTokenStoringService
import com.aparokio.aparok.domain.device.NetworkConnectionStatus
import com.aparokio.aparok.domain.event.interactor.SpaceSyncAndP2PStatusProvider
import com.aparokio.aparok.domain.library.CrossSpaceSubscriptionContainer
import com.aparokio.aparok.domain.library.StorelessSubscriptionContainer
import com.aparokio.aparok.domain.misc.DeepLinkResolver
import com.aparokio.aparok.domain.multiplayer.ActiveSpaceMemberSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.DefaultUserPermissionProvider
import com.aparokio.aparok.domain.multiplayer.ParticipantSubscriptionContainer
import com.aparokio.aparok.domain.objects.CrossSpaceObjectTypesContainer
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.notifications.PushKeyProvider
import com.aparokio.aparok.domain.notifications.RegisterDeviceToken
import com.aparokio.aparok.domain.objects.DefaultStoreOfObjectTypes
import com.aparokio.aparok.domain.objects.DefaultStoreOfRelationOptions
import com.aparokio.aparok.domain.objects.DefaultStoreOfRelations
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.objects.StoreOfRelationOptions
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.domain.search.HasInstanceOfObjectTypeSubscriptionContainer
import com.aparokio.aparok.domain.search.HasInstanceOfObjectTypeSubscriptionManager
import com.aparokio.aparok.domain.search.ObjectTypesSubscriptionContainer
import com.aparokio.aparok.domain.search.ObjectTypesSubscriptionManager
import com.aparokio.aparok.domain.search.ProfileSubscriptionManager
import com.aparokio.aparok.domain.search.RelationOptionsSubscriptionContainer
import com.aparokio.aparok.domain.search.RelationOptionsSubscriptionManager
import com.aparokio.aparok.domain.search.RelationsSubscriptionContainer
import com.aparokio.aparok.domain.search.RelationsSubscriptionManager
import com.aparokio.aparok.domain.search.SearchObjects
import com.aparokio.aparok.domain.search.SubscriptionEventChannel
import com.aparokio.aparok.domain.spaces.SpaceDeletedStatusWatcher
import com.aparokio.aparok.domain.subscriptions.GlobalSubscriptionManager
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.domain.workspace.SyncAndP2PStatusChannel
import com.aparokio.aparok.other.DefaultDeepLinkResolver
import com.aparokio.aparok.presentation.sync.SpaceSyncAndP2PStatusProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope

@Module
object SubscriptionsModule {

    @JvmStatic
    @Provides
    @Singleton
    fun relationsSubscriptionContainer(
        repo: BlockRepository,
        channel: SubscriptionEventChannel,
        dispatchers: AppCoroutineDispatchers,
        store: StoreOfRelations,
        logger: Logger
    ): RelationsSubscriptionContainer = RelationsSubscriptionContainer(
        repo = repo,
        channel = channel,
        store = store,
        dispatchers = dispatchers,
        logger = logger
    )

    @JvmStatic
    @Provides
    @Singleton
    fun objectTypesSubscriptionContainer(
        repo: BlockRepository,
        channel: SubscriptionEventChannel,
        dispatchers: AppCoroutineDispatchers,
        store: StoreOfObjectTypes,
        logger: Logger
    ): ObjectTypesSubscriptionContainer = ObjectTypesSubscriptionContainer(
        repo = repo,
        channel = channel,
        store = store,
        dispatchers = dispatchers,
        logger = logger
    )

    @JvmStatic
    @Provides
    @Singleton
    fun relationOptionsSubscriptionContainer(
        repo: BlockRepository,
        channel: SubscriptionEventChannel,
        dispatchers: AppCoroutineDispatchers,
        store: StoreOfRelationOptions,
        logger: Logger
    ): RelationOptionsSubscriptionContainer = RelationOptionsSubscriptionContainer(
        repo = repo,
        channel = channel,
        store = store,
        dispatchers = dispatchers,
        logger = logger
    )

    @JvmStatic
    @Provides
    @Singleton
    fun relationsStore(): StoreOfRelations = DefaultStoreOfRelations()

    @JvmStatic
    @Provides
    @Singleton
    fun objectTypesStore(): StoreOfObjectTypes = DefaultStoreOfObjectTypes()

    @JvmStatic
    @Provides
    @Singleton
    fun relationOptionsStore(): StoreOfRelationOptions = DefaultStoreOfRelationOptions()

    @JvmStatic
    @Provides
    @Singleton
    fun relationsSubscriptionManager(
        subscription: RelationsSubscriptionContainer,
        spaceManager: SpaceManager,
    ): RelationsSubscriptionManager = RelationsSubscriptionManager(
        container = subscription,
        spaceManager = spaceManager
    )

    @JvmStatic
    @Provides
    @Singleton
    fun objectTypesSubscriptionManager(
        subscription: ObjectTypesSubscriptionContainer,
        spaceManager: SpaceManager
    ): ObjectTypesSubscriptionManager = ObjectTypesSubscriptionManager(
        container = subscription,
        spaceManager = spaceManager
    )

    @JvmStatic
    @Provides
    @Singleton
    fun hasInstanceOfObjectTypeSubscriptionContainer(
        storelessContainer: StorelessSubscriptionContainer,
        storeOfObjectTypes: StoreOfObjectTypes,
        dispatchers: AppCoroutineDispatchers,
        logger: Logger,
        @Named(DEFAULT_APP_COROUTINE_SCOPE) scope: CoroutineScope
    ): HasInstanceOfObjectTypeSubscriptionContainer = HasInstanceOfObjectTypeSubscriptionContainer(
        storelessContainer = storelessContainer,
        storeOfObjectTypes = storeOfObjectTypes,
        dispatchers = dispatchers,
        logger = logger,
        scope = scope
    )

    @JvmStatic
    @Provides
    @Singleton
    fun hasInstanceOfObjectTypeSubscriptionManager(
        container: HasInstanceOfObjectTypeSubscriptionContainer,
        spaceManager: SpaceManager
    ): HasInstanceOfObjectTypeSubscriptionManager = HasInstanceOfObjectTypeSubscriptionManager(
        container = container,
        spaceManager = spaceManager
    )

    @JvmStatic
    @Provides
    @Singleton
    fun relationOptionsSubscriptionManager(
        subscription: RelationOptionsSubscriptionContainer,
        spaceManager: SpaceManager
    ): RelationOptionsSubscriptionManager = RelationOptionsSubscriptionManager(
        container = subscription,
        spaceManager = spaceManager
    )

    @JvmStatic
    @Provides
    @Singleton
    fun spaceStatusWatcher(
        spaceManager: SpaceManager,
        dispatchers: AppCoroutineDispatchers,
        @Named(DEFAULT_APP_COROUTINE_SCOPE) scope: CoroutineScope,
        configStorage: ConfigStorage,
        container: StorelessSubscriptionContainer,
        logger: Logger
    ): SpaceDeletedStatusWatcher = SpaceDeletedStatusWatcher(
        spaceManager = spaceManager,
        dispatchers = dispatchers,
        configStorage = configStorage,
        scope = scope,
        container = container,
        logger = logger
    )

    @JvmStatic
    @Provides
    @Singleton
    fun userPermissionProvider(
        dispatchers: AppCoroutineDispatchers,
        @Named(DEFAULT_APP_COROUTINE_SCOPE) scope: CoroutineScope,
        container: StorelessSubscriptionContainer,
        repo: AuthRepository,
        logger: Logger,
        spaceViewSubscriptionContainer: SpaceViewSubscriptionContainer
    ): UserPermissionProvider = DefaultUserPermissionProvider(
        dispatchers = dispatchers,
        scope = scope,
        container = container,
        repo = repo,
        logger = logger,
        spaceViewSubscriptionContainer = spaceViewSubscriptionContainer
    )

    @JvmStatic
    @Provides
    @Singleton
    fun spaceViewSubscriptionContainer(
        dispatchers: AppCoroutineDispatchers,
        @Named(DEFAULT_APP_COROUTINE_SCOPE) scope: CoroutineScope,
        container: StorelessSubscriptionContainer,
        awaitAccountStartManager: AwaitAccountStartManager,
        logger: Logger,
        configStorage: ConfigStorage
    ): SpaceViewSubscriptionContainer = SpaceViewSubscriptionContainer.Default(
        dispatchers = dispatchers,
        scope = scope,
        container = container,
        awaitAccountStart = awaitAccountStartManager,
        logger = logger,
        config = configStorage
    )

    @JvmStatic
    @Provides
    @Singleton
    fun chatsViewSubscriptionContainer(
        dispatchers: AppCoroutineDispatchers,
        @Named(DEFAULT_APP_COROUTINE_SCOPE) scope: CoroutineScope,
        container: CrossSpaceSubscriptionContainer,
        awaitAccountStartManager: AwaitAccountStartManager,
        logger: Logger,
    ): ChatsDetailsSubscriptionContainer = ChatsDetailsSubscriptionContainer.Default(
        dispatchers = dispatchers,
        scope = scope,
        container = container,
        awaitAccountStart = awaitAccountStartManager,
        logger = logger,
    )

    @JvmStatic
    @Provides
    @Singleton
    fun participantSubscriptionContainer(
        dispatchers: AppCoroutineDispatchers,
        @Named(DEFAULT_APP_COROUTINE_SCOPE) scope: CoroutineScope,
        container: CrossSpaceSubscriptionContainer,
        awaitAccountStartManager: AwaitAccountStartManager,
        logger: Logger,
    ): ParticipantSubscriptionContainer = ParticipantSubscriptionContainer.Default(
        dispatchers = dispatchers,
        scope = scope,
        container = container,
        awaitAccountStart = awaitAccountStartManager,
        logger = logger,
    )

    @JvmStatic
    @Provides
    @Singleton
    fun crossSpaceObjectTypesContainer(
        dispatchers: AppCoroutineDispatchers,
        @Named(DEFAULT_APP_COROUTINE_SCOPE) scope: CoroutineScope,
        container: CrossSpaceSubscriptionContainer,
        awaitAccountStartManager: AwaitAccountStartManager,
        logger: Logger,
    ): CrossSpaceObjectTypesContainer = CrossSpaceObjectTypesContainer.Default(
        dispatchers = dispatchers,
        scope = scope,
        container = container,
        awaitAccountStart = awaitAccountStartManager,
        logger = logger,
    )

    @JvmStatic
    @Provides
    @Singleton
    fun provideCrossSpaceSubscriptionContainer(
        repo: BlockRepository,
        channel: SubscriptionEventChannel,
        dispatchers: AppCoroutineDispatchers,
        logger: Logger
    ): CrossSpaceSubscriptionContainer =
        CrossSpaceSubscriptionContainer.Impl(repo, channel, dispatchers, logger)

    @JvmStatic
    @Provides
    @Singleton
    fun provideChatPreviewContainer(
        @Named(DEFAULT_APP_COROUTINE_SCOPE) scope: CoroutineScope,
        dispatchers: AppCoroutineDispatchers,
        repo: BlockRepository,
        logger: Logger,
        events: ChatEventChannel,
        subscription: StorelessSubscriptionContainer,
        awaitAccountStartManager: AwaitAccountStartManager
    ): ChatPreviewContainer = ChatPreviewContainer.Default(
        repo = repo,
        dispatchers = dispatchers,
        scope = scope,
        logger = logger,
        events = events,
        subscription = subscription,
        awaitAccountStart = awaitAccountStartManager
    )

    @JvmStatic
    @Provides
    @Singleton
    fun profileSubscriptionManager(
        dispatchers: AppCoroutineDispatchers,
        @Named(DEFAULT_APP_COROUTINE_SCOPE) scope: CoroutineScope,
        container: StorelessSubscriptionContainer,
        awaitAccountStartManager: AwaitAccountStartManager,
        configStorage: ConfigStorage,
        logger: Logger
    ): ProfileSubscriptionManager = ProfileSubscriptionManager.Default(
        dispatchers = dispatchers,
        scope = scope,
        container = container,
        awaitAccountStartManager = awaitAccountStartManager,
        configStorage = configStorage,
        logger = logger
    )

    @JvmStatic
    @Provides
    @Singleton
    fun activeSpaceMemberSubscriptionContainer(
        dispatchers: AppCoroutineDispatchers,
        @Named(DEFAULT_APP_COROUTINE_SCOPE) scope: CoroutineScope,
        container: StorelessSubscriptionContainer,
        spaceManager: SpaceManager,
        awaitAccountStartManager: AwaitAccountStartManager,
        logger: Logger
    ): ActiveSpaceMemberSubscriptionContainer = ActiveSpaceMemberSubscriptionContainer.Default(
        dispatchers = dispatchers,
        scope = scope,
        container = container,
        manager = spaceManager,
        awaitAccountStart = awaitAccountStartManager,
        logger = logger
    )

    @JvmStatic
    @Provides
    @Singleton
    fun provideSpaceSyncStatusProvider(
        syncChannel: SyncAndP2PStatusChannel,
        spaceManager: SpaceManager
    ): SpaceSyncAndP2PStatusProvider = SpaceSyncAndP2PStatusProviderImpl(
        spaceManager = spaceManager,
        spaceSyncStatusChannel = syncChannel
    )

    @JvmStatic
    @Provides
    @Singleton
    fun globalSubscriptionManager(
        types: ObjectTypesSubscriptionManager,
        relations: RelationsSubscriptionManager,
        relationOptions: RelationOptionsSubscriptionManager,
        permissions: UserPermissionProvider,
        isSpaceDeleted: SpaceDeletedStatusWatcher,
        profileSubscriptionManager: ProfileSubscriptionManager,
        networkConnectionStatus: NetworkConnectionStatus,
        deviceTokenStoringService: DeviceTokenStoringService,
        pushKeyProvider: PushKeyProvider,
        hasInstanceOfType: HasInstanceOfObjectTypeSubscriptionManager,
        logger: Logger
    ): GlobalSubscriptionManager = GlobalSubscriptionManager.Default(
        types = types,
        relations = relations,
        relationOptions = relationOptions,
        permissions = permissions,
        isSpaceDeleted = isSpaceDeleted,
        profile = profileSubscriptionManager,
        networkConnectionStatus = networkConnectionStatus,
        deviceTokenStoringService = deviceTokenStoringService,
        pushKeyProvider = pushKeyProvider,
        hasInstanceOfType = hasInstanceOfType,
        logger = logger
    )

    @JvmStatic
    @Provides
    @Singleton
    fun searchObjects(
        repo: BlockRepository
    ): SearchObjects = SearchObjects(repo = repo)

    @JvmStatic
    @PerScreen
    @Singleton
    fun getObjectTypes(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): GetObjectTypes = GetObjectTypes(repo, dispatchers)

    @JvmStatic
    @Provides
    @Singleton
    fun provideDebugAccountSelectTrace(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): DebugAccountSelectTrace =
        DebugAccountSelectTrace(
            repo = repo,
            dispatchers = dispatchers
        )

    @JvmStatic
    @Provides
    @Singleton
    fun deviceTokenStoreService(
        registerDeviceToken: RegisterDeviceToken,
        dispatchers: AppCoroutineDispatchers,
        @Named(DEFAULT_APP_COROUTINE_SCOPE) scope: CoroutineScope
    ): DeviceTokenStoringService = DeviceTokenStoringServiceImpl(
        registerDeviceToken = registerDeviceToken,
        dispatchers = dispatchers,
        scope = scope
    )

    @JvmStatic
    @Provides
    @Singleton
    fun provideDeeplinkResolver() : DeepLinkResolver = DefaultDeepLinkResolver

    @JvmStatic
    @Provides
    @Singleton
    fun providePendingIntentStore(): PendingIntentStore = PendingIntentStore()
}