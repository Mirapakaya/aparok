package com.aparokio.aparok.presentation.editor.editor

import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.ObjectViewDetails
import com.aparokio.aparok.core_models.Block
import com.aparokio.aparok.core_models.Event
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.NetworkModeConfig
import com.aparokio.aparok.core_models.ObjectWrapper
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_models.Relations
import com.aparokio.aparok.core_models.Response
import com.aparokio.aparok.core_models.Struct
import com.aparokio.aparok.core_models.StubObjectType
import com.aparokio.aparok.core_models.StubSpaceView
import com.aparokio.aparok.core_models.multiplayer.SpaceMemberPermissions
import com.aparokio.aparok.core_models.multiplayer.SpaceUxType
import com.aparokio.aparok.core_models.primitives.ParsedProperties
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.core_models.primitives.TypeId
import com.aparokio.aparok.core_models.primitives.TypeKey
import com.aparokio.aparok.core_models.restrictions.ObjectRestriction
import com.aparokio.aparok.domain.ai.TypeSuggestionEngine
import com.aparokio.aparok.domain.auth.interactor.ClearLastOpenedObject
import com.aparokio.aparok.domain.base.Either
import com.aparokio.aparok.domain.base.Result
import com.aparokio.aparok.domain.base.Resultat
import com.aparokio.aparok.domain.block.UpdateDivider
import com.aparokio.aparok.domain.block.interactor.ClearBlockContent
import com.aparokio.aparok.domain.block.interactor.ClearBlockStyle
import com.aparokio.aparok.domain.block.interactor.CreateBlock
import com.aparokio.aparok.domain.block.interactor.DuplicateBlock
import com.aparokio.aparok.domain.block.interactor.MergeBlocks
import com.aparokio.aparok.domain.block.interactor.Move
import com.aparokio.aparok.domain.block.interactor.RemoveLinkMark
import com.aparokio.aparok.domain.block.interactor.ReplaceBlock
import com.aparokio.aparok.domain.block.interactor.SetObjectType
import com.aparokio.aparok.domain.block.interactor.SplitBlock
import com.aparokio.aparok.domain.block.interactor.TurnIntoDocument
import com.aparokio.aparok.domain.block.interactor.TurnIntoStyle
import com.aparokio.aparok.domain.block.interactor.UnlinkBlocks
import com.aparokio.aparok.domain.block.interactor.UpdateAlignment
import com.aparokio.aparok.domain.block.interactor.UpdateBackgroundColor
import com.aparokio.aparok.domain.block.interactor.UpdateBlocksMark
import com.aparokio.aparok.domain.block.interactor.UpdateCheckbox
import com.aparokio.aparok.domain.block.interactor.UpdateFields
import com.aparokio.aparok.domain.block.interactor.UpdateLinkMarks
import com.aparokio.aparok.domain.block.interactor.UpdateText
import com.aparokio.aparok.domain.block.interactor.UpdateTextColor
import com.aparokio.aparok.domain.block.interactor.UpdateTextStyle
import com.aparokio.aparok.domain.block.interactor.UploadBlock
import com.aparokio.aparok.domain.block.interactor.sets.CreateObjectSet
import com.aparokio.aparok.domain.block.interactor.sets.GetObjectTypes
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.clipboard.Copy
import com.aparokio.aparok.domain.clipboard.Paste
import com.aparokio.aparok.domain.config.Gateway
import com.aparokio.aparok.domain.cover.SetDocCoverImage
import com.aparokio.aparok.domain.debugging.Logger
import com.aparokio.aparok.domain.download.DownloadFile
import com.aparokio.aparok.domain.event.interactor.InterceptEvents
import com.aparokio.aparok.domain.event.interactor.SpaceSyncAndP2PStatusProvider
import com.aparokio.aparok.domain.icon.SetDocumentImageIcon
import com.aparokio.aparok.domain.launch.GetDefaultObjectType
import com.aparokio.aparok.domain.library.StorelessSubscriptionContainer
import com.aparokio.aparok.domain.misc.DateProvider
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.misc.UrlBuilderImpl
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.networkmode.GetNetworkMode
import com.aparokio.aparok.domain.`object`.ConvertObjectToCollection
import com.aparokio.aparok.domain.`object`.ConvertObjectToSet
import com.aparokio.aparok.domain.`object`.SetObjectInternalFlags
import com.aparokio.aparok.domain.`object`.UpdateDetail
import com.aparokio.aparok.domain.objects.DefaultStoreOfObjectTypes
import com.aparokio.aparok.domain.objects.DefaultStoreOfRelations
import com.aparokio.aparok.domain.objects.GetDateObjectByTimestamp
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.domain.page.CloseObject
import com.aparokio.aparok.domain.page.CreateBlockLinkWithObject
import com.aparokio.aparok.domain.page.CreateObject
import com.aparokio.aparok.domain.page.CreateObjectAsMentionOrLink
import com.aparokio.aparok.domain.page.OpenPage
import com.aparokio.aparok.domain.page.Redo
import com.aparokio.aparok.domain.page.Undo
import com.aparokio.aparok.domain.page.bookmark.CreateBookmarkBlock
import com.aparokio.aparok.domain.page.bookmark.SetupBookmark
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.domain.primitives.FieldParserImpl
import com.aparokio.aparok.domain.relations.AddRelationToObject
import com.aparokio.aparok.domain.relations.SetRelationKey
import com.aparokio.aparok.domain.resources.StringResourceProvider
import com.aparokio.aparok.domain.search.SearchObjects
import com.aparokio.aparok.domain.sets.FindObjectSetForType
import com.aparokio.aparok.domain.table.CreateTable
import com.aparokio.aparok.domain.table.CreateTableColumn
import com.aparokio.aparok.domain.table.CreateTableRow
import com.aparokio.aparok.domain.table.DeleteTableColumn
import com.aparokio.aparok.domain.table.DeleteTableRow
import com.aparokio.aparok.domain.table.DuplicateTableColumn
import com.aparokio.aparok.domain.table.DuplicateTableRow
import com.aparokio.aparok.domain.table.FillTableColumn
import com.aparokio.aparok.domain.table.FillTableRow
import com.aparokio.aparok.domain.table.MoveTableColumn
import com.aparokio.aparok.domain.table.MoveTableRow
import com.aparokio.aparok.domain.table.SetTableRowHeader
import com.aparokio.aparok.domain.templates.ApplyTemplate
import com.aparokio.aparok.domain.templates.GetTemplates
import com.aparokio.aparok.domain.unsplash.DownloadUnsplashImage
import com.aparokio.aparok.domain.unsplash.UnsplashRepository
import com.aparokio.aparok.domain.workspace.InterceptFileLimitEvents
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.common.Action
import com.aparokio.aparok.presentation.common.Delegator
import com.aparokio.aparok.presentation.editor.DocumentExternalEventReducer
import com.aparokio.aparok.presentation.editor.Editor
import com.aparokio.aparok.presentation.editor.EditorViewModel
import com.aparokio.aparok.presentation.editor.cover.CoverImageHashProvider
import com.aparokio.aparok.presentation.editor.editor.pattern.DefaultPatternMatcher
import com.aparokio.aparok.presentation.editor.editor.table.DefaultEditorTableDelegate
import com.aparokio.aparok.presentation.editor.editor.table.EditorTableDelegate
import com.aparokio.aparok.presentation.editor.render.DefaultBlockViewRenderer
import com.aparokio.aparok.presentation.editor.selection.SelectionStateHolder
import com.aparokio.aparok.presentation.editor.toggle.ToggleStateHolder
import com.aparokio.aparok.presentation.home.UserPermissionProviderStub
import com.aparokio.aparok.presentation.templates.ObjectTypeTemplatesContainer
import com.aparokio.aparok.presentation.util.CopyFileToCacheDirectory
import com.aparokio.aparok.presentation.util.Dispatcher
import com.aparokio.aparok.presentation.util.dispatchers
import com.aparokio.aparok.presentation.util.downloader.DocumentFileShareDownloader
import com.aparokio.aparok.presentation.widgets.collection.ResourceProvider
import com.aparokio.aparok.test_utils.MockDataFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub

