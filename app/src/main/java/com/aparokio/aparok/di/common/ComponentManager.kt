package com.aparokio.aparok.di.common

import com.aparokio.aparok.BuildConfig
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.di.feature.CreateBookmarkModule
import com.aparokio.aparok.di.feature.CreateObjectModule
import com.aparokio.aparok.di.feature.DaggerAllContentComponent
import com.aparokio.aparok.di.feature.DaggerAppPreferencesComponent
import com.aparokio.aparok.di.feature.DaggerBacklinkOrAddToObjectComponent
import com.aparokio.aparok.di.feature.DaggerCreateObjectFeatureComponent
import com.aparokio.aparok.di.feature.DaggerCreateObjectTypeComponent
import com.aparokio.aparok.di.feature.DaggerDateObjectComponent
import com.aparokio.aparok.di.feature.DaggerEditTypePropertiesComponent
import com.aparokio.aparok.di.feature.DaggerLinkToObjectComponent
import com.aparokio.aparok.di.feature.DaggerMediaComponent
import com.aparokio.aparok.di.feature.DaggerMoveToComponent
import com.aparokio.aparok.di.feature.DaggerMySitesComponent
import com.aparokio.aparok.di.feature.DaggerObjectTypeComponent
import com.aparokio.aparok.di.feature.DaggerPublishToWebComponent
import com.aparokio.aparok.di.feature.DaggerSpacePropertiesComponent
import com.aparokio.aparok.di.feature.DaggerSpaceTypesComponent
import com.aparokio.aparok.di.feature.DaggerSplashComponent
import com.aparokio.aparok.di.feature.DebugSettingsModule
import com.aparokio.aparok.di.feature.DefaultComponentParam
import com.aparokio.aparok.di.feature.EditorSessionModule
import com.aparokio.aparok.di.feature.EditorUseCaseModule
import com.aparokio.aparok.di.feature.KeychainPhraseModule
import com.aparokio.aparok.di.feature.LinkToObjectOrWebModule
import com.aparokio.aparok.di.feature.MainEntryModule
import com.aparokio.aparok.di.feature.ModifyViewerSortModule
import com.aparokio.aparok.di.feature.ObjectAppearanceIconModule
import com.aparokio.aparok.di.feature.ObjectAppearancePreviewLayoutModule
import com.aparokio.aparok.di.feature.ObjectAppearanceSettingModule
import com.aparokio.aparok.di.feature.ObjectIconPickerBaseModule
import com.aparokio.aparok.di.feature.ObjectIconPickerModule
import com.aparokio.aparok.di.feature.ObjectMenuModule
import com.aparokio.aparok.di.feature.ObjectMenuModuleBase
import com.aparokio.aparok.di.feature.ObjectRelationListModule
import com.aparokio.aparok.di.feature.ObjectSetCreateBookmarkRecordModule
import com.aparokio.aparok.di.feature.ObjectSetIconPickerModule
import com.aparokio.aparok.di.feature.ObjectSetMenuModule
import com.aparokio.aparok.di.feature.ObjectSetModule
import com.aparokio.aparok.di.feature.ObjectSetRecordModule
import com.aparokio.aparok.di.feature.ObjectSetSettingsModule
import com.aparokio.aparok.di.feature.RelationDataViewDateValueModule
import com.aparokio.aparok.di.feature.RelationDataViewTextValueModule
import com.aparokio.aparok.di.feature.RelationDateValueModule
import com.aparokio.aparok.di.feature.RelationTextValueModule
import com.aparokio.aparok.di.feature.SelectCoverObjectModule
import com.aparokio.aparok.di.feature.SelectCoverObjectSetModule
import com.aparokio.aparok.di.feature.SelectSortRelationModule
import com.aparokio.aparok.di.feature.TextBlockIconPickerModule
import com.aparokio.aparok.di.feature.ViewerFilterModule
import com.aparokio.aparok.di.feature.ViewerSortModule
import com.aparokio.aparok.di.feature.auth.DaggerDeletedAccountComponent
import com.aparokio.aparok.di.feature.chats.DaggerChatComponent
import com.aparokio.aparok.di.feature.discussions.DaggerDiscussionComponent
import com.aparokio.aparok.di.feature.chats.DaggerChatReactionComponent
import com.aparokio.aparok.di.feature.chats.DaggerSelectChatIconComponent
import com.aparokio.aparok.di.feature.chats.DaggerSelectChatReactionComponent
import com.aparokio.aparok.di.feature.cover.UnsplashModule
import com.aparokio.aparok.di.feature.gallery.DaggerGalleryInstallationComponent
import com.aparokio.aparok.di.feature.home.DaggerHomeScreenComponent
import com.aparokio.aparok.di.feature.membership.DaggerMembershipComponent
import com.aparokio.aparok.di.feature.multiplayer.DaggerRequestJoinSpaceComponent
import com.aparokio.aparok.di.feature.multiplayer.DaggerShareSpaceComponent
import com.aparokio.aparok.di.feature.multiplayer.DaggerSpaceJoinRequestComponent
import com.aparokio.aparok.di.feature.notifications.DaggerNotificationComponent
import com.aparokio.aparok.di.feature.notifications.DaggerPushContentComponent
import com.aparokio.aparok.di.feature.objects.DaggerSelectObjectTypeComponent
import com.aparokio.aparok.di.feature.onboarding.DaggerOnboardingComponent
import com.aparokio.aparok.di.feature.onboarding.DaggerOnboardingStartComponent
import com.aparokio.aparok.di.feature.onboarding.login.DaggerOnboardingMnemonicLoginComponent
import com.aparokio.aparok.di.feature.onboarding.signup.DaggerOnboardingMnemonicComponent
import com.aparokio.aparok.di.feature.onboarding.signup.DaggerOnboardingSoulCreationComponent
import com.aparokio.aparok.di.feature.participant.DaggerParticipantComponent
import com.aparokio.aparok.di.feature.relations.DaggerRelationCreateFromLibraryComponent
import com.aparokio.aparok.di.feature.relations.LimitObjectTypeModule
import com.aparokio.aparok.di.feature.relations.RelationAddToDataViewModule
import com.aparokio.aparok.di.feature.relations.RelationAddToObjectModule
import com.aparokio.aparok.di.feature.relations.RelationCreateFromScratchForDataViewModule
import com.aparokio.aparok.di.feature.relations.RelationCreateFromScratchForObjectBlockModule
import com.aparokio.aparok.di.feature.relations.RelationCreateFromScratchForObjectModule
import com.aparokio.aparok.di.feature.search.DaggerGlobalSearchComponent
import com.aparokio.aparok.di.feature.search.DaggerSearchV2Component
import com.aparokio.aparok.feature_search.presentation.SearchViewModel
import com.aparokio.aparok.di.feature.sets.CreateFilterModule
import com.aparokio.aparok.di.feature.sets.ModifyFilterModule
import com.aparokio.aparok.di.feature.sets.PickConditionModule
import com.aparokio.aparok.di.feature.sets.SelectFilterRelationModule
import com.aparokio.aparok.di.feature.quickcapture.DaggerQuickCaptureComponent
import com.aparokio.aparok.di.feature.settings.DaggerAboutAppComponent
import com.aparokio.aparok.di.feature.settings.DaggerAppearanceComponent
import com.aparokio.aparok.di.feature.settings.DaggerDebugComponent
import com.aparokio.aparok.di.feature.settings.DaggerExperimentalFeaturesComponent
import com.aparokio.aparok.di.feature.settings.DaggerFilesStorageComponent
import com.aparokio.aparok.di.feature.settings.DaggerSpacesStorageComponent
import com.aparokio.aparok.di.feature.settings.LogoutWarningModule
import com.aparokio.aparok.di.feature.settings.ProfileModule
import com.aparokio.aparok.di.feature.sharing.DaggerSharingComponent
import com.aparokio.aparok.di.feature.spaces.DaggerCreateSpaceComponent
import com.aparokio.aparok.di.feature.spaces.DaggerManageSectionsComponent
import com.aparokio.aparok.di.feature.spaces.DaggerSpaceListComponent
import com.aparokio.aparok.di.feature.spaces.DaggerSpaceSettingsComponent
import com.aparokio.aparok.di.feature.templates.DaggerTemplateBlankComponent
import com.aparokio.aparok.di.feature.templates.DaggerTemplateSelectComponent
import com.aparokio.aparok.di.feature.vault.DaggerVaultComponent
import com.aparokio.aparok.di.feature.widgets.DaggerCreateChatObjectComponent
import com.aparokio.aparok.di.feature.oswidgets.DaggerCreateObjectWidgetConfigComponent
import com.aparokio.aparok.di.feature.oswidgets.DaggerDataViewWidgetConfigComponent
import com.aparokio.aparok.di.feature.oswidgets.DaggerObjectShortcutWidgetConfigComponent
import com.aparokio.aparok.di.feature.widgets.DaggerSelectWidgetSourceComponent
import com.aparokio.aparok.di.feature.widgets.DaggerSelectWidgetTypeComponent
import com.aparokio.aparok.di.main.MainComponent
import com.aparokio.aparok.feature_allcontent.presentation.AllContentViewModel
import com.aparokio.aparok.feature_chats.presentation.ChatReactionViewModel
import com.aparokio.aparok.feature_chats.presentation.ChatViewModel
import com.aparokio.aparok.feature_discussions.presentation.DiscussionViewModel
import com.aparokio.aparok.feature_chats.presentation.SelectChatReactionViewModel
import com.aparokio.aparok.feature_create_object.presentation.NewCreateObjectViewModel
import com.aparokio.aparok.feature_date.viewmodel.DateObjectVmParams
import com.aparokio.aparok.feature_object_type.ui.ObjectTypeVmParams
import com.aparokio.aparok.feature_object_type.viewmodel.CreateTypeVmParams
import com.aparokio.aparok.feature_properties.add.EditTypePropertiesVmParams
import com.aparokio.aparok.feature_properties.space.SpacePropertiesViewModel
import com.aparokio.aparok.gallery_experience.viewmodel.GalleryInstallationViewModel
import com.aparokio.aparok.presentation.editor.EditorViewModel
import com.aparokio.aparok.presentation.history.VersionHistoryViewModel
import com.aparokio.aparok.presentation.home.HomeScreenVmParams
import com.aparokio.aparok.presentation.linking.LinkToObjectOrWebViewModel
import com.aparokio.aparok.presentation.moving.MoveToViewModel
import com.aparokio.aparok.presentation.multiplayer.RequestJoinSpaceViewModel
import com.aparokio.aparok.presentation.multiplayer.ShareSpaceViewModel
import com.aparokio.aparok.presentation.multiplayer.SpaceJoinRequestViewModel
import com.aparokio.aparok.presentation.objects.ObjectTypeChangeViewModel
import com.aparokio.aparok.presentation.objects.SelectObjectTypeViewModel
import com.aparokio.aparok.presentation.profile.ParticipantViewModel
import com.aparokio.aparok.presentation.publishtoweb.MySitesViewModel
import com.aparokio.aparok.presentation.publishtoweb.PublishToWebViewModel
import com.aparokio.aparok.presentation.relations.RelationAddViewModelBase
import com.aparokio.aparok.presentation.relations.RelationListViewModel
import com.aparokio.aparok.presentation.relations.option.CreateOrEditOptionViewModel
import com.aparokio.aparok.presentation.relations.value.`object`.ObjectValueViewModel
import com.aparokio.aparok.presentation.relations.value.tagstatus.TagOrStatusValueViewModel
import com.aparokio.aparok.presentation.search.GlobalSearchViewModel
import com.aparokio.aparok.presentation.search.ObjectSearchViewModel
import com.aparokio.aparok.presentation.sets.ObjectSetViewModel
import com.aparokio.aparok.presentation.settings.SpacesStorageViewModel
import com.aparokio.aparok.presentation.spaces.CreateSpaceViewModel
import com.aparokio.aparok.presentation.spaces.SpaceSettingsViewModel
import com.aparokio.aparok.presentation.types.SpaceTypesViewModel
import com.aparokio.aparok.presentation.widgets.CreateChatObjectViewModel
import com.aparokio.aparok.presentation.widgets.collection.CollectionViewModel
import com.aparokio.aparok.ui.widgets.collection.DaggerCollectionComponent
import timber.log.Timber

