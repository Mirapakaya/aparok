package com.aparokio.aparok.di.main

import com.aparokio.aparok.app.AndroidApplication
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.di.common.ComponentDependenciesKey
import com.aparokio.aparok.di.feature.AllContentDependencies
import com.aparokio.aparok.di.feature.AppPreferencesDependencies
import com.aparokio.aparok.di.feature.BacklinkOrAddToObjectDependencies
import com.aparokio.aparok.di.feature.CreateBookmarkSubComponent
import com.aparokio.aparok.di.feature.CreateObjectFeatureDependencies
import com.aparokio.aparok.di.feature.CreateObjectSubComponent
import com.aparokio.aparok.di.feature.CreateObjectTypeDependencies
import com.aparokio.aparok.di.feature.DateObjectDependencies
import com.aparokio.aparok.di.feature.DebugSettingsSubComponent
import com.aparokio.aparok.di.feature.EditTypePropertiesDependencies
import com.aparokio.aparok.di.feature.EditorSubComponent
import com.aparokio.aparok.di.feature.KeychainPhraseSubComponent
import com.aparokio.aparok.di.feature.LinkToObjectDependencies
import com.aparokio.aparok.di.feature.MainEntrySubComponent
import com.aparokio.aparok.di.feature.MediaDependencies
import com.aparokio.aparok.di.feature.MoveToDependencies
import com.aparokio.aparok.di.feature.MySitesDependencies
import com.aparokio.aparok.di.feature.ObjectSetSubComponent
import com.aparokio.aparok.di.feature.ObjectTypeChangeSubComponent
import com.aparokio.aparok.di.feature.ObjectTypeDependencies
import com.aparokio.aparok.di.feature.PublishToWebDependencies
import com.aparokio.aparok.di.feature.SpacePropertiesDependencies
import com.aparokio.aparok.di.feature.SpaceTypesDependencies
import com.aparokio.aparok.di.feature.SplashDependencies
import com.aparokio.aparok.di.feature.auth.DeletedAccountDependencies
import com.aparokio.aparok.di.feature.chats.ChatComponentDependencies
import com.aparokio.aparok.di.feature.discussions.DiscussionComponentDependencies
import com.aparokio.aparok.di.feature.chats.ChatReactionDependencies
import com.aparokio.aparok.di.feature.chats.SelectChatIconDependencies
import com.aparokio.aparok.di.feature.chats.SelectChatReactionDependencies
import com.aparokio.aparok.di.feature.gallery.GalleryInstallationComponentDependencies
import com.aparokio.aparok.di.feature.home.HomeScreenDependencies
import com.aparokio.aparok.di.feature.membership.MembershipComponentDependencies
import com.aparokio.aparok.di.feature.multiplayer.RequestJoinSpaceDependencies
import com.aparokio.aparok.di.feature.multiplayer.ShareSpaceDependencies
import com.aparokio.aparok.di.feature.multiplayer.SpaceJoinRequestDependencies
import com.aparokio.aparok.di.feature.notifications.NotificationDependencies
import com.aparokio.aparok.di.feature.notifications.PushContentDependencies
import com.aparokio.aparok.di.feature.objects.SelectObjectTypeDependencies
import com.aparokio.aparok.di.feature.onboarding.OnboardingDependencies
import com.aparokio.aparok.di.feature.oswidgets.CreateObjectWidgetConfigDependencies
import com.aparokio.aparok.di.feature.oswidgets.DataViewWidgetConfigDependencies
import com.aparokio.aparok.di.feature.oswidgets.ObjectShortcutWidgetConfigDependencies
import com.aparokio.aparok.di.feature.onboarding.OnboardingStartDependencies
import com.aparokio.aparok.di.feature.onboarding.login.OnboardingMnemonicLoginDependencies
import com.aparokio.aparok.di.feature.onboarding.signup.OnboardingMnemonicDependencies
import com.aparokio.aparok.di.feature.onboarding.signup.OnboardingSoulCreationDependencies
import com.aparokio.aparok.di.feature.participant.ParticipantComponentDependencies
import com.aparokio.aparok.di.feature.relations.RelationCreateFromLibraryDependencies
import com.aparokio.aparok.di.feature.search.GlobalSearchDependencies
import com.aparokio.aparok.di.feature.search.SearchV2Dependencies
import com.aparokio.aparok.di.feature.settings.AboutAppDependencies
import com.aparokio.aparok.di.feature.settings.AppearanceDependencies
import com.aparokio.aparok.di.feature.settings.DebugDependencies
import com.aparokio.aparok.di.feature.quickcapture.QuickCaptureDependencies
import com.aparokio.aparok.di.feature.settings.ExperimentalFeaturesDependencies
import com.aparokio.aparok.di.feature.settings.FilesStorageDependencies
import com.aparokio.aparok.di.feature.settings.LogoutWarningSubComponent
import com.aparokio.aparok.di.feature.settings.ProfileSubComponent
import com.aparokio.aparok.di.feature.settings.SpacesStorageDependencies
import com.aparokio.aparok.di.feature.sharing.SharingDependencies
import com.aparokio.aparok.di.feature.spaces.CreateSpaceDependencies
import com.aparokio.aparok.di.feature.spaces.ManageSectionsDependencies
import com.aparokio.aparok.di.feature.spaces.SpaceListDependencies
import com.aparokio.aparok.di.feature.spaces.SpaceSettingsDependencies
import com.aparokio.aparok.di.feature.templates.TemplateBlankDependencies
import com.aparokio.aparok.di.feature.templates.TemplateSelectDependencies
import com.aparokio.aparok.di.feature.vault.VaultComponentDependencies
import com.aparokio.aparok.di.feature.widgets.CreateChatObjectDependencies
import com.aparokio.aparok.di.feature.widgets.SelectWidgetSourceDependencies
import com.aparokio.aparok.di.feature.widgets.SelectWidgetTypeDependencies
import com.aparokio.aparok.domain.chats.ChatPreviewContainer
import com.aparokio.aparok.domain.chats.ChatsDetailsSubscriptionContainer
import com.aparokio.aparok.domain.objects.CrossSpaceObjectTypesContainer
import com.aparokio.aparok.ui.widgets.collection.CollectionDependencies
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ComponentDependenciesModule::class,
        ContextModule::class,
        AiModule::class,
        DataModule::class,
        EventModule::class,
        ConfigModule::class,
        WorkspaceModule::class,
        DeviceModule::class,
        UtilModule::class,
        EmojiModule::class,
        ClipboardModule::class,
        AnalyticsModule::class,
        LocalNetworkProviderModule::class,
        SubscriptionsModule::class,
        CrashReportingModule::class,
        TemplatesModule::class,
        NetworkModeModule::class,
        NotificationsModule::class,
        MembershipModule::class,
        DispatcherModule::class,
        NavigationModule::class
    ]
)
interface MainComponent :
    AppearanceDependencies,
    HomeScreenDependencies,
    CollectionDependencies,
    RelationCreateFromLibraryDependencies,
    SplashDependencies,
    DeletedAccountDependencies,
    BacklinkOrAddToObjectDependencies,
    FilesStorageDependencies,
    OnboardingDependencies,
    OnboardingStartDependencies,
    OnboardingMnemonicDependencies,
    OnboardingMnemonicLoginDependencies,
    OnboardingSoulCreationDependencies,
    AboutAppDependencies,
    TemplateBlankDependencies,
    TemplateSelectDependencies,
    CreateSpaceDependencies,
    SpaceListDependencies,
    SpaceSettingsDependencies,
    ManageSectionsDependencies,
    SelectObjectTypeDependencies,
    SpacesStorageDependencies,
    AppPreferencesDependencies,
    ShareSpaceDependencies,
    SpaceJoinRequestDependencies,
    RequestJoinSpaceDependencies,
    MembershipComponentDependencies,
    GalleryInstallationComponentDependencies,
    NotificationDependencies,
    GlobalSearchDependencies,
    SearchV2Dependencies,
    VaultComponentDependencies,
    AllContentDependencies,
    ChatComponentDependencies,
    DiscussionComponentDependencies,
    SelectWidgetSourceDependencies,
    SelectWidgetTypeDependencies,
    LinkToObjectDependencies,
    MoveToDependencies,
    DateObjectDependencies,
    ObjectTypeDependencies,
    SelectChatReactionDependencies,
    SelectChatIconDependencies,
    ChatReactionDependencies,
    ParticipantComponentDependencies,
    EditTypePropertiesDependencies,
    DebugDependencies,
    ExperimentalFeaturesDependencies,
    QuickCaptureDependencies,
    CreateObjectTypeDependencies,
    SpaceTypesDependencies,
    SpacePropertiesDependencies,
    PushContentDependencies,
    PublishToWebDependencies,
    MySitesDependencies,
    MediaDependencies,
    CreateChatObjectDependencies,
    SharingDependencies,
    CreateObjectFeatureDependencies,
    CreateObjectWidgetConfigDependencies,
    DataViewWidgetConfigDependencies,
    ObjectShortcutWidgetConfigDependencies
{

    fun inject(app: AndroidApplication)

    fun editorComponentBuilder(): EditorSubComponent.Builder
    fun createBookmarkBuilder(): CreateBookmarkSubComponent.Builder
    fun mainEntryComponentBuilder(): MainEntrySubComponent.Builder
    fun objectSetComponentBuilder(): ObjectSetSubComponent.Builder
    fun objectTypeChangeComponent(): ObjectTypeChangeSubComponent.Factory
    fun createObjectComponent(): CreateObjectSubComponent.Builder

    //region Settings

    fun profileComponent(): ProfileSubComponent.Builder
    fun debugSettingsBuilder(): DebugSettingsSubComponent.Builder
    fun keychainPhraseComponentBuilder(): KeychainPhraseSubComponent.Builder
    fun logoutWarningComponent(): LogoutWarningSubComponent.Builder

    //endregion

    fun chatPreviewContainer(): ChatPreviewContainer
    fun chatsDetailsSubscriptionContainer(): ChatsDetailsSubscriptionContainer
    fun crossSpaceObjectTypesContainer(): CrossSpaceObjectTypesContainer
}

