package com.aparokio.aparok.di.feature

import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_utils.di.scope.PerDialog
import com.aparokio.aparok.core_utils.tools.FeatureToggles
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.interactor.CreateBlock
import com.aparokio.aparok.domain.block.interactor.UpdateFields
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.collections.AddObjectToCollection
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.dashboard.interactor.SetObjectListIsFavorite
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.favorites.AddPersonalFavorite
import com.aparokio.aparok.presentation.widgets.ObservePersonalFavoriteTargets
import com.aparokio.aparok.domain.favorites.RemovePersonalFavorite
import com.aparokio.aparok.domain.misc.DeepLinkResolver
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.multiplayer.GetSpaceInviteLink
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.`object`.DuplicateObject
import com.aparokio.aparok.domain.`object`.GetObject
import com.aparokio.aparok.domain.`object`.SetObjectDetails
import com.aparokio.aparok.domain.objects.SetObjectListIsArchived
import com.aparokio.aparok.domain.page.AddBackLinkToObject
import com.aparokio.aparok.domain.page.CloseObject
import com.aparokio.aparok.domain.page.OpenPage
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.domain.relations.AddToFeaturedRelations
import com.aparokio.aparok.domain.relations.DeleteRelationFromObject
import com.aparokio.aparok.domain.relations.RemoveFromFeaturedRelations
import com.aparokio.aparok.domain.templates.CreateTemplateFromObject
import com.aparokio.aparok.domain.widgets.CreateWidget
import com.aparokio.aparok.domain.widgets.DeleteWidget
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.domain.workspace.spaceIdOrNull
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.common.Action
import com.aparokio.aparok.presentation.common.Delegator
import com.aparokio.aparok.presentation.common.PayloadDelegator
import com.aparokio.aparok.presentation.editor.Editor
import com.aparokio.aparok.presentation.objects.menu.ObjectMenuOptionsProvider
import com.aparokio.aparok.presentation.objects.menu.ObjectMenuOptionsProviderImpl
import com.aparokio.aparok.presentation.objects.menu.ObjectMenuViewModel
import com.aparokio.aparok.presentation.objects.menu.ObjectSetMenuViewModel
import com.aparokio.aparok.presentation.sets.state.ObjectState
import com.aparokio.aparok.presentation.util.Dispatcher
import com.aparokio.aparok.presentation.util.downloader.DebugGoroutinesShareDownloader
import com.aparokio.aparok.presentation.util.downloader.DebugTreeShareDownloader
import com.aparokio.aparok.ui.editor.sheets.ObjectMenuFragment
import com.aparokio.aparok.ui.sets.ObjectSetMenuFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull


@Subcomponent(modules = [ObjectMenuModuleBase::class, ObjectMenuModule::class])
@PerDialog
interface ObjectMenuComponent {
    @Subcomponent.Builder
    interface Builder {
        fun base(module: ObjectMenuModuleBase): Builder
        fun module(module: ObjectMenuModule): Builder
        fun build(): ObjectMenuComponent
    }

    fun inject(fragment: ObjectMenuFragment)
}

@Subcomponent(modules = [ObjectMenuModuleBase::class, ObjectSetMenuModule::class])
@PerDialog
interface ObjectSetMenuComponent {
    @Subcomponent.Builder
    interface Builder {
        fun base(module: ObjectMenuModuleBase): Builder
        fun module(module: ObjectSetMenuModule): Builder
        fun build(): ObjectSetMenuComponent
    }

    fun inject(fragment: ObjectSetMenuFragment)
}

@Module
object ObjectMenuModuleBase {

    @JvmStatic
    @Provides
    @PerDialog
    fun provideAddBackLinkToObject(
        openPage: OpenPage,
        createBlock: CreateBlock,
        closeObject: CloseObject,
        dispatchers: AppCoroutineDispatchers
    ): AddBackLinkToObject = AddBackLinkToObject(openPage, createBlock, closeObject, dispatchers)
}