open class EditorPresentationTestSetup {

    val root: Id = MockDataFactory.randomString()
    val defaultSpace: Id = MockDataFactory.randomUuid()

    @Mock
    lateinit var openPage: OpenPage

    @Mock
    lateinit var closePage: CloseObject

    @Mock
    lateinit var interceptEvents: InterceptEvents

    @Mock
    lateinit var createBlock: CreateBlock

    @Mock
    lateinit var updateText: UpdateText

    @Mock
    lateinit var updateCheckbox: UpdateCheckbox

    @Mock
    lateinit var duplicateBlock: DuplicateBlock

    @Mock
    lateinit var unlinkBlocks: UnlinkBlocks

    @Mock
    lateinit var updateTextStyle: UpdateTextStyle

    @Mock
    lateinit var updateTextColor: UpdateTextColor

    @Mock
    lateinit var clearLastOpenedObject: ClearLastOpenedObject

    @Mock
    lateinit var updateBlocksMark: UpdateBlocksMark

    @Mock
    lateinit var updateDivider: UpdateDivider

    @Mock
    lateinit var analytics: Analytics

    @Mock
    lateinit var updateLinkMark: UpdateLinkMarks

    @Mock
    lateinit var removeLinkMark: RemoveLinkMark

    @Mock
    lateinit var mergeBlocks: MergeBlocks

