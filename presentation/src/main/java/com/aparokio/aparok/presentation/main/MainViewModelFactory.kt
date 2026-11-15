package com.aparokio.aparok.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.core_utils.tools.AppInfo
import com.aparokio.aparok.domain.account.AwaitAccountStartManager
import com.aparokio.aparok.domain.account.InterceptAccountStatus
import com.aparokio.aparok.domain.auth.interactor.AppShutdown
import com.aparokio.aparok.domain.auth.interactor.CheckAuthorizationStatus
import com.aparokio.aparok.domain.auth.interactor.HasAccount
import com.aparokio.aparok.domain.auth.interactor.Logout
import com.aparokio.aparok.domain.auth.interactor.ResumeAccount
import com.aparokio.aparok.domain.chats.ChatPreviewContainer
import com.aparokio.aparok.domain.chats.ChatsDetailsSubscriptionContainer
import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.domain.config.ObserveShowSpacesIntroduction
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.debugging.DebugRunProfiler
import com.aparokio.aparok.domain.deeplink.PendingIntentStore
import com.aparokio.aparok.domain.launch.PreferredSpaceIdHolder
import com.aparokio.aparok.domain.launch.RemainingSpacesPreloader
import com.aparokio.aparok.domain.misc.LocaleProvider
import com.aparokio.aparok.domain.multiplayer.ParticipantSubscriptionContainer
import com.aparokio.aparok.domain.objects.CrossSpaceObjectTypesContainer
import com.aparokio.aparok.domain.multiplayer.SpaceInviteResolver
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.notifications.SystemNotificationService
import com.aparokio.aparok.domain.spaces.ResolveSpaceHomepage
import com.aparokio.aparok.domain.subscriptions.GlobalSubscriptionManager
import com.aparokio.aparok.domain.vault.SetSpacesIntroductionShown
import com.aparokio.aparok.domain.wallpaper.ObserveSpaceWallpaper
import com.aparokio.aparok.domain.workspace.DeepLinkToObjectDelegate
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.presentation.membership.provider.MembershipProvider
import com.aparokio.aparok.presentation.notifications.NotificationActionDelegate
import com.aparokio.aparok.presentation.notifications.NotificationsProvider
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope

class MainViewModelFactory @Inject constructor(
    private val resumeAccount: ResumeAccount,
    private val analytics: Analytics,
    private val interceptAccountStatus: InterceptAccountStatus,
    private val logout: Logout,
    private val checkAuthorizationStatus: CheckAuthorizationStatus,
    private val hasAccount: HasAccount,
    private val configStorage: ConfigStorage,
    private val localeProvider: LocaleProvider,
    private val notificationsProvider: NotificationsProvider,
    private val notificator: SystemNotificationService,
    private val notificationActionDelegate: NotificationActionDelegate,
    private val deepLinkToObjectDelegate: DeepLinkToObjectDelegate,
    private val awaitAccountStartManager: AwaitAccountStartManager,
    private val membershipProvider: MembershipProvider,
    private val globalSubscriptionManager: GlobalSubscriptionManager,
    private val spaceInviteResolver: SpaceInviteResolver,
    private val spaceManager: SpaceManager,
    private val spaceViews: SpaceViewSubscriptionContainer,
    private val pendingIntentStore: PendingIntentStore,
    private val observeSpaceWallpaper: ObserveSpaceWallpaper,
    private val urlBuilder: UrlBuilder,
    private val appShutdown: AppShutdown,
    private val scope: CoroutineScope,
    private val observeShowSpacesIntroduction: ObserveShowSpacesIntroduction,
    private val setSpacesIntroductionShown: SetSpacesIntroductionShown,
    private val appInfo: AppInfo,
    private val chatPreviewContainer: ChatPreviewContainer,
    private val chatsDetailsSubscriptionContainer: ChatsDetailsSubscriptionContainer,
    private val participantSubscriptionContainer: ParticipantSubscriptionContainer,
    private val crossSpaceObjectTypesContainer: CrossSpaceObjectTypesContainer,
    private val userSettingsRepository: UserSettingsRepository,
    private val resolveSpaceHomepage: ResolveSpaceHomepage,
    private val debugRunProfiler: DebugRunProfiler,
    private val preferredSpaceIdHolder: PreferredSpaceIdHolder,
    private val remainingSpacesPreloader: RemainingSpacesPreloader
    ) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T = MainViewModel(
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
        spaceViews = spaceViews,
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
    ) as T
}