@Module
object ObjectMenuModule {
    @JvmStatic
    @Provides
    @PerDialog
    fun provideViewModelFactory(
        duplicateObject: DuplicateObject,
        debugTreeShareDownloader: DebugTreeShareDownloader,
        addBackLinkToObject: AddBackLinkToObject,
        urlBuilder: UrlBuilder,
        storage: Editor.Storage,
        analytics: Analytics,
        dispatcher: Dispatcher<Payload>,
        updateFields: UpdateFields,
        featureToggles: FeatureToggles,
        delegator: Delegator<Action>,
        addObjectToCollection: AddObjectToCollection,
        createTemplateFromObject: CreateTemplateFromObject,
        setObjectDetails: SetObjectDetails,
        debugGoroutinesShareDownloader: DebugGoroutinesShareDownloader,
        createWidget: CreateWidget,
        spaceManager: SpaceManager,
        deepLinkResolver: DeepLinkResolver,
        analyticSpaceHelperDelegate: AnalyticSpaceHelperDelegate,
        payloadDelegator: PayloadDelegator,
        setObjectListIsFavorite: SetObjectListIsFavorite,
        setObjectIsArchived: SetObjectListIsArchived,
        fieldParser: FieldParser,
        spaceViewSubscriptionContainer: SpaceViewSubscriptionContainer,
        getSpaceInviteLink: GetSpaceInviteLink,
        addToFeaturedRelations: AddToFeaturedRelations,
        removeFromFeaturedRelations: RemoveFromFeaturedRelations,
        userPermissionProvider: UserPermissionProvider,
        deleteRelationFromObject: DeleteRelationFromObject,
        objectMenuOptionsProvider: ObjectMenuOptionsProvider,
        showObject: GetObject,
        deleteWidget: DeleteWidget,
        addPersonalFavorite: AddPersonalFavorite,
        removePersonalFavorite: RemovePersonalFavorite
    ): ObjectMenuViewModel.Factory = ObjectMenuViewModel.Factory(
        setObjectIsArchived = setObjectIsArchived,
        duplicateObject = duplicateObject,
        debugTreeShareDownloader = debugTreeShareDownloader,
        addBackLinkToObject = addBackLinkToObject,
        urlBuilder = urlBuilder,
        storage = storage,
        analytics = analytics,
        dispatcher = dispatcher,
        updateFields = updateFields,
        delegator = delegator,
        menuOptionsProvider = objectMenuOptionsProvider,
        addObjectToCollection = addObjectToCollection,
        createTemplateFromObject = createTemplateFromObject,
        setObjectDetails = setObjectDetails,
        debugGoroutinesShareDownloader = debugGoroutinesShareDownloader,
        createWidget = createWidget,
        spaceManager = spaceManager,
        deepLinkResolver = deepLinkResolver,
        analyticSpaceHelperDelegate = analyticSpaceHelperDelegate,
        payloadDelegator = payloadDelegator,
        setObjectListIsFavorite = setObjectListIsFavorite,
        fieldParser = fieldParser,
        getSpaceInviteLink = getSpaceInviteLink,
        spaceViewSubscriptionContainer = spaceViewSubscriptionContainer,
        addToFeaturedRelations = addToFeaturedRelations,
        removeFromFeaturedRelations = removeFromFeaturedRelations,
        userPermissionProvider = userPermissionProvider,
        deleteRelationFromObject = deleteRelationFromObject,
        showObject = showObject,
        deleteWidget = deleteWidget,
        addPersonalFavorite = addPersonalFavorite,
        removePersonalFavorite = removePersonalFavorite
    )