    @Mock
    lateinit var splitBlock: SplitBlock

    @Mock
    lateinit var createBlockLinkWithObject: CreateBlockLinkWithObject

    @Mock
    lateinit var updateAlignment: UpdateAlignment

    @Mock
    lateinit var updateBackgroundColor: UpdateBackgroundColor

    @Mock
    lateinit var downloadFile: DownloadFile

    @Mock
    lateinit var documentFileShareDownloader: DocumentFileShareDownloader

    @Mock
    lateinit var uploadBlock: UploadBlock

    @Mock
    lateinit var updateFields: UpdateFields

    @Mock
    lateinit var paste: Paste

    @Mock
    lateinit var copy: Copy

    @Mock
    lateinit var getDateObjectByTimestamp: GetDateObjectByTimestamp

    @Mock
    lateinit var undo: Undo

    @Mock
    lateinit var redo: Redo

    @Mock
    lateinit var setupBookmark: SetupBookmark

    @Mock
    lateinit var resourceProvider: ResourceProvider

    @Mock
    lateinit var createBookmarkBlock: CreateBookmarkBlock

    @Mock
    lateinit var setRelationKey: SetRelationKey

    @Mock
    lateinit var createObjectAsMentionOrLink: CreateObjectAsMentionOrLink

    @Mock
    lateinit var replaceBlock: ReplaceBlock

    @Mock
    lateinit var move: Move

    lateinit var fieldParser: FieldParser

    @Mock
    lateinit var turnIntoDocument: TurnIntoDocument

    @Mock
    lateinit var turnIntoStyle: TurnIntoStyle

    @Mock
    lateinit var gateway: Gateway

    @Mock
    lateinit var repo: BlockRepository

    @Mock
    lateinit var unsplashRepo: UnsplashRepository

    @Mock
    lateinit var coverImageHashProvider: CoverImageHashProvider

    @Mock
    lateinit var setObjectType: SetObjectType

    @Mock
    lateinit var searchObjects: SearchObjects

    @Mock
    lateinit var applyTemplate: ApplyTemplate

    @Mock
    lateinit var getDefaultObjectType: GetDefaultObjectType

    @Mock
    lateinit var findObjectSetForType: FindObjectSetForType

    @Mock
    lateinit var createObjectSet: CreateObjectSet

    @Mock
    lateinit var copyFileToCacheDirectory: CopyFileToCacheDirectory

    @Mock
    lateinit var createObject: CreateObject

    @Mock
    lateinit var getTemplates: GetTemplates

    @Mock
    lateinit var createTable: CreateTable

    @Mock
    lateinit var fillTableRow: FillTableRow

    @Mock
    lateinit var spacedViews: SpaceViewSubscriptionContainer

