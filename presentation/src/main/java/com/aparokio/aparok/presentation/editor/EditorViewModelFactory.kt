package com.aparokio.aparok.presentation.editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.Block
import com.aparokio.aparok.core_models.Event
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_utils.tools.FeatureToggles
import com.aparokio.aparok.domain.ai.TypeSuggestionEngine
import com.aparokio.aparok.domain.auth.interactor.ClearLastOpenedObject
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.interactor.RemoveLinkMark
import com.aparokio.aparok.domain.block.interactor.SetObjectType
import com.aparokio.aparok.domain.block.interactor.UpdateLinkMarks
import com.aparokio.aparok.domain.block.interactor.sets.CreateObjectSet
import com.aparokio.aparok.domain.block.interactor.sets.GetObjectTypes
import com.aparokio.aparok.domain.cover.SetDocCoverImage
import com.aparokio.aparok.domain.chats.GetChatMessages
import com.aparokio.aparok.domain.discussions.AddDiscussion
import com.aparokio.aparok.domain.event.interactor.InterceptEvents
import com.aparokio.aparok.domain.event.interactor.SpaceSyncAndP2PStatusProvider
import com.aparokio.aparok.domain.icon.SetDocumentImageIcon
import com.aparokio.aparok.domain.launch.GetDefaultObjectType
import com.aparokio.aparok.domain.library.StorelessSubscriptionContainer
import com.aparokio.aparok.domain.misc.DateProvider
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.networkmode.GetNetworkMode
import com.aparokio.aparok.domain.`object`.ConvertObjectToCollection
import com.aparokio.aparok.domain.`object`.ConvertObjectToSet
import com.aparokio.aparok.domain.`object`.UpdateDetail
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.domain.page.CloseObject
import com.aparokio.aparok.domain.page.CreateBlockLinkWithObject
import com.aparokio.aparok.domain.page.CreateObject
import com.aparokio.aparok.domain.page.CreateObjectAsMentionOrLink
import com.aparokio.aparok.domain.page.OpenPage
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.domain.relations.AddRelationToObject
import com.aparokio.aparok.domain.search.SearchObjects
import com.aparokio.aparok.domain.sets.FindObjectSetForType
import com.aparokio.aparok.domain.templates.ApplyTemplate
import com.aparokio.aparok.domain.unsplash.DownloadUnsplashImage
import com.aparokio.aparok.domain.workspace.InterceptFileLimitEvents
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.common.Action
import com.aparokio.aparok.presentation.common.Delegator
import com.aparokio.aparok.presentation.common.StateReducer
import com.aparokio.aparok.presentation.editor.editor.Orchestrator
import com.aparokio.aparok.presentation.editor.editor.table.EditorTableDelegate
import com.aparokio.aparok.presentation.editor.render.DefaultBlockViewRenderer
import com.aparokio.aparok.presentation.navigation.backstack.BackHistoryDelegate
import com.aparokio.aparok.presentation.vault.ExitToVaultDelegate
import com.aparokio.aparok.presentation.templates.ObjectTypeTemplatesContainer
import com.aparokio.aparok.presentation.util.CopyFileToCacheDirectory
import com.aparokio.aparok.presentation.util.Dispatcher
import com.aparokio.aparok.presentation.util.UrlHelper
import javax.inject.Inject

