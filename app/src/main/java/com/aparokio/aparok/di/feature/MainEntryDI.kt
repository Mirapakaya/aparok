package com.aparokio.aparok.di.feature

import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.main.ConfigModule.DEFAULT_APP_COROUTINE_SCOPE
import com.aparokio.aparok.domain.account.AccountStatusChannel
import com.aparokio.aparok.domain.account.AwaitAccountStartManager
import com.aparokio.aparok.domain.account.InterceptAccountStatus
import com.aparokio.aparok.domain.auth.interactor.AppShutdown
import com.aparokio.aparok.domain.auth.interactor.CheckAuthorizationStatus
import com.aparokio.aparok.domain.auth.interactor.HasAccount
import com.aparokio.aparok.domain.auth.interactor.Logout
import com.aparokio.aparok.domain.auth.interactor.ResumeAccount
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.chats.ChatPreviewContainer
import com.aparokio.aparok.domain.chats.ChatsDetailsSubscriptionContainer
import com.aparokio.aparok.domain.launch.RemainingSpacesPreloader
import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.domain.config.ObserveShowSpacesIntroduction
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.debugging.DebugRunProfiler
import com.aparokio.aparok.domain.deeplink.PendingIntentStore
import com.aparokio.aparok.domain.launch.PreferredSpaceIdHolder
import com.aparokio.aparok.domain.device.PathProvider
import com.aparokio.aparok.domain.misc.LocaleProvider
import com.aparokio.aparok.domain.multiplayer.ParticipantSubscriptionContainer
import com.aparokio.aparok.domain.objects.CrossSpaceObjectTypesContainer
import com.aparokio.aparok.domain.multiplayer.SpaceInviteResolver
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.notifications.SystemNotificationService
import com.aparokio.aparok.domain.platform.InitialParamsProvider
import com.aparokio.aparok.domain.spaces.ResolveSpaceHomepage
import com.aparokio.aparok.domain.subscriptions.GlobalSubscriptionManager
import com.aparokio.aparok.domain.theme.GetTheme
import com.aparokio.aparok.domain.vault.SetSpacesIntroductionShown
import com.aparokio.aparok.domain.wallpaper.ObserveSpaceWallpaper
import com.aparokio.aparok.domain.workspace.DeepLinkToObjectDelegate
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.other.DefaultSpaceInviteResolver
import com.aparokio.aparok.presentation.main.MainViewModelFactory
import com.aparokio.aparok.presentation.membership.provider.MembershipProvider
import com.aparokio.aparok.presentation.notifications.NotificationActionDelegate
import com.aparokio.aparok.presentation.notifications.NotificationsProvider
import com.aparokio.aparok.ui.main.MainActivity
import com.aparokio.aparok.ui_settings.appearance.ThemeApplicator
import com.aparokio.aparok.ui_settings.appearance.ThemeApplicatorImpl
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import javax.inject.Named
import kotlinx.coroutines.CoroutineScope

@Subcomponent(
    modules = [MainEntryModule::class]
)
@PerScreen
interface MainEntrySubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): MainEntrySubComponent
        fun module(module: MainEntryModule): Builder
    }

    fun inject(activity: MainActivity)
}

@Module
object MainEntryModule {

    @Provides
    fun provideUnqualifiedScope(
        @Named(DEFAULT_APP_COROUTINE_SCOPE) scope: CoroutineScope
    ): CoroutineScope = scope

    @JvmStatic
    @PerScreen
    @Provides
    fun provideMainViewModelFactory(
        resumeAccount: ResumeAccount,
        analytics: Analytics,
        interceptAccountStatus: InterceptAccountStatus,
        logout: Logout,
        checkAuthorizationStatus: CheckAuthorizationStatus,
        hasAccount: HasAccount,
        configStorage: ConfigStorage,
        localeProvider: LocaleProvider,
        notificationsProvider: NotificationsProvider,
        notificator: SystemNotificationService,
        notificationActionDelegate: NotificationActionDelegate,
        deepLinkToObjectDelegate: DeepLinkToObjectDelegate,
        awaitAccountStartManager: AwaitAccountStartManager,
        membershipProvider: MembershipProvider,
        globalSubscriptionManager: GlobalSubscriptionManager,
        spaceInviteResolver: SpaceInviteResolver,
        spaceManager: SpaceManager,
        spaceViewSubscriptionContainer: SpaceViewSubscriptionContainer,
        pendingIntentStore: PendingIntentStore,
        observeSpaceWallpaper: ObserveSpaceWallpaper,
        urlBuilder: UrlBuilder,
        appShutdown: AppShutdown,
        scope: CoroutineScope,
        observeShowSpacesIntroduction: ObserveShowSpacesIntroduction,
        setSpacesIntroductionShown: SetSpacesIntroductionShown,
        appInfo: com.aparokio.aparok.core_utils.tools.AppInfo,
        chatPreviewContainer: ChatPreviewContainer,
        chatsDetailsSubscriptionContainer: ChatsDetailsSubscriptionContainer,
        participantSubscriptionContainer: ParticipantSubscriptionContainer,
        crossSpaceObjectTypesContainer: CrossSpaceObjectTypesContainer,
        userSettingsRepository: UserSettingsRepository,
        resolveSpaceHomepage: ResolveSpaceHomepage,
        debugRunProfiler: DebugRunProfiler,
        preferredSpaceIdHolder: PreferredSpaceIdHolder,
        remainingSpacesPreloader: RemainingSpacesPreloader
    ): MainViewModelFactory = MainViewModelFactory(
        resumeAccount = resumeAccount,
        analytics = analytics,
        interceptAccountStatus = interceptAccountStatus,
        logout = logout,
        checkAuthorizationStatus = checkAuthorizationStatus,
        hasAccount = hasAccount,
        configStorage = configStorage,
        localeProvider = localeProvider,
        notificationsProvider = notificationsProvider,
        notificator = notificator,
        notificationActionDelegate = notificationActionDelegate,
        deepLinkToObjectDelegate = deepLinkToObjectDelegate,
        awaitAccountStartManager = awaitAccountStartManager,
        membershipProvider = membershipProvider,
        globalSubscriptionManager = globalSubscriptionManager,
        spaceInviteResolver = spaceInviteResolver,
        spaceManager = spaceManager,
        spaceViews = spaceViewSubscriptionContainer,
        pendingIntentStore = pendingIntentStore,
        observeSpaceWallpaper = observeSpaceWallpaper,
        urlBuilder = urlBuilder,
        appShutdown = appShutdown,
        scope = scope,
        observeShowSpacesIntroduction = observeShowSpacesIntroduction,
        setSpacesIntroductionShown = setSpacesIntroductionShown,
        appInfo = appInfo,
        chatPreviewContainer = chatPreviewContainer,
        chatsDetailsSubscriptionContainer = chatsDetailsSubscriptionContainer,
        participantSubscriptionContainer = participantSubscriptionContainer,
        crossSpaceObjectTypesContainer = crossSpaceObjectTypesContainer,
        userSettingsRepository = userSettingsRepository,
        resolveSpaceHomepage = resolveSpaceHomepage,
        debugRunProfiler = debugRunProfiler,
        preferredSpaceIdHolder = preferredSpaceIdHolder,
        remainingSpacesPreloader = remainingSpacesPreloader
    )