    @Mock
    lateinit var convertObjectToCollection: ConvertObjectToCollection

    lateinit var tableDelegate: EditorTableDelegate

    lateinit var dispatcher: Dispatcher<Payload>

    @Mock
    lateinit var templatesContainer: ObjectTypeTemplatesContainer

    protected val builder: UrlBuilder get() = UrlBuilderImpl(gateway)

    private lateinit var updateDetail: UpdateDetail
    private lateinit var downloadUnsplashImage: DownloadUnsplashImage
    private lateinit var setDocCoverImage: SetDocCoverImage
    private lateinit var setDocImageIcon: SetDocumentImageIcon
    private lateinit var objectToSet: ConvertObjectToSet
    private lateinit var clearBlockContent: ClearBlockContent
    private lateinit var clearBlockStyle: ClearBlockStyle

    @Mock
    lateinit var createTableColumn: CreateTableColumn

    @Mock
    lateinit var createTableRow: CreateTableRow

    @Mock
    lateinit var deleteTableColumn: DeleteTableColumn

    @Mock
    lateinit var deleteTableRow: DeleteTableRow

    @Mock
    lateinit var duplicateTableRow: DuplicateTableRow

    @Mock
    lateinit var duplicateTableColumn: DuplicateTableColumn

    @Mock
    lateinit var fillTableColumn: FillTableColumn

    @Mock
    lateinit var moveTableRow: MoveTableRow

    @Mock
    lateinit var moveTableColumn: MoveTableColumn

    @Mock
    lateinit var addRelationToObject: AddRelationToObject

    @Mock
    lateinit var setTableRowHeader: SetTableRowHeader

    open lateinit var orchestrator: Orchestrator

    private val delegator = Delegator.Default<Action>()

    protected val storeOfRelations: StoreOfRelations = DefaultStoreOfRelations()
    protected val storeOfObjectTypes: StoreOfObjectTypes = DefaultStoreOfObjectTypes()

    @Mock
    lateinit var getObjectTypes: GetObjectTypes

    @Mock
    lateinit var interceptFileLimitEvents: InterceptFileLimitEvents

    @Mock
    lateinit var setObjectInternalFlags: SetObjectInternalFlags

    @Mock
    lateinit var spaceManager: SpaceManager

    @Mock
    lateinit var storelessSubscriptionContainer: StorelessSubscriptionContainer

    @Mock
    lateinit var analyticSpaceHelperDelegate: AnalyticSpaceHelperDelegate

    @Mock
    lateinit var getNetworkMode: GetNetworkMode

    @Mock
    lateinit var spaceSyncAndP2PStatusProvider: SpaceSyncAndP2PStatusProvider

    @Mock
    lateinit var dateProvider: DateProvider

    @Mock
    lateinit var logger: Logger

    var permissions: UserPermissionProvider = UserPermissionProviderStub()

    val objTypeUniqueKey = "objTypeUniqueKey-${MockDataFactory.randomString()}"

    val objType = StubObjectType(
        id = MockDataFactory.randomUuid(),
        uniqueKey = objTypeUniqueKey,
    )