open class EditorViewModelFactory @Inject constructor(
    private val params: EditorViewModel.Params,
    private val permissions: UserPermissionProvider,
    private val openPage: OpenPage,
    private val closeObject: CloseObject,
    private val createObjectSet: CreateObjectSet,
    private val createBlockLinkWithObject: CreateBlockLinkWithObject,
    private val createObjectAsMentionOrLink: CreateObjectAsMentionOrLink,
    private val interceptEvents: InterceptEvents,
    private val updateLinkMarks: UpdateLinkMarks,
    private val removeLinkMark: RemoveLinkMark,
    private val documentEventReducer: StateReducer<List<Block>, Event>,
    private val urlBuilder: UrlBuilder,
    private val renderer: DefaultBlockViewRenderer,
    private val orchestrator: Orchestrator,
    private val analytics: Analytics,
    private val dispatcher: Dispatcher<Payload>,
    private val delegator: Delegator<Action>,
    private val updateDetail: UpdateDetail,
    private val searchObjects: SearchObjects,
    private val getDefaultObjectType: GetDefaultObjectType,
    private val findObjectSetForType: FindObjectSetForType,
    private val copyFileToCacheDirectory: CopyFileToCacheDirectory,
    private val downloadUnsplashImage: DownloadUnsplashImage,
    private val setDocCoverImage: SetDocCoverImage,
    private val setDocImageIcon: SetDocumentImageIcon,
    private val createObject: CreateObject,
    private val objectToSet: ConvertObjectToSet,
    private val storeOfRelations: StoreOfRelations,
    private val storeOfObjectTypes: StoreOfObjectTypes,
    private val featureToggles: FeatureToggles,
    private val tableDelegate: EditorTableDelegate,
    private val spaceManager: SpaceManager,
    private val getObjectTypes: GetObjectTypes,
    private val objectToCollection: ConvertObjectToCollection,
    private val interceptFileLimitEvents: InterceptFileLimitEvents,
    private val addRelationToObject: AddRelationToObject,
    private val applyTemplate: ApplyTemplate,
    private val setObjectType: SetObjectType,
    private val templatesContainer: ObjectTypeTemplatesContainer,
    private val storelessSubscriptionContainer: StorelessSubscriptionContainer,
    private val dispatchers: AppCoroutineDispatchers,
    private val getNetworkMode: GetNetworkMode,
    private val clearLastOpenedObject: ClearLastOpenedObject,
    private val analyticSpaceHelperDelegate: AnalyticSpaceHelperDelegate,
    private val syncStatusProvider: SpaceSyncAndP2PStatusProvider,
    private val fieldParser : FieldParser,
    private val dateProvider: DateProvider,
    private val spaceViews: SpaceViewSubscriptionContainer,
    private val urlHelper: UrlHelper,
    private val addDiscussion: AddDiscussion,
    private val getChatMessages: GetChatMessages,
    private val backHistoryDelegate: BackHistoryDelegate,
    private val exitToVaultDelegate: ExitToVaultDelegate,
    private val typeSuggestionEngine: TypeSuggestionEngine = TypeSuggestionEngine.NoOp
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EditorViewModel(
            vmParams = params,
            permissions = permissions,
            openPage = openPage,
            closePage = closeObject,
            createBlockLinkWithObject = createBlockLinkWithObject,
            createObjectAsMentionOrLink = createObjectAsMentionOrLink,
            interceptEvents = interceptEvents,
            updateLinkMarks = updateLinkMarks,
            removeLinkMark = removeLinkMark,
            reducer = documentEventReducer,
            urlBuilder = urlBuilder,
            renderer = renderer,
            orchestrator = orchestrator,
            analytics = analytics,
            dispatcher = dispatcher,
            delegator = delegator,
            updateDetail = updateDetail,
            searchObjects = searchObjects,
            getDefaultObjectType = getDefaultObjectType,
            findObjectSetForType = findObjectSetForType,
            createObjectSet = createObjectSet,
            copyFileToCache = copyFileToCacheDirectory,
            downloadUnsplashImage = downloadUnsplashImage,
            setDocCoverImage = setDocCoverImage,
            setDocImageIcon = setDocImageIcon,
            createObject = createObject,
            objectToSet = objectToSet,
            objectToCollection = objectToCollection,
            storeOfRelations = storeOfRelations,
            storeOfObjectTypes = storeOfObjectTypes,
            featureToggles = featureToggles,
            tableDelegate = tableDelegate,
            spaceManager = spaceManager,
            getObjectTypes = getObjectTypes,
            interceptFileLimitEvents = interceptFileLimitEvents,
            addRelationToObject = addRelationToObject,
            applyTemplate = applyTemplate,
            setObjectType = setObjectType,
            templatesContainer = templatesContainer,
            dispatchers = dispatchers,
            storelessSubscriptionContainer = storelessSubscriptionContainer,
            spaceSyncAndP2PStatusProvider = syncStatusProvider,
            getNetworkMode = getNetworkMode,
            analyticSpaceHelperDelegate = analyticSpaceHelperDelegate,
            clearLastOpenedObject = clearLastOpenedObject,
            fieldParser = fieldParser,
            dateProvider = dateProvider,
            spaceViews = spaceViews,
            urlHelper = urlHelper,
            addDiscussion = addDiscussion,
            getChatMessages = getChatMessages,
            backHistoryDelegate = backHistoryDelegate,
            exitToVaultDelegate = exitToVaultDelegate,
            typeSuggestionEngine = typeSuggestionEngine
        ) as T
    }
}
