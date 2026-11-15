package com.aparokio.aparok.feature_vault.presentation

import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.ObjectWrapper
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.core_utils.notifications.NotificationPermissionManager
import com.aparokio.aparok.core_utils.tools.AppInfo
import com.aparokio.aparok.domain.chats.ChatPreviewContainer
import com.aparokio.aparok.domain.chats.ChatsDetailsSubscriptionContainer
import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.deeplink.PendingIntentStore
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
import com.aparokio.aparok.domain.spaces.CreateSpace
import com.aparokio.aparok.domain.spaces.DeleteSpace
import com.aparokio.aparok.domain.spaces.ResolveSpaceHomepage
import com.aparokio.aparok.domain.spaces.SetHomepage
import com.aparokio.aparok.domain.spaces.SaveCurrentSpace
import com.aparokio.aparok.domain.vault.SetCreateSpaceBadgeSeen
import com.aparokio.aparok.domain.vault.SetSpaceOrder
import com.aparokio.aparok.domain.vault.SetVaultSearchHighlightSeen
import com.aparokio.aparok.domain.vault.ShouldShowCreateSpaceBadge
import com.aparokio.aparok.domain.vault.UnpinSpace
import com.aparokio.aparok.domain.wallpaper.GetSpaceWallpapers
import com.aparokio.aparok.domain.network.NetworkModeProvider
import com.aparokio.aparok.domain.payments.GetMembershipFeatures
import com.aparokio.aparok.domain.widgets.OsWidgetDataViewSync
import com.aparokio.aparok.domain.widgets.OsWidgetSpacesSync
import com.aparokio.aparok.domain.workspace.DeepLinkToObjectDelegate
import com.aparokio.aparok.domain.workspace.SpaceManager
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.mockito.kotlin.any
import org.mockito.kotlin.mock

object VaultViewModelFabric {
    fun create(
        spaceViewSubscriptionContainer: SpaceViewSubscriptionContainer = mock(),
        urlBuilder: UrlBuilder = mock(),
        spaceManager: SpaceManager = mock(),
        saveCurrentSpace: SaveCurrentSpace = mock(),
        analytics: Analytics = mock(),
        deepLinkToObjectDelegate: DeepLinkToObjectDelegate = mock(),
        appActionManager: AppActionManager = mock(),
        spaceInviteResolver: SpaceInviteResolver = mock(),
        profileContainer: ProfileSubscriptionManager = mock {
            on { observe() }.thenReturn(flowOf(ObjectWrapper.Basic(emptyMap())))
        },
        chatPreviewContainer: ChatPreviewContainer = mock(),
        chatsDetailsContainer: ChatsDetailsSubscriptionContainer = mock {
            on { observe() }.thenReturn(flowOf(emptyList()))
        },
        pendingIntentStore: PendingIntentStore = mock(),
        stringResourceProvider: StringResourceProvider = mock(),
        dateProvider: DateProvider = mock(),
        fieldParser: FieldParser = mock(),
        storeOfObjectTypes: StoreOfObjectTypes = mock(),
        setSpaceNotificationMode: SetSpaceNotificationMode = mock(),
        deleteSpace: DeleteSpace = mock(),
        userPermissionProvider: UserPermissionProvider = mock(),
        notificationPermissionManager: NotificationPermissionManager = mock(),
        unpinSpace: UnpinSpace = mock(),
        setSpaceOrder: SetSpaceOrder = mock(),
        getSpaceWallpaper: GetSpaceWallpapers = mock(),
        searchOneToOneChatByIdentity: SearchOneToOneChatByIdentity = mock(),
        shouldShowCreateSpaceBadge: ShouldShowCreateSpaceBadge = mock {
            on { runBlocking { async(any()) } }.thenReturn(com.aparokio.aparok.domain.base.Resultat.Success(false))
        },
        setCreateSpaceBadgeSeen: SetCreateSpaceBadgeSeen = mock(),
        setVaultSearchHighlightSeen: SetVaultSearchHighlightSeen = mock(),
        appInfo: AppInfo = mock {
            on { versionName }.thenReturn("1.0.0-test")
        },
        participantSubscriptionContainer: ParticipantSubscriptionContainer = mock(),
        configStorage: ConfigStorage = mock {
            on { getOrNull() }.thenReturn(null)
            on { getAccountId() }.thenReturn(null)
        },
        createSpace: CreateSpace = mock(),
        deepLinkResolver: DeepLinkResolver = mock(),
        osWidgetSpacesSync: OsWidgetSpacesSync = mock(),
        osWidgetDataViewSync: OsWidgetDataViewSync = mock(),
        networkModeProvider: NetworkModeProvider = mock(),
        getMembershipFeatures: GetMembershipFeatures = mock(),
        resolveSpaceHomepage: ResolveSpaceHomepage = mock(),
        setHomepage: SetHomepage = mock(),
        userSettingsRepository: UserSettingsRepository = mock {
            on { observeCompactModeEnabled() }.thenReturn(flowOf(false))
            on { observeQuickCaptureEnabled() }.thenReturn(flowOf(false))
            onBlocking { getVaultSortKeys() }.thenReturn(emptyMap())
        }
    ): VaultViewModel = VaultViewModel(
        spaceViewSubscriptionContainer = spaceViewSubscriptionContainer,
        urlBuilder = urlBuilder,
        spaceManager = spaceManager,
        saveCurrentSpace = saveCurrentSpace,
        analytics = analytics,
        deepLinkToObjectDelegate = deepLinkToObjectDelegate,
        appActionManager = appActionManager,
        spaceInviteResolver = spaceInviteResolver,
        profileContainer = profileContainer,
        chatPreviewContainer = chatPreviewContainer,
        chatsDetailsContainer = chatsDetailsContainer,
        pendingIntentStore = pendingIntentStore,
        stringResourceProvider = stringResourceProvider,
        dateProvider = dateProvider,
        fieldParser = fieldParser,
        storeOfObjectTypes = storeOfObjectTypes,
        setSpaceNotificationMode = setSpaceNotificationMode,
        deleteSpace = deleteSpace,
        userPermissionProvider = userPermissionProvider,
        notificationPermissionManager = notificationPermissionManager,
        unpinSpace = unpinSpace,
        setSpaceOrder = setSpaceOrder,
        getSpaceWallpapers = getSpaceWallpaper,
        shouldShowCreateSpaceBadge = shouldShowCreateSpaceBadge,
        setCreateSpaceBadgeSeen = setCreateSpaceBadgeSeen,
        setVaultSearchHighlightSeen = setVaultSearchHighlightSeen,
        appInfo = appInfo,
        participantContainer = participantSubscriptionContainer,
        searchOneToOneChatByIdentity = searchOneToOneChatByIdentity,
        createSpace = createSpace,
        deepLinkResolver = deepLinkResolver,
        configStorage = configStorage,
        osWidgetSpacesSync = osWidgetSpacesSync,
        osWidgetDataViewSync = osWidgetDataViewSync,
        networkModeProvider = networkModeProvider,
        getMembershipFeatures = getMembershipFeatures,
        resolveSpaceHomepage = resolveSpaceHomepage,
        setHomepage = setHomepage,
        userSettingsRepository = userSettingsRepository
    )
}