    open fun buildViewModel(urlBuilder: UrlBuilder = builder): EditorViewModel {

        runBlocking {
            storeOfObjectTypes.merge(
                types = listOf(objType)
            )
        }

        val storage = Editor.Storage()
        val proxies = Editor.Proxer()
        val memory = Editor.Memory(
            selections = SelectionStateHolder.Default()
        )
        objectToSet = ConvertObjectToSet(repo, dispatchers)
        updateDetail = UpdateDetail(repo)
        setDocCoverImage = SetDocCoverImage(repo)
        setDocImageIcon = SetDocumentImageIcon(repo)
        downloadUnsplashImage = DownloadUnsplashImage(unsplashRepo)
        clearBlockContent = ClearBlockContent(repo)
        clearBlockStyle = ClearBlockStyle(repo)

        orchestrator = Orchestrator(
            createBlock = createBlock,
            replaceBlock = replaceBlock,
            updateTextColor = updateTextColor,
            duplicateBlock = duplicateBlock,
            downloadFile = downloadFile,
            documentFileShareDownloader = documentFileShareDownloader,
            undo = undo,
            redo = redo,
            updateText = updateText,
            updateCheckbox = updateCheckbox,
            updateTextStyle = updateTextStyle,
            updateBackgroundColor = updateBackgroundColor,
            mergeBlocks = mergeBlocks,
            uploadBlock = uploadBlock,
            splitBlock = splitBlock,
            unlinkBlocks = unlinkBlocks,
            updateDivider = updateDivider,
            memory = memory,
            stores = storage,
            proxies = proxies,
            textInteractor = Interactor.TextInteractor(
                proxies = proxies,
                stores = storage,
                matcher = DefaultPatternMatcher()
            ),
            updateAlignment = updateAlignment,
            setupBookmark = setupBookmark,
            createBookmarkBlock = createBookmarkBlock,
            paste = paste,
            copy = copy,
            move = move,
            turnIntoDocument = turnIntoDocument,
            analytics = analytics,
            updateFields = updateFields,
            setRelationKey = setRelationKey,
            turnIntoStyle = turnIntoStyle,
            updateBlocksMark = updateBlocksMark,
            createTable = createTable,
            fillTableRow = fillTableRow,
            clearBlockContent = clearBlockContent,
            clearBlockStyle = clearBlockStyle,
            analyticsSpaceHelperDelegate = analyticSpaceHelperDelegate,
            spaceManager = spaceManager
        )

        dispatcher = Dispatcher.Default()

        tableDelegate = DefaultEditorTableDelegate(
            dispatcher, createTableColumn, createTableRow,
            deleteTableColumn, deleteTableRow, duplicateTableRow,
            duplicateTableColumn, fillTableRow, fillTableColumn,
            moveTableRow, moveTableColumn, setTableRowHeader
        )

        return EditorViewModel(
            openPage = openPage,
            closePage = closePage,
            createBlockLinkWithObject = createBlockLinkWithObject,
            createObjectAsMentionOrLink = createObjectAsMentionOrLink,
            interceptEvents = interceptEvents,
            updateLinkMarks = updateLinkMark,
            removeLinkMark = removeLinkMark,
            reducer = DocumentExternalEventReducer(),
            urlBuilder = urlBuilder,
            renderer = DefaultBlockViewRenderer(
                urlBuilder = urlBuilder,
                toggleStateHolder = ToggleStateHolder.Default(),
                coverImageHashProvider = coverImageHashProvider,
                storeOfRelations = storeOfRelations,
                storeOfObjectTypes = storeOfObjectTypes,
                fieldParser = fieldParser,
                resourceProvider = resourceProvider
            ),
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
            objectToCollection = convertObjectToCollection,
            storeOfRelations = storeOfRelations,
            storeOfObjectTypes = storeOfObjectTypes,
            featureToggles = mock(),
            tableDelegate = tableDelegate,
            getObjectTypes = getObjectTypes,
            interceptFileLimitEvents = interceptFileLimitEvents,
            addRelationToObject = addRelationToObject,
            spaceManager = spaceManager,
            applyTemplate = applyTemplate,
            setObjectType = setObjectType,
            templatesContainer = templatesContainer,
            storelessSubscriptionContainer = storelessSubscriptionContainer,
            dispatchers = dispatchers,
            vmParams = EditorViewModel.Params(
                ctx = root,
                space = SpaceId(defaultSpace)
            ),
            permissions = permissions,
            analyticSpaceHelperDelegate = analyticSpaceHelperDelegate,
            clearLastOpenedObject = clearLastOpenedObject,
            spaceSyncAndP2PStatusProvider = spaceSyncAndP2PStatusProvider,
            getNetworkMode = getNetworkMode,
            fieldParser = fieldParser,
            dateProvider = dateProvider,
            spaceViews = spacedViews,
            urlHelper = mock(),
            addDiscussion = mock(),
            getChatMessages = mock(),
            backHistoryDelegate = mock(),
            exitToVaultDelegate = mock(),
            typeSuggestionEngine = TypeSuggestionEngine.NoOp
        )
    }

