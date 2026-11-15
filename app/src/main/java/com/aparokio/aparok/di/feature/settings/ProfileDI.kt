package com.aparokio.aparok.di.feature.settings

import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.core_utils.notifications.NotificationPermissionManager
import com.aparokio.aparok.domain.account.AwaitAccountStartManager
import com.aparokio.aparok.domain.account.DeleteAccount
import com.aparokio.aparok.domain.auth.interactor.Logout
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.launch.PreferredSpaceIdHolder
import com.aparokio.aparok.domain.launch.RemainingSpacesPreloader
import com.aparokio.aparok.domain.debugging.DebugSpace
import com.aparokio.aparok.domain.icon.RemoveObjectIcon
import com.aparokio.aparok.domain.icon.SetDocumentImageIcon
import com.aparokio.aparok.domain.library.StorelessSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.GenerateOneToOneChatLink
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.networkmode.GetNetworkMode
import com.aparokio.aparok.domain.`object`.SetObjectDetails
import com.aparokio.aparok.domain.search.ProfileSubscriptionManager
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.presentation.membership.provider.MembershipProvider
import com.aparokio.aparok.presentation.spaces.SpaceGradientProvider
import com.aparokio.aparok.ui.settings.ProfileSettingsFragment
import com.aparokio.aparok.ui_settings.account.ProfileSettingsViewModel
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [ProfileModule::class])
@PerScreen
interface ProfileSubComponent {

    @Subcomponent.Builder
    interface Builder {
        fun module(module: ProfileModule): Builder
        fun build(): ProfileSubComponent
    }

    fun inject(fragment: ProfileSettingsFragment)
}

@Module(includes = [ProfileModule.Bindings::class])
object ProfileModule {

    @JvmStatic
    @Provides
    @PerScreen
    fun provideViewModelFactory(
        analytics: Analytics,
        storelessSubscriptionContainer: StorelessSubscriptionContainer,
        setObjectDetails: SetObjectDetails,
        configStorage: ConfigStorage,
        urlBuilder: UrlBuilder,
        setDocumentImageIcon: SetDocumentImageIcon,
        membershipProvider: MembershipProvider,
        getNetworkMode: GetNetworkMode,
        profileSubscriptionManager: ProfileSubscriptionManager,
        removeObjectIcon: RemoveObjectIcon,
        notificationPermissionManager: NotificationPermissionManager,
        userPermissionProvider: UserPermissionProvider,
        generateOneToOneChatLink: GenerateOneToOneChatLink,
        userSettingsRepository: UserSettingsRepository
    ): ProfileSettingsViewModel.Factory = ProfileSettingsViewModel.Factory(
        analytics = analytics,
        container = storelessSubscriptionContainer,
        setObjectDetails = setObjectDetails,
        configStorage = configStorage,
        urlBuilder = urlBuilder,
        setDocumentImageIcon = setDocumentImageIcon,
        membershipProvider = membershipProvider,
        getNetworkMode = getNetworkMode,
        profileSubscriptionManager = profileSubscriptionManager,
        removeObjectIcon = removeObjectIcon,
        notificationPermissionManager = notificationPermissionManager,
        userPermissionProvider = userPermissionProvider,
        generateOneToOneChatLink = generateOneToOneChatLink,
        userSettingsRepository = userSettingsRepository
    )

    @Provides
    @PerScreen
    fun provideSpaceGradientProvider(): SpaceGradientProvider = SpaceGradientProvider.Default

    @Provides
    @PerScreen
    fun provideDebugSync(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers,
        spaceManager: SpaceManager
    ): DebugSpace = DebugSpace(
        repo = repo,
        dispatchers = dispatchers,
        spaceManager = spaceManager
    )

    @Provides
    @PerScreen
    fun provideRemoveObjectIcon(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): RemoveObjectIcon = RemoveObjectIcon(repo, dispatchers)

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
        config = provider,
        user = user,
        dispatchers = dispatchers,
        spaceManager = spaceManager,
        awaitAccountStartManager = awaitAccountStartManager,
        remainingSpacesPreloader = remainingSpacesPreloader,
        preferredSpaceIdHolder = preferredSpaceIdHolder
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun deleteAccount(repo: AuthRepository): DeleteAccount = DeleteAccount(repo)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideSetObjectDetails(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetObjectDetails = SetObjectDetails(
        repo,
        dispatchers
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideSetDocumentImageIcon(
        repo: BlockRepository
    ): SetDocumentImageIcon = SetDocumentImageIcon(repo)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideGenerateOneToOneChatLink(
        dispatchers: AppCoroutineDispatchers
    ): GenerateOneToOneChatLink = GenerateOneToOneChatLink(
        dispatchers = dispatchers
    )

    @Module
    interface Bindings {
        // Add bindings if needed
    }
}