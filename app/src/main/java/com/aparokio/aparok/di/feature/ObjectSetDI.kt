package com.aparokio.aparok.di.feature

import android.content.Context
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_models.primitives.Space
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.feature.cover.UnsplashSubComponent
import com.aparokio.aparok.di.feature.relations.RelationAddToDataViewSubComponent
import com.aparokio.aparok.di.feature.relations.RelationAddToObjectSubComponent
import com.aparokio.aparok.di.feature.relations.RelationCreateFromScratchForDataViewSubComponent
import com.aparokio.aparok.di.feature.relations.RelationCreateFromScratchForObjectSubComponent
import com.aparokio.aparok.di.feature.sets.CreateFilterSubComponent
import com.aparokio.aparok.di.feature.sets.ModifyFilterSubComponent
import com.aparokio.aparok.di.feature.sets.SelectFilterRelationSubComponent
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.interactor.CreateBlock
import com.aparokio.aparok.domain.block.interactor.UpdateText
import com.aparokio.aparok.domain.block.interactor.sets.GetObjectTypes
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.collections.AddObjectToCollection
import com.aparokio.aparok.domain.collections.RemoveObjectFromCollection
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.cover.SetDocCoverImage
import com.aparokio.aparok.domain.dataview.SetDataViewProperties
import com.aparokio.aparok.domain.discussions.AddDiscussion
import com.aparokio.aparok.domain.dataview.interactor.AddDataViewViewer
import com.aparokio.aparok.domain.dataview.interactor.CreateDataViewObject
import com.aparokio.aparok.domain.dataview.interactor.DeleteDataViewViewer
import com.aparokio.aparok.domain.dataview.interactor.DuplicateDataViewViewer
import com.aparokio.aparok.domain.dataview.interactor.SetDataViewObjectOrder
import com.aparokio.aparok.domain.dataview.interactor.SetDataViewViewerPosition
import com.aparokio.aparok.domain.dataview.interactor.UpdateDataViewViewer
import com.aparokio.aparok.domain.event.interactor.EventChannel
import com.aparokio.aparok.domain.event.interactor.InterceptEvents
import com.aparokio.aparok.domain.event.interactor.SpaceSyncAndP2PStatusProvider
import com.aparokio.aparok.domain.icon.SetDocumentImageIcon
import com.aparokio.aparok.domain.launch.GetDefaultObjectType
import com.aparokio.aparok.domain.misc.DateProvider
import com.aparokio.aparok.domain.misc.DeepLinkResolver
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.`object`.ConvertObjectToCollection
import com.aparokio.aparok.domain.`object`.DuplicateObject
import com.aparokio.aparok.domain.`object`.DuplicateObjects
import com.aparokio.aparok.domain.`object`.UpdateDetail
import com.aparokio.aparok.domain.objects.DefaultObjectStore
import com.aparokio.aparok.domain.objects.ObjectStore
import com.aparokio.aparok.domain.objects.SetObjectListIsArchived
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.domain.objects.options.GetOptions
import com.aparokio.aparok.domain.page.CloseObject
import com.aparokio.aparok.domain.page.CreateObject
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.domain.relations.AddFileToObject
import com.aparokio.aparok.domain.relations.AddRelationToObject
import com.aparokio.aparok.domain.relations.DeleteRelationFromDataView
import com.aparokio.aparok.domain.resources.StringResourceProvider
import com.aparokio.aparok.domain.search.CancelSearchSubscription
import com.aparokio.aparok.domain.debugging.Logger
import com.aparokio.aparok.domain.library.StorelessSubscriptionContainer
import com.aparokio.aparok.domain.search.BoardGroupSubscriptionContainer
import com.aparokio.aparok.domain.search.BoardRecordsSubscriptionContainer
import com.aparokio.aparok.domain.search.DataViewSubscriptionContainer
import com.aparokio.aparok.domain.search.SearchObjects
import com.aparokio.aparok.domain.search.SubscriptionEventChannel
import com.aparokio.aparok.domain.sets.OpenObjectSet
import com.aparokio.aparok.domain.sets.SetQueryToObjectSet
import com.aparokio.aparok.domain.spaces.ClearLastOpenedSpace
import com.aparokio.aparok.domain.templates.CreateTemplate
import com.aparokio.aparok.domain.templates.GetTemplates
import com.aparokio.aparok.domain.unsplash.DownloadUnsplashImage
import com.aparokio.aparok.domain.unsplash.UnsplashRepository
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.emojifier.data.Emoji
import com.aparokio.aparok.emojifier.data.EmojiProvider
import com.aparokio.aparok.emojifier.suggest.EmojiSuggester
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.common.Action
import com.aparokio.aparok.presentation.common.Delegator
import com.aparokio.aparok.presentation.editor.cover.CoverImageHashProvider
import com.aparokio.aparok.presentation.objects.LockedStateProvider
import com.aparokio.aparok.presentation.relations.providers.DataViewObjectRelationProvider
import com.aparokio.aparok.presentation.relations.providers.DataViewObjectValueProvider
import com.aparokio.aparok.presentation.relations.providers.ObjectRelationListProvider
import com.aparokio.aparok.presentation.relations.providers.ObjectRelationProvider
import com.aparokio.aparok.presentation.relations.providers.ObjectRelationProvider.Companion.DATA_VIEW_PROVIDER_TYPE
import com.aparokio.aparok.presentation.relations.providers.ObjectRelationProvider.Companion.INTRINSIC_PROVIDER_TYPE
import com.aparokio.aparok.presentation.relations.providers.ObjectValueProvider
import com.aparokio.aparok.presentation.relations.providers.SetOrCollectionObjectValueProvider
import com.aparokio.aparok.presentation.relations.providers.SetOrCollectionRelationProvider
import com.aparokio.aparok.presentation.sets.ObjectSetDatabase
import com.aparokio.aparok.presentation.sets.ObjectSetPaginator
import com.aparokio.aparok.presentation.sets.ObjectSetSession
import com.aparokio.aparok.presentation.sets.ObjectSetViewModel
import com.aparokio.aparok.presentation.navigation.backstack.BackHistoryDelegate
import com.aparokio.aparok.presentation.navigation.backstack.NavigationBackStackInspector
import com.aparokio.aparok.presentation.sets.ObjectSetViewModelFactory
import com.aparokio.aparok.presentation.vault.ExitToVaultDelegate
import com.aparokio.aparok.presentation.sets.state.DefaultObjectStateReducer
import com.aparokio.aparok.presentation.sets.state.ObjectState
import com.aparokio.aparok.presentation.sets.state.ObjectStateReducer
import com.aparokio.aparok.presentation.sets.subscription.DataViewSubscription
import com.aparokio.aparok.presentation.sets.subscription.DefaultDataViewSubscription
import com.aparokio.aparok.presentation.sets.viewer.DefaultViewerDelegate
import com.aparokio.aparok.presentation.sets.viewer.ViewerDelegate
import com.aparokio.aparok.presentation.templates.ObjectTypeTemplatesContainer
import com.aparokio.aparok.presentation.util.CopyFileToCacheDirectory
import com.aparokio.aparok.presentation.util.Dispatcher
import com.aparokio.aparok.presentation.util.defaultCopyFileToCacheDirectory
import com.aparokio.aparok.providers.DefaultCoverImageHashProvider
import com.aparokio.aparok.ui.sets.ObjectSetFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import javax.inject.Named
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow

