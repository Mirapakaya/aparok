package com.aparokio.aparok.feature_vault.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
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
import com.aparokio.aparok.domain.spaces.SaveCurrentSpace
import com.aparokio.aparok.domain.spaces.SetHomepage
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
import javax.inject.Inject

class VaultViewModelFactory @Inject constructor(
    private val spaceViewSubscriptionContainer: SpaceViewSubscriptionContainer,
    private val urlBuilder: UrlBuilder,
    private val spaceManager: SpaceManager,
    private val saveCurrentSpace: SaveCurrentSpace,
    private val analytics: Analytics,
    private val deepLinkToObjectDelegate: DeepLinkToObjectDelegate,
    private val appActionManager: AppActionManager,
    private val spaceInviteResolver: SpaceInviteResolver,
    private val profileContainer: ProfileSubscriptionManager,
    private val chatPreviewContainer: ChatPreviewContainer,
    private val chatsDetailsContainer: ChatsDetailsSubscriptionContainer,
    private val participantContainer: ParticipantSubscriptionContainer,
    private val pendingIntentStore: PendingIntentStore,
    private val stringResourceProvider: StringResourceProvider,
    private val dateProvider: DateProvider,
    private val fieldParser: FieldParser,
    private val storeOfObjectTypes: StoreOfObjectTypes,
    private val setSpaceNotificationMode: SetSpaceNotificationMode,
    private val deleteSpace: DeleteSpace,
    private val userPermissionProvider: UserPermissionProvider,
    private val notificationPermissionManager: NotificationPermissionManager,
    private val unpinSpace: UnpinSpace,
    private val setSpaceOrder: SetSpaceOrder,
    private val getSpaceWallpapers: GetSpaceWallpapers,
    private val shouldShowCreateSpaceBadge: ShouldShowCreateSpaceBadge,
    private val setCreateSpaceBadgeSeen: SetCreateSpaceBadgeSeen,
    private val setVaultSearchHighlightSeen: SetVaultSearchHighlightSeen,
    private val appInfo: AppInfo,
    private val searchOneToOneChatByIdentity: SearchOneToOneChatByIdentity,
    private val createSpace: CreateSpace,
    private val deepLinkResolver: DeepLinkResolver,
    private val configStorage: ConfigStorage,
    private val osWidgetSpacesSync: OsWidgetSpacesSync,
    private val osWidgetDataViewSync: OsWidgetDataViewSync,
    private val networkModeProvider: NetworkModeProvider,
    private val getMembershipFeatures: GetMembershipFeatures,
    private val resolveSpaceHomepage: ResolveSpaceHomepage,
    private val setHomepage: SetHomepage,
    private val userSettingsRepository: UserSettingsRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ) = VaultViewModel(
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
        participantContainer = participantContainer,
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
        getSpaceWallpapers = getSpaceWallpapers,
        shouldShowCreateSpaceBadge = shouldShowCreateSpaceBadge,
        setCreateSpaceBadgeSeen = setCreateSpaceBadgeSeen,
        setVaultSearchHighlightSeen = setVaultSearchHighlightSeen,
        appInfo = appInfo,
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
    ) as T
}