    @JvmStatic
    @PerScreen
    @Provides
    fun provideResumeAccountUseCase(
        authRepository: AuthRepository,
        pathProvider: PathProvider,
        configStorage: ConfigStorage,
        initialParamsProvider: InitialParamsProvider,
        awaitAccountStartManager: AwaitAccountStartManager,
        spaceManager: SpaceManager,
        settingsRepository: UserSettingsRepository
    ): ResumeAccount = ResumeAccount(
        repository = authRepository,
        pathProvider = pathProvider,
        configStorage = configStorage,
        initialParamsProvider = initialParamsProvider,
        awaitAccountStartManager = awaitAccountStartManager,
        spaceManager = spaceManager,
        settings = settingsRepository
    )

    @JvmStatic
    @PerScreen
    @Provides
    fun provideSetupThemeUseCase(
        repo: UserSettingsRepository
    ): GetTheme = GetTheme(repo)

    @JvmStatic
    @PerScreen
    @Provides
    fun provideThemeApplicator(): ThemeApplicator = ThemeApplicatorImpl()

    @JvmStatic
    @PerScreen
    @Provides
    fun provideInterceptAccountStatus(
        channel: AccountStatusChannel,
        dispatchers: AppCoroutineDispatchers
    ): InterceptAccountStatus = InterceptAccountStatus(
        channel = channel,
        dispatchers = dispatchers
    )

    @JvmStatic
    @PerScreen
    @Provides
    fun provideLogoutUseCase(
        repo: AuthRepository,
        provider: ConfigStorage,
        user: UserSettingsRepository,
        dispatchers: AppCoroutineDispatchers,
        spaceManager: SpaceManager,
        awaitAccountStartManager: AwaitAccountStartManager,
        remainingSpacesPreloader: RemainingSpacesPreloader,
        preferredSpaceIdHolder: PreferredSpaceIdHolder
    ): Logout = Logout(
        repo = repo,
        user = user,
        config = provider,
        dispatchers = dispatchers,
        spaceManager = spaceManager,
        awaitAccountStartManager = awaitAccountStartManager,
        remainingSpacesPreloader = remainingSpacesPreloader,
        preferredSpaceIdHolder = preferredSpaceIdHolder
    )

    @JvmStatic
    @PerScreen
    @Provides
    fun provideCheckAuthStatus(
        repo: AuthRepository,
        dispatchers: AppCoroutineDispatchers
    ): CheckAuthorizationStatus = CheckAuthorizationStatus(repo, dispatchers)

    @JvmStatic
    @PerScreen
    @Provides
    fun provideHasAccount(
        repo: AuthRepository,
        dispatchers: AppCoroutineDispatchers
    ): HasAccount = HasAccount(repo, dispatchers)

    @JvmStatic
    @PerScreen
    @Provides
    fun provideNotificationActionDelegate(
        default: NotificationActionDelegate.Default
    ) : NotificationActionDelegate = default

    @JvmStatic
    @PerScreen
    @Provides
    fun provideDeepLinkToObjectDelegate(
        default: DeepLinkToObjectDelegate.Default
    ): DeepLinkToObjectDelegate = default

    @JvmStatic
    @PerScreen
    @Provides
    fun provideSpaceInviteResolver(): SpaceInviteResolver = DefaultSpaceInviteResolver

    @JvmStatic
    @PerScreen
    @Provides
    fun provideDebugRunProfiler(
        repo: AuthRepository,
        dispatchers: AppCoroutineDispatchers
    ): DebugRunProfiler = DebugRunProfiler(repo, dispatchers)
}