@Subcomponent(modules = [ObjectSetModule::class])
@PerScreen
interface ObjectSetSubComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun withParams(param: ObjectSetViewModel.Params) : Builder
        fun module(module: ObjectSetModule): Builder
        fun build(): ObjectSetSubComponent
    }

    fun inject(fragment: ObjectSetFragment)

    fun objectSetRecordComponent(): ObjectSetRecordSubComponent.Builder
    fun objectSetCreateBookmarkRecordComponent(): ObjectSetCreateBookmarkRecordSubComponent.Builder
    fun viewerFilterBySubComponent(): ViewerFilterSubComponent.Builder

    fun objectSetSettingsComponent(): ObjectSetSettingsSubComponent.Builder
    fun relationAddToDataViewComponent(): RelationAddToDataViewSubComponent.Builder
    fun relationCreateFromScratchForDataViewComponent(): RelationCreateFromScratchForDataViewSubComponent.Builder
    fun selectSortRelationComponent(): SelectSortRelationSubComponent.Builder
    fun selectFilterRelationComponent(): SelectFilterRelationSubComponent.Builder
    fun createFilterComponent(): CreateFilterSubComponent.Builder
    fun modifyFilterComponent(): ModifyFilterSubComponent.Builder
    fun viewerSortComponent(): ViewerSortSubComponent.Builder
    fun modifyViewerSortComponent(): ModifyViewerSortSubComponent.Builder

    fun relationTextValueComponent(): RelationTextValueSubComponent.Builder
    fun relationDataViewTextValueComponent(): RelationDataViewTextValueSubComponent.Builder

    fun relationDateValueComponent(): DefaultRelationDataValueSubComponent.Builder
    fun dataViewRelationDateValueComponent(): DataViewRelationDataValueSubComponent.Builder

    fun objectSetMenuComponent(): ObjectSetMenuComponent.Builder
    fun objectSetIconPickerComponent(): ObjectSetIconPickerComponent.Builder
    fun objectSetCoverComponent(): SelectCoverObjectSetSubComponent.Builder
    fun objectUnsplashComponent(): UnsplashSubComponent.Builder

    fun objectRelationListComponent(): ObjectRelationListComponent.Builder
    fun relationAddToObjectComponent(): RelationAddToObjectSubComponent.Builder
    fun relationCreateFromScratchForObjectComponent(): RelationCreateFromScratchForObjectSubComponent.Builder

    fun tagStatusSetComponent(): TagOrStatusValueSetComponent.Builder
    fun tagStatusDataViewComponent(): TagOrStatusValueDataViewComponent.Builder
    fun optionSetComponent(): CreateOrEditOptionSetComponent.Builder
    fun optionDataViewComponent(): CreateOrEditOptionDataViewComponent.Builder
    fun objectValueSetComponent(): ObjectValueSetComponent.Builder
    fun objectValueDataViewComponent(): ObjectValueDataViewComponent.Builder
}