    fun stubParsedProperties() {
        fieldParser.stub {
            onBlocking {
                getObjectParsedProperties(
                    objectType = any(),
                    objPropertiesKeys = any(),
                    storeOfRelations = any()
                )
            } doReturn ParsedProperties()
        }
    }

    fun stubGetNetworkMode() {
        getNetworkMode.stub {
            onBlocking { run(Unit) } doReturn NetworkModeConfig()
        }
    }

    fun stubOpenDocument(
        document: List<Block> = emptyList(),
        details: ObjectViewDetails = ObjectViewDetails(
            details = mapOf(
                root to mapOf(
                    Relations.ID to root,
                    Relations.TYPE to listOf(objType.id)
                )
            )
        ),
        objectRestrictions: List<ObjectRestriction> = emptyList(),
        spaceId: SpaceId = SpaceId(defaultSpace)
    ) {
        openPage.stub {
            onBlocking {
                async(
                    OpenPage.Params(
                        obj = root,
                        saveAsLastOpened = true,
                        space = spaceId
                    )
                )
            } doReturn Resultat.success(
                Result.Success(
                    Payload(
                        context = root,
                        events = listOf(
                            Event.Command.ShowObject(
                                context = root,
                                root = root,
                                details = details.details,
                                blocks = document,
                                objectRestrictions = objectRestrictions
                            )
                        )
                    )
                )
            )
        }
    }

    fun stubMove() {
        move.stub {
            onBlocking { async(any()) } doReturn Resultat.Success(
                Payload(
                    context = root,
                    events = emptyList()
                )
            )
        }
    }

    fun stubInterceptEvents(
        params: InterceptEvents.Params = InterceptEvents.Params(context = root),
        flow: Flow<List<Event>> = flowOf(),
        stubInterceptThreadStatus: Boolean = true
    ) {
        interceptEvents.stub {
            onBlocking { build(params) } doReturn flow
        }
        if (stubInterceptThreadStatus) stubInterceptThreadStatus()
    }

    fun stubInterceptThreadStatus() {
        spaceSyncAndP2PStatusProvider.stub {
            onBlocking { observe() } doReturn emptyFlow()
        }
    }

    fun stubUpdateTextStyle(
        params: UpdateTextStyle.Params? = null,
        events: List<Event> = emptyList()
    ) {
        updateTextStyle.stub {
            onBlocking { invoke(params ?: any()) } doReturn Either.Right(
                Payload(
                    context = root,
                    events = events
                )
            )
        }
    }

    fun stubTurnIntoStyle(
        params: TurnIntoStyle.Params? = null,
        events: List<Event> = emptyList()
    ) {
        turnIntoStyle.stub {
            onBlocking { invoke(params ?: any()) } doReturn Either.Right(
                Payload(
                    context = root,
                    events = events
                )
            )
        }
    }

    fun stubUnlinkBlocks(
        params: UnlinkBlocks.Params? = null,
        events: List<Event> = emptyList()
    ) {
        unlinkBlocks.stub {
            onBlocking {
                if (params == null) invoke(any()) else invoke(params)
            } doReturn Either.Right(
                Payload(
                    context = root,
                    events = events
                )
            )
        }
    }

    fun stubCreateBlock(root: String) {
        createBlock.stub {
            onBlocking { async(any()) } doReturn Resultat.success(
                Pair(
                    MockDataFactory.randomString(), Payload(
                        context = root,
                        events = listOf()
                    )
                )
            )

        }
    }

    fun stubMergeBlocks(root: String) {
        mergeBlocks.stub {
            onBlocking { invoke(any()) } doReturn Either.Right(
                Payload(
                    context = root,
                    events = emptyList()
                )
            )
        }
    }

    fun stubCreateBlockLinkWithObject(root: String, target: String) {
        createBlockLinkWithObject.stub {
            onBlocking {
                async(any())
            } doReturn Resultat.success(
                CreateBlockLinkWithObject.Result(
                    id = root,
                    objectId = target,
                    payload = Payload(
                        context = root,
                        events = emptyList()
                    ),
                    details = emptyMap()
                )
            )
        }
    }

