package com.aparokio.aparok.presentation.sets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.block.interactor.CreateBlock
import com.aparokio.aparok.domain.block.interactor.UpdateText
import com.aparokio.aparok.domain.collections.AddObjectToCollection
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.collections.RemoveObjectFromCollection
import com.aparokio.aparok.domain.cover.SetDocCoverImage
import com.aparokio.aparok.domain.dataview.SetDataViewProperties
import com.aparokio.aparok.domain.dataview.interactor.SetDataViewObjectOrder
import com.aparokio.aparok.domain.search.BoardGroupSubscriptionContainer
import com.aparokio.aparok.domain.library.StorelessSubscriptionContainer
import com.aparokio.aparok.domain.search.BoardRecordsSubscriptionContainer
import com.aparokio.aparok.domain.discussions.AddDiscussion
import com.aparokio.aparok.domain.dataview.interactor.CreateDataViewObject
import com.aparokio.aparok.domain.event.interactor.InterceptEvents
import com.aparokio.aparok.domain.event.interactor.SpaceSyncAndP2PStatusProvider
import com.aparokio.aparok.domain.launch.GetDefaultObjectType
import com.aparokio.aparok.domain.misc.DateProvider
import com.aparokio.aparok.domain.misc.DeepLinkResolver
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.`object`.ConvertObjectToCollection
import com.aparokio.aparok.domain.`object`.DuplicateObjects
import com.aparokio.aparok.domain.`object`.UpdateDetail
import com.aparokio.aparok.domain.objects.ObjectStore
import com.aparokio.aparok.domain.objects.SetObjectListIsArchived
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.domain.page.CloseObject
import com.aparokio.aparok.domain.page.CreateObject
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.domain.resources.StringResourceProvider
import com.aparokio.aparok.domain.search.DataViewSubscriptionContainer
import com.aparokio.aparok.domain.sets.OpenObjectSet
import com.aparokio.aparok.domain.sets.SetQueryToObjectSet
import com.aparokio.aparok.domain.templates.CreateTemplate
import com.aparokio.aparok.domain.unsplash.DownloadUnsplashImage
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.emojifier.data.EmojiProvider
import com.aparokio.aparok.emojifier.suggest.EmojiSuggester
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.common.Action
import com.aparokio.aparok.presentation.common.Delegator
import com.aparokio.aparok.presentation.editor.cover.CoverImageHashProvider
import com.aparokio.aparok.presentation.navigation.backstack.BackHistoryDelegate
import com.aparokio.aparok.presentation.sets.state.ObjectStateReducer
import com.aparokio.aparok.presentation.vault.ExitToVaultDelegate
import com.aparokio.aparok.presentation.sets.subscription.DataViewSubscription
import com.aparokio.aparok.presentation.sets.viewer.ViewerDelegate
import com.aparokio.aparok.presentation.templates.ObjectTypeTemplatesContainer
import com.aparokio.aparok.presentation.util.Dispatcher