    @JvmStatic
    @Provides
    @PerDialog
    fun provideDeleteRelationFromObject(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): DeleteRelationFromObject = DeleteRelationFromObject(
        repo = repo,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerDialog
    fun provideSetObjectDetails(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetObjectDetails = SetObjectDetails(
        repo,
        dispatchers
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    @Provides
    @PerDialog
    @JvmStatic
    fun provideMenuOptionsProvider(
        storage: Editor.Storage,
        spaceManager: SpaceManager,
        observePersonalFavoriteTargets: ObservePersonalFavoriteTargets,
        userPermissionProvider: UserPermissionProvider
    ): ObjectMenuOptionsProvider {
        // From the state, not the config flow: a space can be active without a config
        // (SpaceManager.activate, used by quick capture to skip Workspace.Open), and the
        // config flow stays silent in that case — which would leave this menu, opened from
        // the quick-capture sheet, with no space at all.
        val spaceIdFlow = spaceManager.state()
            .mapNotNull { it.spaceIdOrNull() }
            .distinctUntilChanged()
        val personalFavoriteTargets = spaceIdFlow.flatMapLatest { space ->
            observePersonalFavoriteTargets(space).map { it.toSet() }
        }
        val canToggleChannelPin = spaceIdFlow.flatMapLatest { space ->
            userPermissionProvider.observe(space).map { it?.isOwnerOrEditor() == true }
        }
        return ObjectMenuOptionsProviderImpl(
            objectViewDetailsFlow = storage.details.stream(),
            hasObjectLayoutConflict = storage.hasLayoutOrRelationConflict.stream(),
            personalFavoriteTargets = personalFavoriteTargets,
            canToggleChannelPin = canToggleChannelPin
        )
    }

    @JvmStatic
    @Provides
    @PerDialog
    fun provideAddObjectToCollection(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): AddObjectToCollection = AddObjectToCollection(
        repo = repo,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerDialog
    fun provideFavoriteUseCase(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetObjectListIsFavorite = SetObjectListIsFavorite(repo = repo, dispatchers = dispatchers)

    @JvmStatic
    @Provides
    @PerDialog
    fun provideArchiveUseCase(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetObjectListIsArchived = SetObjectListIsArchived(repo = repo, dispatchers = dispatchers)

    @JvmStatic
    @Provides
    @PerDialog
    fun addToFeaturedRelations(repo: BlockRepository, dispatchers: AppCoroutineDispatchers): AddToFeaturedRelations =
        AddToFeaturedRelations(repo, dispatchers)

    @JvmStatic
    @Provides
    @PerDialog
    fun removeFromFeaturedRelations(repo: BlockRepository, dispatchers: AppCoroutineDispatchers): RemoveFromFeaturedRelations =
        RemoveFromFeaturedRelations(repo, dispatchers)
}

@Module
object ObjectSetMenuModule {

    @JvmStatic
    @Provides
    @PerDialog
    fun provideUpdateFieldsUseCase(
        repo: BlockRepository
    ): UpdateFields = UpdateFields(repo)

    @JvmStatic
    @Provides
    @PerDialog
    fun provideViewModelFactory(
        addBackLinkToObject: AddBackLinkToObject,
        duplicateObject: DuplicateObject,
        delegator: Delegator<Action>,
        urlBuilder: UrlBuilder,
        analytics: Analytics,
        state: MutableStateFlow<ObjectState>,
        updateFields: UpdateFields,
        featureToggles: FeatureToggles,
        dispatcher: Dispatcher<Payload>,
        addObjectToCollection: AddObjectToCollection,
        debugGoroutinesShareDownloader: DebugGoroutinesShareDownloader,
        createWidget: CreateWidget,
        spaceManager: SpaceManager,
        deepLinkResolver: DeepLinkResolver,
        analyticSpaceHelperDelegate: AnalyticSpaceHelperDelegate,
        payloadDelegator: PayloadDelegator,
        setObjectListIsFavorite: SetObjectListIsFavorite,
        setObjectIsArchived: SetObjectListIsArchived,
        fieldParser: FieldParser,
        getSpaceInviteLink: GetSpaceInviteLink,
        spaceViewSubscriptionContainer: SpaceViewSubscriptionContainer,
        addToFeaturedRelations: AddToFeaturedRelations,
        removeFromFeaturedRelations: RemoveFromFeaturedRelations,
        userPermissionProvider: UserPermissionProvider,
        deleteRelationFromObject: DeleteRelationFromObject,
        setObjectDetails: SetObjectDetails,
        objectMenuOptionsProvider: ObjectMenuOptionsProvider,
        showObject: GetObject,
        deleteWidget: DeleteWidget,
        addPersonalFavorite: AddPersonalFavorite,
        removePersonalFavorite: RemovePersonalFavorite
    ): ObjectSetMenuViewModel.Factory = ObjectSetMenuViewModel.Factory(
        setObjectListIsArchived = setObjectIsArchived,
        addBackLinkToObject = addBackLinkToObject,
        duplicateObject = duplicateObject,
        urlBuilder = urlBuilder,
        delegator = delegator,
        analytics = analytics,
        objectState = state,
        dispatcher = dispatcher,
        menuOptionsProvider = objectMenuOptionsProvider,
        addObjectToCollection = addObjectToCollection,
        debugGoroutinesShareDownloader = debugGoroutinesShareDownloader,
        createWidget = createWidget,
        spaceManager = spaceManager,
        deepLinkResolver = deepLinkResolver,
        analyticSpaceHelperDelegate = analyticSpaceHelperDelegate,
        payloadDelegator = payloadDelegator,
        setObjectListIsFavorite = setObjectListIsFavorite,
        fieldParser = fieldParser,
        getSpaceInviteLink = getSpaceInviteLink,
        spaceViewSubscriptionContainer = spaceViewSubscriptionContainer,
        addToFeaturedRelations = addToFeaturedRelations,
        removeFromFeaturedRelations = removeFromFeaturedRelations,
        userPermissionProvider = userPermissionProvider,
        deleteRelationFromObject = deleteRelationFromObject,
        updateFields = updateFields,
        setObjectDetails = setObjectDetails,
        showObject = showObject,
        deleteWidget = deleteWidget,
        addPersonalFavorite = addPersonalFavorite,
        removePersonalFavorite = removePersonalFavorite
    )

    @JvmStatic
    @Provides
    @PerDialog
    fun provideDeleteRelationFromObject(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): DeleteRelationFromObject = DeleteRelationFromObject(
        repo = repo,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerDialog
    fun provideOpenPage(
        repo: BlockRepository,
        settings: UserSettingsRepository,
        dispatchers: AppCoroutineDispatchers
    ): OpenPage = OpenPage(
        repo = repo,
        settings = settings,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerDialog
    fun provideFavoriteUseCase(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetObjectListIsFavorite = SetObjectListIsFavorite(repo = repo, dispatchers = dispatchers)

    @OptIn(ExperimentalCoroutinesApi::class)
    @JvmStatic
    @Provides
    @PerDialog
    fun provideMenuOptionsProvider(
        state: MutableStateFlow<ObjectState>,
        spaceManager: SpaceManager,
        observePersonalFavoriteTargets: ObservePersonalFavoriteTargets,
        userPermissionProvider: UserPermissionProvider
    ): ObjectMenuOptionsProvider {
        val objectViewDetailsFlow = state
            .flatMapLatest { currentState ->
                if (currentState is ObjectState.DataView) {
                    flowOf(currentState.details)
                } else {
                    emptyFlow()
                }
            }
            .distinctUntilChanged()

        val hasObjectLayoutConflictFlow = state
            .flatMapLatest { currentState ->
                if (currentState is ObjectState.DataView) {
                    flowOf(currentState.hasObjectLayoutConflict)
                } else {
                    emptyFlow()
                }
            }
            .distinctUntilChanged()

        // From the state, not the config flow: a space can be active without a config
        // (SpaceManager.activate, used by quick capture to skip Workspace.Open), and the
        // config flow stays silent in that case — which would leave this menu, opened from
        // the quick-capture sheet, with no space at all.
        val spaceIdFlow = spaceManager.state()
            .mapNotNull { it.spaceIdOrNull() }
            .distinctUntilChanged()
        val personalFavoriteTargets = spaceIdFlow.flatMapLatest { space ->
            observePersonalFavoriteTargets(space).map { it.toSet() }
        }
        val canToggleChannelPin = spaceIdFlow.flatMapLatest { space ->
            userPermissionProvider.observe(space).map { it?.isOwnerOrEditor() == true }
        }

        return ObjectMenuOptionsProviderImpl(
            objectViewDetailsFlow = objectViewDetailsFlow,
            hasObjectLayoutConflict = hasObjectLayoutConflictFlow,
            personalFavoriteTargets = personalFavoriteTargets,
            canToggleChannelPin = canToggleChannelPin
        )
    }

    @JvmStatic
    @Provides
    @PerDialog
    fun provideSetObjectDetails(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetObjectDetails = SetObjectDetails(
        repo,
        dispatchers
    )

    @JvmStatic
    @Provides
    @PerDialog
    fun addToFeaturedRelations(repo: BlockRepository, dispatchers: AppCoroutineDispatchers): AddToFeaturedRelations =
        AddToFeaturedRelations(repo, dispatchers)

    @JvmStatic
    @Provides
    @PerDialog
    fun removeFromFeaturedRelations(repo: BlockRepository, dispatchers: AppCoroutineDispatchers): RemoveFromFeaturedRelations =
        RemoveFromFeaturedRelations(repo, dispatchers)
}