@Module
abstract class ComponentDependenciesModule {

    @Binds
    @IntoMap
    @ComponentDependenciesKey(AppearanceDependencies::class)
    abstract fun provideAppearanceDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(HomeScreenDependencies::class)
    abstract fun provideHomeScreenDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(CollectionDependencies::class)
    abstract fun provideHomeWidgetDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(RelationCreateFromLibraryDependencies::class)
    abstract fun provideRelationCreateFromLibraryDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(SplashDependencies::class)
    abstract fun provideSplashDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(DeletedAccountDependencies::class)
    abstract fun provideDeletedAccountDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(BacklinkOrAddToObjectDependencies::class)
    abstract fun provideBackLinkDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(FilesStorageDependencies::class)
    abstract fun provideFilesStorageDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(OnboardingDependencies::class)
    abstract fun provideOnboardingDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(OnboardingStartDependencies::class)
    abstract fun provideOnboardingStartDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(OnboardingMnemonicDependencies::class)
    abstract fun provideOnboardingMnemonicDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(OnboardingMnemonicLoginDependencies::class)
    abstract fun provideOnboardingMnemonicLoginDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(OnboardingSoulCreationDependencies::class)
    abstract fun provideOnboardingSoulCreationDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(AboutAppDependencies::class)
    abstract fun provideAboutAppDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(TemplateBlankDependencies::class)
    abstract fun provideTemplateBlankDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(TemplateSelectDependencies::class)
    abstract fun provideTemplateSelectDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(SpaceListDependencies::class)
    abstract fun provideSpaceListDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(CreateSpaceDependencies::class)
    abstract fun provideCreateSpaceDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(SpaceSettingsDependencies::class)
    abstract fun provideSpaceSettingsDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(ManageSectionsDependencies::class)
    abstract fun provideManageSectionsDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(SelectObjectTypeDependencies::class)
    abstract fun provideCreateObjectOfTypeDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(SpacesStorageDependencies::class)
    abstract fun provideSpacesStorageDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(AppPreferencesDependencies::class)
    abstract fun providePreferencesDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(ShareSpaceDependencies::class)
    abstract fun provideShareSpaceDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(SpaceJoinRequestDependencies::class)
    abstract fun provideSpaceJoinRequestDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(MembershipComponentDependencies::class)
    abstract fun provideMembershipComponentDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(RequestJoinSpaceDependencies::class)
    abstract fun provideRequestToJoinSpaceDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(GalleryInstallationComponentDependencies::class)
    abstract fun provideGalleryInstallationDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(NotificationDependencies::class)
    abstract fun provideNotificationDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(GlobalSearchDependencies::class)
    abstract fun provideGlobalSearchDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(SearchV2Dependencies::class)
    abstract fun provideSearchV2Dependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(ChatComponentDependencies::class)
    abstract fun provideChatComponentDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(DiscussionComponentDependencies::class)
    abstract fun provideDiscussionComponentDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(VaultComponentDependencies::class)
    abstract fun provideVaultComponentDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(AllContentDependencies::class)
    abstract fun provideAllContentDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(SelectWidgetSourceDependencies::class)
    abstract fun provideSelectWidgetSourceDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(SelectWidgetTypeDependencies::class)
    abstract fun provideSelectWidgetTypeDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(LinkToObjectDependencies::class)
    abstract fun provideLinkToObjectDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(MoveToDependencies::class)
    abstract fun provideMoveToDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(DateObjectDependencies::class)
    abstract fun provideDateObjectDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(ObjectTypeDependencies::class)
    abstract fun provideObjectTypeDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(SelectChatReactionDependencies::class)
    abstract fun provideChatReactionPickerDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(SelectChatIconDependencies::class)
    abstract fun provideSelectChatIconDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(ChatReactionDependencies::class)
    abstract fun provideChatReactionDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(ParticipantComponentDependencies::class)
    abstract fun provideParticipantComponentDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(DebugDependencies::class)
    abstract fun provideDebugDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(ExperimentalFeaturesDependencies::class)
    abstract fun provideExperimentalFeaturesDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(QuickCaptureDependencies::class)
    abstract fun provideQuickCaptureDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(EditTypePropertiesDependencies::class)
    abstract fun provideEditTypePropertiesDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(CreateObjectTypeDependencies::class)
    abstract fun provideCreateObjectTypeDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(SpaceTypesDependencies::class)
    abstract fun provideSpaceTypesDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(SpacePropertiesDependencies::class)
    abstract fun provideSpacePropertiesDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(PushContentDependencies::class)
    abstract fun providePushContentDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(PublishToWebDependencies::class)
    abstract fun providePublishToWebDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(MySitesDependencies::class)
    abstract fun mySitesDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(MediaDependencies::class)
    abstract fun mediaDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(CreateChatObjectDependencies::class)
    abstract fun createChatObjectDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(SharingDependencies::class)
    abstract fun sharingDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(CreateObjectFeatureDependencies::class)
    abstract fun provideCreateObjectFeatureDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(CreateObjectWidgetConfigDependencies::class)
    abstract fun provideCreateObjectWidgetConfigDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(ObjectShortcutWidgetConfigDependencies::class)
    abstract fun provideObjectShortcutWidgetConfigDependencies(component: MainComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(DataViewWidgetConfigDependencies::class)
    abstract fun provideDataViewWidgetConfigDependencies(component: MainComponent): ComponentDependencies
}