    fun stubUpdateText() {
        updateText.stub {
            onBlocking { invoke(any()) } doReturn Either.Right(Unit)
        }
    }

    fun stubCopy() {
        copy.stub {
            onBlocking { invoke(any()) } doReturn Either.Right(Unit)
        }
    }

    fun stubPaste() {
        paste.stub {
            onBlocking { invoke(any()) } doReturn Either.Right(
                Response.Clipboard.Paste(
                    0,
                    true,
                    emptyList(),
                    Payload("", emptyList())
                )
            )
        }
    }


    fun stubReplaceBlock() {
        replaceBlock.stub {
            onBlocking { invoke(any()) } doReturn Either.Right(
                Pair(
                    MockDataFactory.randomUuid(),
                    Payload(context = root, events = emptyList())
                )
            )
        }
    }

    /**
     * Stubs [replaceBlock] with the full identity-fork swap payload: [forked]
     * is added, [empty] is deleted, and the root's children become [children].
     */
    fun stubReplaceBlockWithSwap(empty: Block, forked: Block, children: List<Id>) {
        replaceBlock.stub {
            onBlocking { invoke(any()) } doReturn Either.Right(
                Pair(
                    forked.id,
                    Payload(
                        context = root,
                        events = listOf(
                            Event.Command.UpdateStructure(
                                context = root,
                                id = root,
                                children = children
                            ),
                            Event.Command.AddBlock(
                                context = root,
                                blocks = listOf(forked)
                            ),
                            Event.Command.DeleteBlock(
                                context = root,
                                targets = listOf(empty.id)
                            )
                        )
                    )
                )
            )
        }
    }

    /**
     * Stubs [createBlock] with the trailing-placeholder swap payload: [created]
     * is added and the root's children become [children].
     */
    fun stubCreateBlockWithSwap(created: Block, children: List<Id>) {
        createBlock.stub {
            onBlocking { async(any()) } doReturn Resultat.success(
                Pair(
                    created.id,
                    Payload(
                        context = root,
                        events = listOf(
                            Event.Command.AddBlock(
                                context = root,
                                blocks = listOf(created)
                            ),
                            Event.Command.UpdateStructure(
                                context = root,
                                id = root,
                                children = children
                            )
                        )
                    )
                )
            )
        }
    }

    /**
     * Stubs [updateLinkMark] to append the new mark to the given marks — the
     * real use case's merge for a mark that overlaps nothing.
     */
    fun stubUpdateLinkMarksToAppend() {
        updateLinkMark.stub {
            on { invoke(any(), any(), any()) } doAnswer { invocation ->
                val params = invocation.getArgument<UpdateLinkMarks.Params>(1)
                val onResult = invocation
                    .getArgument<(Either<Throwable, List<Block.Content.Text.Mark>>) -> Unit>(2)
                onResult(Either.Right(params.marks + params.newMark))
            }
        }
    }

    fun stubSplitBlock() {
        splitBlock.stub {
            onBlocking {
                invoke(any())
            } doReturn Either.Right(
                Pair(
                    MockDataFactory.randomUuid(),
                    Payload(context = root, events = emptyList())
                )
            )
        }
    }

    fun stubClosePage() {
        closePage.stub {
            onBlocking { async(any()) } doReturn Resultat.success(Unit)
        }
    }

    fun stubUpdateBackground() {
        updateBackgroundColor.stub {
            onBlocking {
                invoke(any())
            } doReturn Either.Right(
                Payload(context = root, events = emptyList())
            )
        }
    }

    fun stubUpdateTextColor() {
        updateTextColor.stub {
            onBlocking {
                invoke(any())
            } doReturn Either.Right(
                Payload(context = root, events = emptyList())
            )
        }
    }

    fun stubUpdateBlocksMark() {
        updateBlocksMark.stub {
            onBlocking {
                invoke(any())
            } doReturn Either.Right(
                Payload(context = root, events = emptyList())
            )
        }
    }