class ComponentManager(
    private val main: MainComponent,
    private val provider: HasComponentDependencies
) {

    val mainEntryComponent = Component {
        main.mainEntryComponentBuilder().module(MainEntryModule).build()
    }

    val debugSettingsComponent = Component {
        main
            .debugSettingsBuilder()
            .module(DebugSettingsModule())
            .build()
    }

    val debugComponent = Component {
        DaggerDebugComponent
            .factory()
            .create(findComponentDependencies())
    }

    val experimentalFeaturesComponent = Component {
        DaggerExperimentalFeaturesComponent
            .factory()
            .create(findComponentDependencies())
    }

    val quickCaptureComponent = Component {
        DaggerQuickCaptureComponent
            .factory()
            .create(findComponentDependencies())
    }

    val splashLoginComponent = Component {
       DaggerSplashComponent
           .factory()
           .create(findComponentDependencies())
    }

    val keychainPhraseComponent = Component {
        main
            .keychainPhraseComponentBuilder()
            .keychainPhraseModule(KeychainPhraseModule)
            .build()
    }

    val homeScreenComponent = ComponentWithParams { vmParams: HomeScreenVmParams ->
        DaggerHomeScreenComponent
            .factory()
            .create(
                vmParams = vmParams, dependencies = findComponentDependencies()
            )
    }

    /**
     * Single-slot cache for the widget overlay. Each fragment open fires get(),
     * each fragment destroy fires release() which clears the slot so the next
     * open builds a fresh DaggerHomeScreenComponent.
     *
     * IMPORTANT: the overlay must NOT be opened while [WidgetsScreenFragment]
     * is alive for the same space. Both fragments use HomeScreenViewModel,
     * and HomeScreenViewModel.onCleared() calls closeObject against the shared
     * widget object. Running both simultaneously causes the first cleared VM
     * to close the widget object the other VM is still subscribed to.
     *
     * In practice the overlay is only triggered from Chat/Editor/ObjectSet
     * destinations where the root widgets screen is not in the back stack,
     * so this invariant is preserved structurally — but any future
     * "show overlay from the widgets screen itself" path must be prevented.
     */
    val widgetOverlayComponent = ComponentWithParams { vmParams: HomeScreenVmParams ->
        DaggerHomeScreenComponent
            .factory()
            .create(
                vmParams = vmParams, dependencies = findComponentDependencies()
            )
    }

    val collectionComponent = ComponentWithParams { vmParams: CollectionViewModel.VmParams ->
        DaggerCollectionComponent
            .factory()
            .create(
                dependencies = findComponentDependencies(),
                vmParams = vmParams
            )
    }

    val selectWidgetSourceSubcomponent =
        ComponentWithParams { params: ObjectSearchViewModel.VmParams ->
            DaggerSelectWidgetSourceComponent.factory()
                .create(params, findComponentDependencies())
        }

    val selectWidgetTypeSubcomponent = Component {
        DaggerSelectWidgetTypeComponent.factory().create(
            findComponentDependencies()
        )
    }

    val createObjectComponent = Component {
        main
            .createObjectComponent()
            .module(CreateObjectModule)
            .build()
    }

    val templateSelectComponent = Component {
        DaggerTemplateSelectComponent.factory().create(findComponentDependencies())
    }

    val editorComponent = ComponentMapWithParam { param: DefaultComponentParam ->
        main
            .editorComponentBuilder()
            .session(EditorSessionModule)
            .usecase(EditorUseCaseModule)
            .withParams(
                EditorViewModel.Params(
                    ctx = param.ctx,
                    space = param.space
                )
            )
            .build()
    }

    val objectIconPickerComponent = ComponentWithParams { param: DefaultComponentParam ->
        editorComponent
            .get(key = param.ctx, param = param)
            .objectIconPickerComponent()
            .base(ObjectIconPickerBaseModule)
            .module(ObjectIconPickerModule)
            .build()
    }

    val textBlockIconPickerComponent = ComponentWithParams { param: DefaultComponentParam ->
        editorComponent
            .get(key = param.ctx, param = param)
            .textBlockIconPickerComponent()
            .module(TextBlockIconPickerModule)
            .build()
    }

    val objectSetIconPickerComponent = ComponentWithParams { param: DefaultComponentParam ->
        objectSetComponent
            .get(key = param.ctx, param = param)
            .objectSetIconPickerComponent()
            .base(ObjectIconPickerBaseModule)
            .module(ObjectSetIconPickerModule)
            .build()
    }

    val objectAppearanceSettingComponent = ComponentWithParams { param: DefaultComponentParam ->
        editorComponent
            .get(key = param.ctx, param = param)
            .objectAppearanceSettingComponent()
            .module(ObjectAppearanceSettingModule)
            .build()
    }

    val objectAppearanceIconComponent = ComponentWithParams { param: DefaultComponentParam ->
        editorComponent
            .get(key = param.ctx, param = param)
            .objectAppearanceIconComponent()
            .module(ObjectAppearanceIconModule)
            .build()
    }

    val objectAppearancePreviewLayoutComponent = ComponentWithParams { param: DefaultComponentParam ->
        editorComponent
            .get(key = param.ctx, param = param)
            .objectAppearancePreviewLayoutComponent()
            .module(ObjectAppearancePreviewLayoutModule)
            .build()
    }

    val objectAppearanceChooseDescriptionComponent = ComponentWithParams { param: DefaultComponentParam ->
        editorComponent
            .get(key = param.ctx, param = param)
            .objectAppearanceChooseDescription()
            .build()
    }

    val setTextBlockValueComponent = ComponentWithParams { param: DefaultComponentParam ->
        editorComponent
            .get(key = param.ctx, param = param)
            .setBlockTextValueComponent()
            .build()
    }

    val createBookmarkSubComponent = Component {
        main
            .createBookmarkBuilder()
            .createBookmarkModule(CreateBookmarkModule())
            .build()
    }


    val linkToObjectComponent = ComponentWithParams { param: ObjectSearchViewModel.VmParams ->
        DaggerLinkToObjectComponent
            .factory()
            .create(
                params = param,
                dependencies = findComponentDependencies()
            )
    }

    val linkToObjectOrWebComponent = ComponentWithParams { param: DefaultComponentParam ->
        editorComponent
            .get(key = param.ctx, param = param)
            .linkToObjectOrWebBuilder()
            .withParams(
                params = LinkToObjectOrWebViewModel.VmParams(
                    space = param.space
                )
            )
            .module(LinkToObjectOrWebModule)
            .build()
    }

    val moveToComponent = ComponentWithParams { params: MoveToViewModel.VmParams ->
        DaggerMoveToComponent.factory()
            .create(params, findComponentDependencies())
    }

    val globalSearchComponent = ComponentWithParams { params: GlobalSearchViewModel.VmParams ->
        DaggerGlobalSearchComponent
            .factory()
            .create(params, findComponentDependencies())
    }

    val searchV2Component = ComponentWithParams { params: SearchViewModel.VmParams ->
        DaggerSearchV2Component
            .factory()
            .create(params, findComponentDependencies())
    }

    val allContentComponent = ComponentWithParams { params: AllContentViewModel.VmParams ->
        DaggerAllContentComponent
            .factory()
            .create(params, findComponentDependencies())
    }

    val participantScreenComponent = ComponentWithParams { params: ParticipantViewModel.VmParams ->
        DaggerParticipantComponent
            .factory()
            .create(params, findComponentDependencies())
    }

    val dateObjectComponent = ComponentWithParams { params: DateObjectVmParams  ->
        DaggerDateObjectComponent
            .factory()
            .create(params, findComponentDependencies())
    }

    val createObjectFeatureComponent = ComponentMapWithParam { params: NewCreateObjectViewModel.VmParams ->
        DaggerCreateObjectFeatureComponent
            .factory()
            .create(params, findComponentDependencies())
    }

    val objectSetComponent = ComponentMapWithParam { param: DefaultComponentParam ->
        main.objectSetComponentBuilder()
            .module(ObjectSetModule)
            .withParams(
                ObjectSetViewModel.Params(
                    ctx = param.ctx,
                    space = param.space
                )
            )
            .build()
    }

    val objectRelationListComponent = ComponentWithParams { param: DefaultComponentParam ->
        editorComponent
            .get(key = param.ctx, param = param)
            .objectRelationListComponent()
            .withVmParams(
                RelationListViewModel.VmParams(
                    objectId = param.ctx,
                    spaceId = param.space
                )
            )
            .module(ObjectRelationListModule)
            .build()
    }

    val objectSetRelationListComponent = ComponentWithParams { param: DefaultComponentParam ->
        objectSetComponent
            .get(key = param.ctx, param = param)
            .objectRelationListComponent()
            .withVmParams(
                RelationListViewModel.VmParams(
                    objectId = param.ctx,
                    spaceId = param.space
                )
            )
            .module(ObjectRelationListModule)
            .build()
    }

    val setOrCollectionRelationTextValueComponent = ComponentWithParams { param: DefaultComponentParam ->
        objectSetComponent
            .get(key = param.ctx, param = param)
            .relationTextValueComponent()
            .module(RelationTextValueModule)
            .build()
    }

    val dataViewRelationTextValueComponent = ComponentWithParams { param: DefaultComponentParam ->
        objectSetComponent
            .get(key = param.ctx, param = param)
            .relationDataViewTextValueComponent()
            .module(RelationDataViewTextValueModule)
            .build()
    }

    val relationTextValueComponent = ComponentWithParams { param: DefaultComponentParam ->
        editorComponent
            .get(key = param.ctx, param = param)
            .relationTextValueComponent()
            .module(RelationTextValueModule)
            .build()
    }

    val dataViewRelationDateValueComponent = ComponentWithParams { param: DefaultComponentParam ->
        objectSetComponent
            .get(key = param.ctx, param = param)
            .dataViewRelationDateValueComponent()
            .module(RelationDataViewDateValueModule)
            .build()
    }

    val setOrCollectionRelationDateValueComponent = ComponentWithParams { param: DefaultComponentParam ->
        objectSetComponent
            .get(key = param.ctx, param = param)
            .relationDateValueComponent()
            .module(RelationDateValueModule)
            .build()
    }

    val objectRelationDateValueComponent = ComponentWithParams { param: DefaultComponentParam ->
        editorComponent
            .get(key = param.ctx, param = param)
            .editRelationDateComponent()
            .module(RelationDateValueModule)
            .build()
    }

    val viewerFilterComponent = ComponentWithParams { param: DefaultComponentParam ->
        objectSetComponent
            .get(key = param.ctx, param = param)
            .viewerFilterBySubComponent()
            .module(ViewerFilterModule)
            .build()
    }

    val objectSetRecordComponent = ComponentWithParams { param: DefaultComponentParam ->
        objectSetComponent
            .get(key = param.ctx, param = param)
            .objectSetRecordComponent()
            .module(ObjectSetRecordModule)
            .build()
    }

    val objectSetCreateBookmarkRecordComponent = ComponentWithParams { param: DefaultComponentParam ->
        objectSetComponent
            .get(key = param.ctx, param = param)
            .objectSetCreateBookmarkRecordComponent()
            .module(ObjectSetCreateBookmarkRecordModule)
            .build()
    }

    val objectsSetSettingsComponent = ComponentWithParams { param: DefaultComponentParam ->
        objectSetComponent
            .get(key = param.ctx, param = param)
            .objectSetSettingsComponent()
            .module(ObjectSetSettingsModule)
            .build()
    }

    val selectSortRelationComponent = ComponentWithParams { param: DefaultComponentParam ->
        objectSetComponent
            .get(key = param.ctx, param = param)
            .selectSortRelationComponent()
            .module(SelectSortRelationModule)
            .build()
    }

    val selectFilterRelationComponent = ComponentWithParams { param: DefaultComponentParam ->
        objectSetComponent
            .get(key = param.ctx, param = param)
            .selectFilterRelationComponent()
            .module(SelectFilterRelationModule)
            .build()
    }

    val createFilterComponent = DependentComponentMap { param: DefaultComponentParam ->
        objectSetComponent
            .get(key = param.ctx, param = param)
            .createFilterComponent()
            .module(CreateFilterModule)
            .build()
    }

    val pickFilterConditionComponentCreate = ComponentWithParams { param: DefaultComponentParam ->
        createFilterComponent
            .get(key = param.ctx, param = param)
            .createPickConditionComponent()
            .module(PickConditionModule)
            .build()
    }

    val pickFilterConditionComponentModify = ComponentWithParams { param: DefaultComponentParam ->
        modifyFilterComponent
            .get(key = param.ctx, param = param)
            .createPickConditionComponent()
            .module(PickConditionModule)
            .build()
    }

    val modifyFilterComponent = DependentComponentMap { param: DefaultComponentParam ->
        objectSetComponent
            .get(key = param.ctx, param = param)
            .modifyFilterComponent()
            .module(ModifyFilterModule)
            .build()
    }

    val viewerSortComponent = ComponentWithParams { param: DefaultComponentParam ->
        objectSetComponent
            .get(key = param.ctx, param = param)
            .viewerSortComponent()
            .module(ViewerSortModule)
            .build()
    }

    val modifyViewerSortComponent = ComponentWithParams { param: DefaultComponentParam ->
        objectSetComponent
            .get(key = param.ctx, param = param)
            .modifyViewerSortComponent()
            .module(ModifyViewerSortModule)
            .build()
    }

    val objectCoverComponent = ComponentWithParams { param: DefaultComponentParam ->
        editorComponent
            .get(key = param.ctx, param = param)
            .objectCoverComponent()
            .module(SelectCoverObjectModule)
            .build()
    }

    val objectUnsplashComponent = ComponentWithParams { param: DefaultComponentParam ->
        editorComponent
            .get(key = param.ctx, param = param)
            .objectUnsplashComponent()
            .module(UnsplashModule)
            .build()
    }

    val objectSetUnsplashComponent = ComponentWithParams { param: DefaultComponentParam ->
        objectSetComponent
            .get(key = param.ctx, param = param)
            .objectUnsplashComponent()
            .module(UnsplashModule)
            .build()
    }

    val objectSetCoverComponent = ComponentWithParams { param: DefaultComponentParam ->
        objectSetComponent
            .get(key = param.ctx, param = param)
            .objectSetCoverComponent()
            .module(SelectCoverObjectSetModule)
            .build()
    }

    val objectMenuComponent = ComponentWithParams { param: DefaultComponentParam ->
        editorComponent
            .get(key = param.ctx, param = param)
            .objectMenuComponent()
            .base(ObjectMenuModuleBase)
            .module(ObjectMenuModule)
            .build()
    }

    val objectSetMenuComponent = ComponentWithParams { param: DefaultComponentParam ->
        objectSetComponent
            .get(key = param.ctx, param = param)
            .objectSetMenuComponent()
            .base(ObjectMenuModuleBase)
            .module(ObjectSetMenuModule)
            .build()
    }

    val relationAddToObjectComponent = ComponentWithParams { param: DefaultComponentParam ->
        editorComponent
            .get(key = param.ctx, param = param)
            .relationAddToObjectComponent()
            .withVmParams(
                vmParams = RelationAddViewModelBase.VmParams(
                    objectId = param.ctx,
                    space = param.space
                )
            )
            .module(RelationAddToObjectModule)
            .build()
    }

    val relationAddToObjectSetComponent = ComponentWithParams { param: DefaultComponentParam ->
        objectSetComponent
            .get(key = param.ctx, param = param)
            .relationAddToObjectComponent()
            .withVmParams(
                vmParams = RelationAddViewModelBase.VmParams(
                    objectId = param.ctx,
                    space = param.space
                )
            )
            .module(RelationAddToObjectModule)
            .build()
    }

    val relationAddToDataViewComponent = ComponentWithParams { param: DefaultComponentParam ->
        objectSetComponent
            .get(key = param.ctx, param = param)
            .relationAddToDataViewComponent()
            .withVmParams(
                vmParams = RelationAddViewModelBase.VmParams(
                    objectId = param.ctx,
                    space = param.space
                )
            )
            .module(RelationAddToDataViewModule)
            .build()
    }

    val relationCreateFromScratchForObjectComponent = DependentComponentMap { param: DefaultComponentParam ->
        editorComponent
            .get(key = param.ctx, param = param)
            .relationCreateFromScratchForObjectComponent()
            .module(RelationCreateFromScratchForObjectModule)
            .build()
    }

    val relationCreateFromScratchForObjectSetComponent = DependentComponentMap { param: DefaultComponentParam ->
        objectSetComponent
            .get(key = param.ctx, param = param)
            .relationCreateFromScratchForObjectComponent()
            .module(RelationCreateFromScratchForObjectModule)
            .build()
    }

    val relationCreateFromScratchForObjectBlockComponent = DependentComponentMap { param: DefaultComponentParam ->
        editorComponent
            .get(key = param.ctx, param = param)
            .relationCreateFromScratchForObjectBlockComponent()
            .module(RelationCreateFromScratchForObjectBlockModule)
            .build()
    }

    val relationCreateFromScratchForDataViewComponent = DependentComponentMap { param: DefaultComponentParam ->
        objectSetComponent
            .get(key = param.ctx, param = param)
            .relationCreateFromScratchForDataViewComponent()
            .module(RelationCreateFromScratchForDataViewModule)
            .build()
    }

    val relationFormatPickerObjectComponent = ComponentWithParams { param: DefaultComponentParam ->
        relationCreateFromScratchForObjectComponent
            .get(key = param.ctx, param = param)
            .relationFormatPickerComponent()
            .build()
    }

    val relationFormatPickerLibraryComponent = ComponentWithParams { ctx: Id ->
        relationCreationFromLibraryComponent
            .get()
            .relationFormatPickerComponent()
            .build()
    }

    val relationFormatPickerBlockComponent = ComponentWithParams { param: DefaultComponentParam ->
        relationCreateFromScratchForObjectBlockComponent
            .get(key = param.ctx, param = param)
            .relationFormatPickerComponent()
            .build()
    }

    val relationFormatPickerDataViewComponent = ComponentWithParams { param: DefaultComponentParam ->
        relationCreateFromScratchForDataViewComponent
            .get(key = param.ctx, param = param)
            .relationFormatPickerComponent()
            .build()
    }

    val relationFormatPickerSetOrCollectionComponent = ComponentWithParams { param: DefaultComponentParam ->
        relationCreateFromScratchForObjectSetComponent
            .get(key = param.ctx, param = param)
            .relationFormatPickerComponent()
            .build()
    }

    val limitObjectTypeObjectComponent = ComponentWithParams { param: DefaultComponentParam ->
        relationCreateFromScratchForObjectComponent
            .get(key = param.ctx, param = param)
            .limitObjectTypeComponent()
            .module(LimitObjectTypeModule)
            .build()
    }

    val limitObjectTypeBlockComponent = ComponentWithParams { param: DefaultComponentParam ->
        relationCreateFromScratchForObjectBlockComponent
            .get(key = param.ctx, param = param)
            .limitObjectTypeComponent()
            .module(LimitObjectTypeModule)
            .build()
    }

    val limitObjectTypeDataViewComponent = ComponentWithParams { param: DefaultComponentParam ->
        relationCreateFromScratchForDataViewComponent
            .get(key = param.ctx, param = param)
            .limitObjectTypeComponent()
            .module(LimitObjectTypeModule)
            .build()
    }

    val limitObjectTypeSetOrCollectionComponent = ComponentWithParams { param: DefaultComponentParam ->
        relationCreateFromScratchForObjectSetComponent
            .get(key = param.ctx, param = param)
            .limitObjectTypeComponent()
            .module(LimitObjectTypeModule)
            .build()
    }

    val limitObjectTypeLibraryComponent = ComponentWithParams { ctx: Id ->
        relationCreationFromLibraryComponent.get()
            .limitObjectTypeComponent()
            .module(LimitObjectTypeModule)
            .build()
    }

    val objectTypeChangeComponent = ComponentWithParams { vmParams: ObjectTypeChangeViewModel.VmParams ->
        main
            .objectTypeChangeComponent()
            .create(vmParams)
    }

    val templateBlankComponent = Component {
        DaggerTemplateBlankComponent.factory().create(findComponentDependencies())
    }

    // Settings

    val aboutAppComponent = Component {
        DaggerAboutAppComponent.factory().create(findComponentDependencies())
    }

    val profileComponent = Component {
        main.profileComponent().module(ProfileModule).build()
    }

    val logoutWarningComponent = Component {
        main.logoutWarningComponent().module(LogoutWarningModule).build()
    }

    val filesStorageComponent = Component {
        DaggerFilesStorageComponent.builder()
            .withDependencies(findComponentDependencies())
            .build()
    }

    val spacesStorageComponent = ComponentWithParams { vmParams: SpacesStorageViewModel.VmParams ->
        DaggerSpacesStorageComponent
            .factory()
            .create(
                vmParams = vmParams,
                dependency = findComponentDependencies()
            )
    }

    val appearanceComponent = Component {
        DaggerAppearanceComponent
            .factory()
            .create(findComponentDependencies())
    }

    val backLinkOrAddToObjectComponent = ComponentWithParams { vmParams: ObjectSearchViewModel.VmParams ->
        DaggerBacklinkOrAddToObjectComponent.builder()
            .withParams(vmParams)
            .withDependencies(findComponentDependencies())
            .build()
    }

    val relationCreationFromLibraryComponent = Component {
        DaggerRelationCreateFromLibraryComponent
            .factory()
            .create(findComponentDependencies())
    }

    val deletedAccountComponent = Component {
        DaggerDeletedAccountComponent
            .factory()
            .create(findComponentDependencies())
    }

    val onboardingComponent = Component {
        DaggerOnboardingComponent
            .factory()
            .create(findComponentDependencies())
    }

    val onboardingStartComponent = Component {
        DaggerOnboardingStartComponent
            .factory()
            .create(findComponentDependencies())
    }

    val onboardingMnemonicComponent = Component {
        DaggerOnboardingMnemonicComponent
            .factory()
            .create(findComponentDependencies())
    }

    val onboardingSoulCreationComponent = Component {
        DaggerOnboardingSoulCreationComponent
            .factory()
            .create(findComponentDependencies())
    }

    val onboardingMnemonicLoginComponent = Component {
        DaggerOnboardingMnemonicLoginComponent
            .factory()
            .create(findComponentDependencies())
    }

    val spaceListComponent = Component {
        DaggerSpaceListComponent
            .factory()
            .create(findComponentDependencies())
    }

    val createSpaceComponent = ComponentWithParams { vmParams: CreateSpaceViewModel.VmParams ->
        DaggerCreateSpaceComponent
            .factory()
            .create(vmParams, findComponentDependencies())
    }

    val spaceSettingsComponent = ComponentWithParams { vmParams: SpaceSettingsViewModel.VmParams ->
        DaggerSpaceSettingsComponent
            .factory()
            .create(
                vmParams = vmParams,
                dependencies = findComponentDependencies()
            )
    }

    val manageSectionsComponent = Component {
        DaggerManageSectionsComponent
            .factory()
            .create(findComponentDependencies())
    }

    val selectObjectTypeComponent = ComponentWithParams { params: SelectObjectTypeViewModel.Params ->
        DaggerSelectObjectTypeComponent
            .factory()
            .create(
                params = params,
                dependencies = findComponentDependencies()
            )
    }

    val appPreferencesComponent = Component {
        DaggerAppPreferencesComponent
            .factory()
            .create(findComponentDependencies())
    }

    val sharingComponent = Component {
        DaggerSharingComponent
            .factory()
            .create(findComponentDependencies())
    }

    val notificationsComponent = Component {
        DaggerNotificationComponent
            .factory()
            .create(findComponentDependencies())
    }

    val tagStatusObjectComponent = ComponentWithParams { params: TagOrStatusValueViewModel.ViewModelParams ->
        editorComponent
            .get(
                key = params.ctx,
                param = DefaultComponentParam(
                    ctx = params.ctx,
                    space = params.space
                )
            )
            .tagStatusObjectComponent()
            .params(params)
            .build()
    }

    val tagStatusSetComponent = ComponentWithParams { params: TagOrStatusValueViewModel.ViewModelParams ->
        objectSetComponent
            .get(
                key = params.ctx,
                param = DefaultComponentParam(
                    ctx = params.ctx,
                    space = params.space
                )
            )
            .tagStatusSetComponent()
            .params(params)
            .build()
    }

    val tagStatusDataViewComponent = ComponentWithParams { params: TagOrStatusValueViewModel.ViewModelParams ->
        objectSetComponent
            .get(
                key = params.ctx,
                param = DefaultComponentParam(
                    ctx = params.ctx,
                    space = params.space
                )
            )
            .tagStatusDataViewComponent()
            .params(params)
            .build()
    }

    val optionObjectComponent = ComponentWithParams { params: CreateOrEditOptionViewModel.ViewModelParams ->
        editorComponent
            .get(
                key = params.ctx,
                param = DefaultComponentParam(
                    ctx = params.ctx,
                    space = params.space
                )
            )
            .optionObjectComponent()
            .params(params)
            .build()
    }

    val optionSetComponent = ComponentWithParams { params: CreateOrEditOptionViewModel.ViewModelParams ->
        objectSetComponent
            .get(
                key = params.ctx,
                param = DefaultComponentParam(
                    ctx = params.ctx,
                    space = params.space
                )
            )
            .optionSetComponent()
            .params(params)
            .build()
    }

    val optionDataViewComponent = ComponentWithParams { params: CreateOrEditOptionViewModel.ViewModelParams ->
        objectSetComponent
            .get(
                key = params.ctx,
                param = DefaultComponentParam(
                    ctx = params.ctx,
                    space = params.space
                )
            )
            .optionDataViewComponent()
            .params(params)
            .build()
    }

    val objectValueObjectComponent = ComponentWithParams { params: ObjectValueViewModel.ViewModelParams ->
        editorComponent
            .get(
                key = params.ctx,
                param = DefaultComponentParam(
                    ctx = params.ctx,
                    space = params.space
                )
            )
            .objectValueComponent()
            .params(params)
            .build()
    }

    val objectValueSetComponent = ComponentWithParams { params: ObjectValueViewModel.ViewModelParams ->
        objectSetComponent
            .get(
                key = params.ctx,
                param = DefaultComponentParam(
                    ctx = params.ctx,
                    space = params.space
                )
            )
            .objectValueSetComponent()
            .params(params)
            .build()
    }

    val objectValueDataViewComponent = ComponentWithParams { params: ObjectValueViewModel.ViewModelParams ->
        objectSetComponent
            .get(
                key = params.ctx,
                param = DefaultComponentParam(
                    ctx = params.ctx,
                    space = params.space
                )
            )
            .objectValueDataViewComponent()
            .params(params)
            .build()
    }

    val shareSpaceComponent = ComponentWithParams { space: SpaceId ->
        DaggerShareSpaceComponent
            .builder()
            .withDependencies(findComponentDependencies())
            .withParams(params = ShareSpaceViewModel.VmParams(space))
            .build()
    }

    val spaceJoinRequestComponent = ComponentMapWithParam { params: SpaceJoinRequestViewModel.VmParams ->
        DaggerSpaceJoinRequestComponent
            .builder()
            .withDependencies(findComponentDependencies())
            .withParams(params = params)
            .build()
    }

    val requestToJoinSpaceComponent = ComponentWithParams { params: RequestJoinSpaceViewModel.Params ->
        DaggerRequestJoinSpaceComponent
            .builder()
            .withDependencies(findComponentDependencies())
            .withParams(params = params)
            .build()
    }

    val membershipComponent = Component {
        DaggerMembershipComponent.factory().create(findComponentDependencies())
    }

    val galleryInstallationsComponent =
        ComponentWithParams { params: GalleryInstallationViewModel.ViewModelParams ->
            DaggerGalleryInstallationComponent.builder()
                .withDependencies(findComponentDependencies())
                .withParams(params)
                .build()
        }

    val versionHistoryComponent =
        ComponentWithParams { vmParams: VersionHistoryViewModel.VmParams ->
            editorComponent.get(
                key = vmParams.objectId,
                param = DefaultComponentParam(
                    ctx = vmParams.objectId,
                    space = vmParams.spaceId
                )
            ).versionHistoryComponent()
                .vmParams(vmParams)
                .build()
        }

    val chatComponent = ComponentMapWithParam { params: ChatViewModel.Params.Default ->
        DaggerChatComponent
            .builder()
            .withDependencies(findComponentDependencies())
            .withParams(params)
            .build()
    }

    val discussionComponent = ComponentMapWithParam { params: DiscussionViewModel.Params ->
        DaggerDiscussionComponent
            .builder()
            .withDependencies(findComponentDependencies())
            .withParams(params)
            .build()
    }

    val selectChatReactionComponent = ComponentMapWithParam { params: SelectChatReactionViewModel.Params ->
        DaggerSelectChatReactionComponent
            .builder()
            .withDependencies(findComponentDependencies())
            .withParams(params)
            .build()
    }

    val chatReactionComponent = ComponentMapWithParam { params: ChatReactionViewModel.Params ->
        DaggerChatReactionComponent
            .builder()
            .withDependencies(findComponentDependencies())
            .withParams(params)
            .build()
    }

    val selectChatIconComponent = Component {
        DaggerSelectChatIconComponent
            .builder()
            .withDependencies(findComponentDependencies())
            .build()
    }

    val vaultComponent = Component {
        DaggerVaultComponent
            .factory()
            .create(findComponentDependencies())
    }

    val objectTypeComponent = ComponentWithParams { params: ObjectTypeVmParams ->
        DaggerObjectTypeComponent
            .factory()
            .create(params, findComponentDependencies())
    }

    val editTypePropertiesComponent = ComponentWithParams { params: EditTypePropertiesVmParams ->
        DaggerEditTypePropertiesComponent
            .factory()
            .create(params, findComponentDependencies())
    }

    val createObjectTypeComponent = ComponentWithParams { params: CreateTypeVmParams ->
        DaggerCreateObjectTypeComponent
            .factory()
            .create(params, findComponentDependencies())
    }

    val spaceTypesComponent = ComponentWithParams { params: SpaceTypesViewModel.VmParams ->
        DaggerSpaceTypesComponent
            .factory()
            .create(params, findComponentDependencies())
    }

    val spacePropertiesComponent = ComponentWithParams { params: SpacePropertiesViewModel.VmParams ->
        DaggerSpacePropertiesComponent
            .factory()
            .create(params, findComponentDependencies())
    }

    val pushContentComponent = Component {
        DaggerPushContentComponent
            .factory()
            .create(findComponentDependencies())
    }

    val publishToWebComponent = ComponentWithParams { params: PublishToWebViewModel.Params ->
        DaggerPublishToWebComponent
            .factory()
            .create(params, findComponentDependencies())
    }

    val mySitesComponent = ComponentWithParams { params: MySitesViewModel.VmParams ->
        DaggerMySitesComponent
            .factory()
            .create(params, findComponentDependencies())
    }

    val mediaComponent = Component {
        DaggerMediaComponent
            .factory()
            .create(findComponentDependencies())
    }

    val createChatObjectComponent = ComponentWithParams { vmParams: CreateChatObjectViewModel.VmParams ->
        DaggerCreateChatObjectComponent
            .factory()
            .create(vmParams, findComponentDependencies())
    }

    val createObjectWidgetConfigComponent = Component {
        DaggerCreateObjectWidgetConfigComponent
            .factory()
            .create(findComponentDependencies())
    }

    val objectShortcutWidgetConfigComponent = Component {
        DaggerObjectShortcutWidgetConfigComponent
            .factory()
            .create(findComponentDependencies())
    }

    val dataViewWidgetConfigComponent = Component {
        DaggerDataViewWidgetConfigComponent
            .factory()
            .create(findComponentDependencies())
    }

    class Component<T>(private val builder: () -> T) {

        private var instance: T? = null

        fun get() = instance ?: builder().also { instance = it }

        fun new() = builder().also { instance = it }

        fun release() {
            instance = null
        }

        fun isInitialized() = instance != null

        override fun toString(): String {
            return if (BuildConfig.DEBUG) {
                instance?.toString().orEmpty()
            } else {
                super.toString()
            }
        }
    }

    class ComponentMap<T>(private val builder: () -> T) {

        private val map = mutableMapOf<String, T>()

        fun get(id: String) = map[id] ?: builder().also { map[id] = it }

        fun new(id: String) = builder().also { map[id] = it }

        fun release(id: String) {
            map.remove(id)
        }

        fun isInitialized() = map.isNotEmpty()

        override fun toString(): String {
            return if (BuildConfig.DEBUG) {
                map.toString()
            } else {
                super.toString()
            }
        }
    }

    class ComponentMapWithParam<out T, in PARAMETER>(private val builder: (PARAMETER) -> T) {

        private val map = mutableMapOf<String, T>()

        fun get(key: String, param: PARAMETER) = map[key] ?: builder(param).also { map[key] = it }

        fun new(id: String, param: PARAMETER) = builder(param).also { map[id] = it }

        fun release(id: String) {
            map.remove(id)
        }

        fun isInitialized() = map.isNotEmpty()

        override fun toString(): String {
            return if (BuildConfig.DEBUG) {
                map.toString()
            } else {
                super.toString()
            }
        }
    }

    class DependentComponentMap<out T, in PARAMETER>(private val builder: (PARAMETER) -> T) {

        private val map = mutableMapOf<String, T>()

        fun get(key: String, param: PARAMETER) = map[key] ?: builder(param).also { map[key] = it }

        fun new(key: String, param: PARAMETER) = builder(param).also { map[key] = it }

        fun release(id: Id) {
            map.remove(id)
        }

        fun isInitialized() = map.isNotEmpty()

        override fun toString(): String {
            return if (BuildConfig.DEBUG) {
                map.toString()
            } else {
                super.toString()
            }
        }
    }

    class ComponentWithParams<out T, in PARAMETER>(private val builder: (PARAMETER) -> T) {

        private var instance: T? = null

        fun get(params: PARAMETER) = instance ?: builder(params).also { instance = it }

        fun new(params: PARAMETER) = builder(params).also { instance = it }

        fun release() {
            instance = null
        }
    }

    private inline fun <reified T : ComponentDependencies> findComponentDependencies(): T {
        return provider.dependencies[T::class.java] as T
    }

    /**
     * Can be used for debugging and tracing unreleased components.
     */
    fun logUnreleasedComponents() {
        if (BuildConfig.DEBUG) {
            javaClass.declaredFields.forEach { field ->
                val component = field.get(this)
                if (component != null) {
                    when (component) {
                        is Component<*> -> {
                            if (component.isInitialized()) {
                                Timber.d("Unreleased component: $component")
                            }
                        }
                        is DependentComponentMap<*, *> -> {
                            if (component.isInitialized()) {
                                Timber.d("Unreleased component: $component")
                            }
                        }
                        is ComponentMap<*> -> {
                            if (component.isInitialized()) {
                                Timber.d("Unreleased component: $component")
                            }
                        }
                    }
                }
            }
        }
    }
}