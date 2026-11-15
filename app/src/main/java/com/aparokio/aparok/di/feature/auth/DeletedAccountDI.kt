package com.aparokio.aparok.di.feature.auth

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.account.AwaitAccountStartManager
import com.aparokio.aparok.domain.account.DateHelper
import com.aparokio.aparok.domain.account.RestoreAccount
import com.aparokio.aparok.domain.auth.interactor.Logout
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.launch.PreferredSpaceIdHolder
import com.aparokio.aparok.domain.launch.RemainingSpacesPreloader
import com.aparokio.aparok.domain.misc.AppActionManager
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.subscriptions.GlobalSubscriptionManager
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.ext.DefaultDateHelper
import com.aparokio.aparok.presentation.auth.account.DeletedAccountViewModel
import com.aparokio.aparok.ui.auth.account.DeletedAccountFragment
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(
    dependencies = [DeletedAccountDependencies::class],
    modules = [
        DeletedAccountModule::class,
        DeletedAccountModule.Declarations::class
    ]
)
@PerScreen
interface DeletedAccountComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: DeletedAccountDependencies): DeletedAccountComponent
    }

    fun inject(fragment: DeletedAccountFragment)
}

@Module
object DeletedAccountModule {

    @JvmStatic
    @Provides
    @PerScreen
    fun provideRestoreAccount(
        repo: AuthRepository,
        dispatchers: AppCoroutineDispatchers
    ): RestoreAccount = RestoreAccount(
        repo = repo,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideLogoutUseCase(
        repo: AuthRepository,
        provider: ConfigStorage,
        dispatchers: AppCoroutineDispatchers,
        user: UserSettingsRepository,
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
    fun provideDateHelper(): DateHelper = DefaultDateHelper()

    @Module
    interface Declarations {

        @PerScreen
        @Binds
        fun bindViewModelFactory(
            factory: DeletedAccountViewModel.Factory
        ): ViewModelProvider.Factory

    }
}

interface DeletedAccountDependencies : ComponentDependencies {
    fun analytics(): Analytics
    fun appActionManager(): AppActionManager
    fun dispatchers(): AppCoroutineDispatchers
    fun configStorage(): ConfigStorage
    fun authRepository(): AuthRepository
    fun userSettingsRepository(): UserSettingsRepository
    fun spaceManager(): SpaceManager
    fun awaitAccountStartManager(): AwaitAccountStartManager
    fun userPermissionProvider(): UserPermissionProvider
    fun globalSubscriptionManager(): GlobalSubscriptionManager
    fun remainingSpacesPreloader(): RemainingSpacesPreloader
    fun preferredSpaceIdHolder(): PreferredSpaceIdHolder
}