    fun stubDuplicateBlock(
        new: Id,
        events: List<Event>
    ) {
        duplicateBlock.stub {
            onBlocking { invoke(any()) } doReturn Either.Right(
                Pair(
                    listOf(new),
                    Payload(
                        context = root,
                        events = events
                    )
                )
            )
        }
    }

    fun stubSearchObjects(objects: List<ObjectWrapper.Basic> = emptyList()) {
        searchObjects.stub {
            onBlocking { invoke(any()) } doReturn Either.Right(objects)
        }
    }

    fun stubGetDefaultObjectType(
        id: String = MockDataFactory.randomString(),
        type: String = MockDataFactory.randomString(),
        name: String? = null,
        template: String? = null
    ) {
        getDefaultObjectType.stub {
            onBlocking { async(
                params = SpaceId(defaultSpace)
            ) } doReturn Resultat.success(
                GetDefaultObjectType.Response(
                    id = TypeId(id),
                    type = TypeKey(type),
                    name = name,
                    defaultTemplate = template
                )
            )
        }
    }

    fun stubCreateObjectAsMentionOrLink(name: String, id: String) {
        val result = CreateObjectAsMentionOrLink.Result(id, name)
        createObjectAsMentionOrLink.stub {
            onBlocking { async(any()) } doReturn Resultat.success(result)
        }
    }

    protected fun stubGetTemplates(
        templates: List<ObjectWrapper.Basic> = emptyList()
    ) {
        getTemplates.stub {
            onBlocking { run(any()) } doReturn templates
        }
    }

    fun stubFillRow() {
        fillTableRow.stub {
            onBlocking { invoke(any()) } doReturn Either.Right(
                Payload(
                    context = root,
                    events = listOf()
                )
            )
        }
    }

    fun stubGetObjectTypes(types: List<ObjectWrapper.Type>) {
        getObjectTypes.stub {
            onBlocking {
                async(any())
            } doReturn Resultat.success(types)
        }
    }

    fun setStoreOfObjectTypes(types: List<ObjectWrapper.Type>) {
        runBlocking {
            storeOfObjectTypes.clear()
            storeOfObjectTypes.merge(types = types)
        }
    }

    fun stubFileLimitEvents() {
        interceptFileLimitEvents.stub {
            onBlocking { run(Unit) } doReturn emptyFlow()
        }
    }

    fun stubSetInternalFlags() {
        setObjectInternalFlags.stub {
            onBlocking { async(any()) } doReturn Resultat.success(Payload(root, emptyList()))
        }
    }

    fun stubSpaceManager(space: String = defaultSpace) {
        spaceManager.stub {
            onBlocking { get() } doReturn space
        }
    }

    fun stubUserPermission(
        space: SpaceId = SpaceId(defaultSpace),
        permission: SpaceMemberPermissions = SpaceMemberPermissions.OWNER
    ) {
        (permissions as UserPermissionProviderStub).stubObserve(
            space, permission
        )
    }

    fun stubAnalyticSpaceHelperDelegate() {
        Mockito.`when`(analyticSpaceHelperDelegate.provideParams(defaultSpace))
            .thenReturn(AnalyticSpaceHelperDelegate.Params.EMPTY)

        analyticSpaceHelperDelegate.stub {
            on { provideParams(defaultSpace) } doReturn AnalyticSpaceHelperDelegate.Params.EMPTY
        }
    }

    @Mock
    lateinit var stringResourceProvider: StringResourceProvider

    fun proceedWithDefaultBeforeTestStubbing() {
        fieldParser = FieldParserImpl(dateProvider, logger, getDateObjectByTimestamp, stringResourceProvider)
        stubAnalyticSpaceHelperDelegate()
        stubSpaceManager()
        stubUserPermission()
        stubGetNetworkMode()
        stubFileLimitEvents()
        stubInterceptEvents()
        stubClosePage()
        stubInterceptThreadStatus()
        val spaceView = StubSpaceView(
            id = defaultSpace,
            spaceUxType = SpaceUxType.DATA
        )
        spacedViews.stub {
            onBlocking { get(space = SpaceId(defaultSpace)) } doReturn spaceView
        }
    }
}