class ObjectSetViewModelFactory(
    private val params: ObjectSetViewModel.Params,
    private val permissions: UserPermissionProvider,
    private val openObjectSet: OpenObjectSet,
    private val closeObject: CloseObject,
    private val setObjectDetails: UpdateDetail,
    private val createDataViewObject: CreateDataViewObject,
    private val downloadUnsplashImage: DownloadUnsplashImage,
    private val setDocCoverImage: SetDocCoverImage,
    private val updateText: UpdateText,
    private val createBlock: CreateBlock,
    private val interceptEvents: InterceptEvents,
    private val dispatcher: Dispatcher<Payload>,
    private val delegator: Delegator<Action>,
    private val coverImageHashProvider: CoverImageHashProvider,
    private val urlBuilder: UrlBuilder,
    private val session: ObjectSetSession,
    private val analytics: Analytics,
    private val createObject: CreateObject,
    private val dataViewSubscriptionContainer: DataViewSubscriptionContainer,
    private val setQueryToObjectSet: SetQueryToObjectSet,
    private val database: ObjectSetDatabase,
    private val paginator: ObjectSetPaginator,
    private val storeOfRelations: StoreOfRelations,
    private val objectStateReducer: ObjectStateReducer,
    private val dataViewSubscription: DataViewSubscription,
    private val objectStore: ObjectStore,
    private val addObjectToCollection: AddObjectToCollection,
    private val objectToCollection: ConvertObjectToCollection,
    private val removeObjectFromCollection: RemoveObjectFromCollection,
    private val storeOfObjectTypes: StoreOfObjectTypes,
    private val duplicateObjects: DuplicateObjects,
    private val templatesContainer: ObjectTypeTemplatesContainer,
    private val setObjectListIsArchived: SetObjectListIsArchived,
    private val createTemplate: CreateTemplate,
    private val viewerDelegate: ViewerDelegate,
    private val spaceManager: SpaceManager,
    private val dateProvider: DateProvider,
    private val spaceSyncAndP2PStatusProvider: SpaceSyncAndP2PStatusProvider,
    private val analyticSpaceHelperDelegate: AnalyticSpaceHelperDelegate,
    private val fieldParser: FieldParser,
    private val spaceViews: SpaceViewSubscriptionContainer,
    private val deepLinkResolver: DeepLinkResolver,
    private val setDataViewProperties: SetDataViewProperties,
    private val setDataViewObjectOrder: SetDataViewObjectOrder,
    private val boardGroupSubscriptionContainer: BoardGroupSubscriptionContainer,
    private val boardRecordsSubscriptionContainer: BoardRecordsSubscriptionContainer,
    private val storelessSubscriptionContainer: StorelessSubscriptionContainer,
    private val emojiProvider: EmojiProvider,
    private val emojiSuggester: EmojiSuggester,
    private val stringResourceProvider: StringResourceProvider,
    private val getDefaultObjectType: GetDefaultObjectType,
    private val addDiscussion: AddDiscussion,
    private val userSettingsRepository: UserSettingsRepository,
    private val backHistoryDelegate: BackHistoryDelegate,
    private val exitToVaultDelegate: ExitToVaultDelegate
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ObjectSetViewModel(
            vmParams = params,
            permissions = permissions,
            openObjectSet = openObjectSet,
            closeObject = closeObject,
            setObjectDetails = setObjectDetails,
            createDataViewObject = createDataViewObject,
            setDocCoverImage = setDocCoverImage,
            downloadUnsplashImage = downloadUnsplashImage,
            updateText = updateText,
            interceptEvents = interceptEvents,
            dispatcher = dispatcher,
            delegator = delegator,
            coverImageHashProvider = coverImageHashProvider,
            urlBuilder = urlBuilder,
            session = session,
            analytics = analytics,
            createObject = createObject,
            dataViewSubscriptionContainer = dataViewSubscriptionContainer,
            setQueryToObjectSet = setQueryToObjectSet,
            database = database,
            paginator = paginator,
            storeOfRelations = storeOfRelations,
            stateReducer = objectStateReducer,
            dataViewSubscription = dataViewSubscription,
            objectStore = objectStore,
            addObjectToCollection = addObjectToCollection,
            objectToCollection = objectToCollection,
            storeOfObjectTypes = storeOfObjectTypes,
            duplicateObjects = duplicateObjects,
            templatesContainer = templatesContainer,
            setObjectListIsArchived = setObjectListIsArchived,
            viewerDelegate = viewerDelegate,
            spaceManager = spaceManager,
            createTemplate = createTemplate,
            dateProvider = dateProvider,
            analyticSpaceHelperDelegate = analyticSpaceHelperDelegate,
            spaceSyncAndP2PStatusProvider = spaceSyncAndP2PStatusProvider,
            fieldParser = fieldParser,
            spaceViews = spaceViews,
            deepLinkResolver = deepLinkResolver,
            removeObjectFromCollection = removeObjectFromCollection,
            setDataViewProperties = setDataViewProperties,
            setDataViewObjectOrder = setDataViewObjectOrder,
            boardGroupSubscriptionContainer = boardGroupSubscriptionContainer,
            boardRecordsSubscriptionContainer = boardRecordsSubscriptionContainer,
            storelessSubscriptionContainer = storelessSubscriptionContainer,
            emojiProvider = emojiProvider,
            emojiSuggester = emojiSuggester,
            createBlock = createBlock,
            stringResourceProvider = stringResourceProvider,
            getDefaultObjectType = getDefaultObjectType,
            addDiscussion = addDiscussion,
            userSettingsRepository = userSettingsRepository,
            backHistoryDelegate = backHistoryDelegate,
            exitToVaultDelegate = exitToVaultDelegate
        ) as T
    }
}