@Module(
    includes = [ObjectSetModule.Bindings::class]
)
object ObjectSetModule {

    @JvmStatic
    @Provides
    @PerScreen
    fun provideSetDocumentImageIconUseCase(
        repo: BlockRepository
    ): SetDocumentImageIcon = SetDocumentImageIcon(repo)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideDataViewSubscription(
        dataViewSubscriptionContainer: DataViewSubscriptionContainer
    ): DataViewSubscription = DefaultDataViewSubscription(
        dataViewSubscriptionContainer
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideSetDataViewProperties(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetDataViewProperties = SetDataViewProperties(
        repo = repo,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideDuplicateObjectsListUseCase(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): DuplicateObjects = DuplicateObjects(
        repo = repo,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideObjectSetViewModelFactory(
        params: ObjectSetViewModel.Params,
        openObjectSet: OpenObjectSet,
        closeObject: CloseObject,
        setObjectDetails: UpdateDetail,
        updateText: UpdateText,
        createBlock: CreateBlock,
        interceptEvents: InterceptEvents,
        createDataViewObject: CreateDataViewObject,
        createObject: CreateObject,
        dispatcher: Dispatcher<Payload>,
        delegator: Delegator<Action>,
        urlBuilder: UrlBuilder,
        coverImageHashProvider: CoverImageHashProvider,
        session: ObjectSetSession,
        analytics: Analytics,
        downloadUnsplashImage: DownloadUnsplashImage,
        setDocCoverImage: SetDocCoverImage,
        dataViewSubscriptionContainer: DataViewSubscriptionContainer,
        setQueryToObjectSet: SetQueryToObjectSet,
        database: ObjectSetDatabase,
        paginator: ObjectSetPaginator,
        storeOfRelations: StoreOfRelations,
        objectStateReducer: ObjectStateReducer,
        dataViewSubscription: DataViewSubscription,
        @Named("object-set-store") objectStore: ObjectStore,
        addObjectToCollection: AddObjectToCollection,
        removeObjectFromCollection: RemoveObjectFromCollection,
        convertObjectToCollection: ConvertObjectToCollection,
        storeOfObjectTypes: StoreOfObjectTypes,
        getObjectTypes: GetObjectTypes,
        duplicateObjects: DuplicateObjects,
        templatesContainer: ObjectTypeTemplatesContainer,
        setObjectListIsArchived: SetObjectListIsArchived,
        createTemplate: CreateTemplate,
        viewerDelegate: ViewerDelegate,
        spaceManager: SpaceManager,
        dateProvider: DateProvider,
        permissions: UserPermissionProvider,
        analyticSpaceHelperDelegate: AnalyticSpaceHelperDelegate,
        spaceSyncAndP2PStatusProvider: SpaceSyncAndP2PStatusProvider,
        fieldParser: FieldParser,
        spaceViews: SpaceViewSubscriptionContainer,
        deepLinkResolver: DeepLinkResolver,
        setDataViewProperties: SetDataViewProperties,
        setDataViewObjectOrder: SetDataViewObjectOrder,
        boardGroupSubscriptionContainer: BoardGroupSubscriptionContainer,
        boardRecordsSubscriptionContainer: BoardRecordsSubscriptionContainer,
        storelessSubscriptionContainer: StorelessSubscriptionContainer,
        emojiProvider: EmojiProvider,
        emojiSuggester: EmojiSuggester,
        stringResourceProvider: StringResourceProvider,
        getDefaultObjectType: GetDefaultObjectType,
        addDiscussion: AddDiscussion,
        userSettingsRepository: UserSettingsRepository,
        backHistoryDelegate: BackHistoryDelegate,
        exitToVaultDelegate: ExitToVaultDelegate
    ): ObjectSetViewModelFactory = ObjectSetViewModelFactory(
        params = params,
        openObjectSet = openObjectSet,
        closeObject = closeObject,
        setObjectDetails = setObjectDetails,
        createDataViewObject = createDataViewObject,
        updateText = updateText,
        createBlock = createBlock,
        interceptEvents = interceptEvents,
        dispatcher = dispatcher,
        delegator = delegator,
        coverImageHashProvider = coverImageHashProvider,
        urlBuilder = urlBuilder,
        session = session,
        analytics = analytics,
        downloadUnsplashImage = downloadUnsplashImage,
        setDocCoverImage = setDocCoverImage,
        createObject = createObject,
        dataViewSubscriptionContainer = dataViewSubscriptionContainer,
        setQueryToObjectSet = setQueryToObjectSet,
        database = database,
        paginator = paginator,
        storeOfRelations = storeOfRelations,
        objectStateReducer = objectStateReducer,
        dataViewSubscription = dataViewSubscription,
        objectStore = objectStore,
        addObjectToCollection = addObjectToCollection,
        objectToCollection = convertObjectToCollection,
        storeOfObjectTypes = storeOfObjectTypes,
        duplicateObjects = duplicateObjects,
        templatesContainer = templatesContainer,
        setObjectListIsArchived = setObjectListIsArchived,
        viewerDelegate = viewerDelegate,
        spaceManager = spaceManager,
        createTemplate = createTemplate,
        dateProvider = dateProvider,
        permissions = permissions,
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
        stringResourceProvider = stringResourceProvider,
        getDefaultObjectType = getDefaultObjectType,
        addDiscussion = addDiscussion,
        userSettingsRepository = userSettingsRepository,
        backHistoryDelegate = backHistoryDelegate,
        exitToVaultDelegate = exitToVaultDelegate
    )

    @JvmStatic
    @Provides
    @PerScreen
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

    @JvmStatic
    @Provides
    @PerScreen
    fun provideExitToVaultDelegate(
        spaceManager: SpaceManager,
        clearLastOpenedSpace: ClearLastOpenedSpace
    ): ExitToVaultDelegate = ExitToVaultDelegate.Default(
        spaceManager = spaceManager,
        clearLastOpenedSpace = clearLastOpenedSpace
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideConvertObjectToCollection(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): ConvertObjectToCollection = ConvertObjectToCollection(
        repo = repo,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideCreateBlock(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): CreateBlock = CreateBlock(
        repo = repo,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun getCreateObject(
        repo: BlockRepository,
        getDefaultObjectType: GetDefaultObjectType,
        dispatchers: AppCoroutineDispatchers
    ): CreateObject = CreateObject(
        repo = repo,
        getDefaultObjectType = getDefaultObjectType,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideSetQueryToSet(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetQueryToObjectSet = SetQueryToObjectSet(repo, dispatchers)

    @JvmStatic
    @PerScreen
    @Provides
    fun provideGetDefaultPageType(
        userSettingsRepository: UserSettingsRepository,
        blockRepository: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): GetDefaultObjectType = GetDefaultObjectType(
        userSettingsRepository = userSettingsRepository,
        blockRepository = blockRepository,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideOpenObjectSetUseCase(
        repo: BlockRepository,
        settings: UserSettingsRepository
    ): OpenObjectSet = OpenObjectSet(
        repo = repo,
        settings = settings
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideUpdateTextUseCase(
        repo: BlockRepository
    ): UpdateText = UpdateText(repo = repo)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideInterceptEventsUseCase(
        channel: EventChannel
    ): InterceptEvents = InterceptEvents(
        channel = channel,
        context = Dispatchers.IO
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideClosePageUseCase(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): CloseObject = CloseObject(repo, dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideState(reducer: ObjectStateReducer): MutableStateFlow<ObjectState> = reducer.state

    @JvmStatic
    @Provides
    @PerScreen
    fun provideObjectStateReducer(): ObjectStateReducer = DefaultObjectStateReducer()

    @JvmStatic
    @Provides
    @PerScreen
    fun provideObjectSetSession(): ObjectSetSession = ObjectSetSession()

    @JvmStatic
    @Provides
    @PerScreen
    fun provideDelegator(): Delegator<Action> = Delegator.Default()

    @JvmStatic
    @Provides
    @PerScreen
    @Named(DATA_VIEW_PROVIDER_TYPE)
    fun provideDataViewObjectRelationProvider(
        state: MutableStateFlow<ObjectState>,
        storeOfRelations: StoreOfRelations
    ): ObjectRelationProvider = DataViewObjectRelationProvider(
        objectState = state,
        storeOfRelations = storeOfRelations
    )

    @JvmStatic
    @Provides
    @PerScreen
    @Named(INTRINSIC_PROVIDER_TYPE)
    fun provideObjectRelationProvider(
        state: MutableStateFlow<ObjectState>,
        storeOfRelations: StoreOfRelations
    ): ObjectRelationProvider = SetOrCollectionRelationProvider(
        objectState = state,
        storeOfRelations = storeOfRelations
    )

    @JvmStatic
    @Provides
    @PerScreen
    @Named(DATA_VIEW_PROVIDER_TYPE)
    fun dataViewObjectValueProvider(
        db: ObjectSetDatabase,
        objectState: MutableStateFlow<ObjectState>,
    ): ObjectValueProvider = DataViewObjectValueProvider(
        objectState = objectState,
        db = db
    )

    @JvmStatic
    @Provides
    @PerScreen
    @Named(INTRINSIC_PROVIDER_TYPE)
    fun setOrCollectionObjectValueProvider(
        db: ObjectSetDatabase,
        objectState: MutableStateFlow<ObjectState>,
    ): ObjectValueProvider = SetOrCollectionObjectValueProvider(
        objectState = objectState,
        db = db
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideUpdateDetailUseCase(
        repository: BlockRepository
    ): UpdateDetail = UpdateDetail(repository)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideDeleteRelationFromDataViewUseCase(
        repo: BlockRepository
    ): DeleteRelationFromDataView = DeleteRelationFromDataView(repo = repo)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideSetDocCoverImageUseCase(
        repo: BlockRepository
    ): SetDocCoverImage = SetDocCoverImage(repo)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideDownload(repo: UnsplashRepository): DownloadUnsplashImage = DownloadUnsplashImage(
        repo = repo
    )

    @JvmStatic
    @PerScreen
    @Provides
    fun getSetObjectListIsArchived(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetObjectListIsArchived = SetObjectListIsArchived(repo, dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideGetTemplates(
        repo: BlockRepository,
        spaceManager: SpaceManager,
        dispatchers: AppCoroutineDispatchers
    ): GetTemplates = GetTemplates(
        repo = repo,
        spaceManager = spaceManager,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun objectSearchSubscriptionContainer(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers,
        channel: SubscriptionEventChannel,
        @Named("object-set-store") store: ObjectStore
    ): DataViewSubscriptionContainer = DataViewSubscriptionContainer(
        repo = repo,
        channel = channel,
        store = store,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun boardGroupSubscriptionContainer(
        repo: BlockRepository,
        channel: SubscriptionEventChannel,
        dispatchers: AppCoroutineDispatchers,
        logger: Logger
    ): BoardGroupSubscriptionContainer = BoardGroupSubscriptionContainer(
        repo = repo,
        channel = channel,
        dispatchers = dispatchers,
        logger = logger
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun getOptions(repo: BlockRepository) = GetOptions(repo)

    @JvmStatic
    @Provides
    @PerScreen
    fun boardRecordsSubscriptionContainer(
        repo: BlockRepository,
        channel: SubscriptionEventChannel,
        @Named("object-set-store") store: ObjectStore,
        dispatchers: AppCoroutineDispatchers,
        logger: Logger
    ): BoardRecordsSubscriptionContainer = BoardRecordsSubscriptionContainer(
        repo = repo,
        channel = channel,
        store = store,
        dispatchers = dispatchers,
        logger = logger
    )

    @JvmStatic
    @Provides
    @PerScreen
    @Named("object-set-store")
    fun provideObjectStore(): ObjectStore = DefaultObjectStore()

    @JvmStatic
    @Provides
    @PerScreen
    fun cancelSearchSubscription(
        repo: BlockRepository,
        @Named("object-set-store") store: ObjectStore
    ): CancelSearchSubscription = CancelSearchSubscription(
        repo = repo,
        store = store
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideObjectSetDatabase(
        @Named("object-set-store") store: ObjectStore
    ): ObjectSetDatabase = ObjectSetDatabase(
        store = store
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun providePaginator(): ObjectSetPaginator = ObjectSetPaginator()

    @JvmStatic
    @Provides
    @PerScreen
    fun provideAddFileToObjectUseCase(
        repo: BlockRepository
    ): AddFileToObject = AddFileToObject(repo = repo)

    @JvmStatic
    @Provides
    @PerScreen
    fun duplicateObject(
        repo: BlockRepository
    ): DuplicateObject = DuplicateObject(
        repo = repo
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideAddObjectToCollection(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): AddObjectToCollection = AddObjectToCollection(
        repo = repo,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideRemoveObjectToCollection(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): RemoveObjectFromCollection = RemoveObjectFromCollection(
        repo = repo,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun lockedStateProvider() : LockedStateProvider = LockedStateProvider.DataViewLockedStateProvider

    @JvmStatic
    @Provides
    @PerScreen
    fun dataViewRelationListProvider(
        objectStateFlow: MutableStateFlow<ObjectState>
    ) : ObjectRelationListProvider = ObjectRelationListProvider.ObjectSetRelationListProvider(
        objectStates = objectStateFlow
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideAddRelationToObject(repo: BlockRepository, dispatchers: AppCoroutineDispatchers)
            : AddRelationToObject = AddRelationToObject(repo, dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideCopyFileToCache(
        context: Context
    ): CopyFileToCacheDirectory = defaultCopyFileToCacheDirectory(context)

    @Module
    interface Bindings {

        @PerScreen
        @Binds
        fun bindCoverImageHashProvider(
            defaultProvider: DefaultCoverImageHashProvider
        ): CoverImageHashProvider
    }

    @JvmStatic
    @Provides
    @PerScreen
    fun provideAddDataViewViewerUseCase(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): AddDataViewViewer = AddDataViewViewer(repo = repo, dispatchers = dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideRenameDataViewViewerUseCase(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): UpdateDataViewViewer = UpdateDataViewViewer(repo = repo, dispatchers = dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideDuplicateDataViewViewerUseCase(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): DuplicateDataViewViewer = DuplicateDataViewViewer(repo = repo, dispatchers = dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideDeleteDataViewViewerUseCase(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): DeleteDataViewViewer = DeleteDataViewViewer(repo = repo, dispatchers = dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideSetDataViewViewerPositionUseCase(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetDataViewViewerPosition = SetDataViewViewerPosition(repo = repo, dispatchers = dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideSetDataViewObjectOrderUseCase(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetDataViewObjectOrder = SetDataViewObjectOrder(repo = repo, dispatchers = dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideViewerDelegate(
        session: ObjectSetSession,
        addDataViewViewer: AddDataViewViewer,
        duplicateDataViewViewer: DuplicateDataViewViewer,
        deleteDataViewViewer: DeleteDataViewViewer,
        setDataViewViewerPosition: SetDataViewViewerPosition,
        analytics: Analytics,
        dispatcher: Dispatcher<Payload>,
        updateDataViewViewer: UpdateDataViewViewer
    ): ViewerDelegate = DefaultViewerDelegate(
        session = session,
        addDataViewViewer = addDataViewViewer,
        updateDataViewViewer = updateDataViewViewer,
        duplicateDataViewViewer = duplicateDataViewViewer,
        deleteDataViewViewer = deleteDataViewViewer,
        setDataViewViewerPosition = setDataViewViewerPosition,
        analytics = analytics,
        dispatcher = dispatcher
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideEmojiProvider(): EmojiProvider = Emoji
}

data class DefaultComponentParam(
    val ctx: Id,
    val space: Space
)

