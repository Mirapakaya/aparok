package com.aparokio.aparok.feature_chats.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.core_utils.notifications.NotificationPermissionManager
import com.aparokio.aparok.domain.auth.interactor.GetAccount
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.chats.AddChatMessage
import com.aparokio.aparok.domain.chats.ChatContainer
import com.aparokio.aparok.domain.chats.DeleteChatMessage
import com.aparokio.aparok.domain.chats.EditChatMessage
import com.aparokio.aparok.domain.chats.ToggleChatMessageReaction
import com.aparokio.aparok.domain.invite.GetCurrentInviteAccessLevel
import com.aparokio.aparok.domain.invite.SpaceInviteLinkStore
import com.aparokio.aparok.domain.media.DiscardPreloadedFile
import com.aparokio.aparok.domain.media.PreloadFile
import com.aparokio.aparok.domain.media.UploadFile
import com.aparokio.aparok.domain.misc.DeepLinkResolver
import com.aparokio.aparok.domain.misc.GetLinkPreview
import com.aparokio.aparok.domain.multiplayer.ActiveSpaceMemberSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.notifications.NotificationBuilder
import com.aparokio.aparok.domain.notifications.SetChatNotificationMode
import com.aparokio.aparok.domain.`object`.GetObject
import com.aparokio.aparok.domain.`object`.SetObjectDetails
import com.aparokio.aparok.domain.objects.CreateObjectFromUrl
import com.aparokio.aparok.domain.objects.SetObjectListIsArchived
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.page.CreateObject
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.domain.spaces.SetSpaceDetails
import com.aparokio.aparok.feature_chats.tools.ClearChatsTempFolder
import com.aparokio.aparok.presentation.util.CopyFileToCacheDirectory
import com.aparokio.aparok.presentation.navigation.backstack.BackHistoryDelegate
import com.aparokio.aparok.presentation.vault.ExitToVaultDelegate
import com.aparokio.aparok.presentation.widgets.PinObjectAsWidgetDelegate
import javax.inject.Inject

class ChatViewModelFactory @Inject constructor(
    private val params: ChatViewModel.Params.Default,
    private val chatContainer: ChatContainer,
    private val addChatMessage: AddChatMessage,
    private val editChatMessage: EditChatMessage,
    private val deleteChatMessage: DeleteChatMessage,
    private val toggleChatMessageReaction: ToggleChatMessageReaction,
    private val members: ActiveSpaceMemberSubscriptionContainer,
    private val getAccount: GetAccount,
    private val urlBuilder: UrlBuilder,
    private val spaceViews: SpaceViewSubscriptionContainer,
    private val dispatchers: AppCoroutineDispatchers,
    private val uploadFile: UploadFile,
    private val preloadFile: PreloadFile,
    private val discardPreloadedFile: DiscardPreloadedFile,
    private val storeOfObjectTypes: StoreOfObjectTypes,
    private val copyFileToCacheDirectory: CopyFileToCacheDirectory,
    private val exitToVaultDelegate: ExitToVaultDelegate,
    private val getLinkPreview: GetLinkPreview,
    private val createObjectFromUrl: CreateObjectFromUrl,
    private val notificationPermissionManager: NotificationPermissionManager,
    private val spacePermissionProvider: UserPermissionProvider,
    private val notificationBuilder: NotificationBuilder,
    private val clearChatsTempFolder: ClearChatsTempFolder,
    private val createObject: CreateObject,
    private val getObject: GetObject,
    private val analytics: Analytics,
    private val spaceInviteLinkStore: SpaceInviteLinkStore,
    private val getCurrentInviteAccessLevel: GetCurrentInviteAccessLevel,
    private val pinObjectAsWidgetDelegate: PinObjectAsWidgetDelegate,
    private val setObjectListIsArchived: SetObjectListIsArchived,
    private val setObjectDetails: SetObjectDetails,
    private val setSpaceDetails: SetSpaceDetails,
    private val setChatNotificationMode: SetChatNotificationMode,
    private val fieldParser: FieldParser,
    private val deepLinkResolver: DeepLinkResolver,
    private val backHistoryDelegate: BackHistoryDelegate
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = ChatViewModel(
        vmParams = params,
        chatContainer = chatContainer,
        addChatMessage = addChatMessage,
        editChatMessage = editChatMessage,
        deleteChatMessage = deleteChatMessage,
        toggleChatMessageReaction = toggleChatMessageReaction,
        members = members,
        getAccount = getAccount,
        urlBuilder = urlBuilder,
        spaceViews = spaceViews,
        dispatchers = dispatchers,
        uploadFile = uploadFile,
        storeOfObjectTypes = storeOfObjectTypes,
        copyFileToCacheDirectory = copyFileToCacheDirectory,
        exitToVaultDelegate = exitToVaultDelegate,
        getLinkPreview = getLinkPreview,
        createObjectFromUrl = createObjectFromUrl,
        notificationPermissionManager = notificationPermissionManager,
        spacePermissionProvider = spacePermissionProvider,
        notificationBuilder = notificationBuilder,
        clearChatsTempFolder = clearChatsTempFolder,
        createObject = createObject,
        getObject = getObject,
        analytics = analytics,
        preloadFile = preloadFile,
        discardPreloadedFile = discardPreloadedFile,
        spaceInviteLinkStore = spaceInviteLinkStore,
        getCurrentInviteAccessLevel = getCurrentInviteAccessLevel,
        pinObjectAsWidgetDelegate = pinObjectAsWidgetDelegate,
        setObjectListIsArchived = setObjectListIsArchived,
        setObjectDetails = setObjectDetails,
        setSpaceDetails = setSpaceDetails,
        setChatNotificationMode = setChatNotificationMode,
        fieldParser = fieldParser,
        deepLinkResolver = deepLinkResolver,
        backHistoryDelegate = backHistoryDelegate
    ) as T
}
