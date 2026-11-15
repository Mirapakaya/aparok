package com.aparokio.aparok.di.feature.onboarding.login

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.CrashReporter
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.data.auth.event.EventProcessMigrationDateChannel
import com.aparokio.aparok.data.auth.event.EventProcessMigrationRemoteChannel
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.account.AwaitAccountStartManager
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.debugging.DebugAccountSelectTrace
import com.aparokio.aparok.domain.debugging.Logger
import com.aparokio.aparok.domain.device.PathProvider
import com.aparokio.aparok.domain.launch.PreferredSpaceIdHolder
import com.aparokio.aparok.domain.launch.RemainingSpacesPreloader
import com.aparokio.aparok.domain.misc.LocaleProvider
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.platform.InitialParamsProvider
import com.aparokio.aparok.domain.spaces.SpaceDeletedStatusWatcher
import com.aparokio.aparok.domain.subscriptions.GlobalSubscriptionManager
import com.aparokio.aparok.domain.workspace.EventProcessMigrationChannel
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.middleware.EventProxy
import com.aparokio.aparok.middleware.interactor.EventProcessMigrationMiddlewareChannel
import com.aparokio.aparok.presentation.auth.account.MigrationHelperDelegate
import com.aparokio.aparok.presentation.onboarding.login.OnboardingMnemonicLoginViewModel
import com.aparokio.aparok.presentation.util.downloader.UriFileProvider
import com.aparokio.aparok.providers.DefaultUriFileProvider
import dagger.Binds
import dagger.Component
import dagger.Module

@Component(
    dependencies = [OnboardingMnemonicLoginDependencies::class],
    modules = [
        OnboardingMnemonicLoginModule::class,
        OnboardingMnemonicLoginModule.Declarations::class
    ]
)
@PerScreen
interface OnboardingMnemonicLoginComponent {
    @Component.Factory
    interface Builder {
        fun create(
            dependencies: OnboardingMnemonicLoginDependencies
        ): OnboardingMnemonicLoginComponent
    }

    fun getViewModel(): OnboardingMnemonicLoginViewModel
}

@Module
object OnboardingMnemonicLoginModule {

    @Module
    interface Declarations {

        @Binds
        @PerScreen
        fun bindUriFileProvider(
            defaultProvider: DefaultUriFileProvider
        ): UriFileProvider

        @Binds
        @PerScreen
        fun bindMigrationHelperDelegate(
            impl: MigrationHelperDelegate.Impl
        ): MigrationHelperDelegate

        @Binds
        @PerScreen
        fun bindMigrationChannel(
            impl: EventProcessMigrationDateChannel
        ): EventProcessMigrationChannel

        @Binds
        @PerScreen
        fun bindMigrationRemoteChannel(
            impl: EventProcessMigrationMiddlewareChannel
        ): EventProcessMigrationRemoteChannel

        @Binds
        @PerScreen
        fun bindViewModelFactory(
            factory: OnboardingMnemonicLoginViewModel.Factory
        ): ViewModelProvider.Factory
    }
}

interface OnboardingMnemonicLoginDependencies : ComponentDependencies {
    fun authRepository(): AuthRepository
    fun analytics(): Analytics
    fun pathProvider(): PathProvider
    fun crashReporter(): CrashReporter
    fun configStorage(): ConfigStorage
    fun metricsProvider(): InitialParamsProvider
    fun spaceStatusWatcher(): SpaceDeletedStatusWatcher
    fun localeProvider(): LocaleProvider
    fun awaitAccountStartManager(): AwaitAccountStartManager
    fun userPermissionProvider() : UserPermissionProvider
    fun provideAppCoroutineDispatchers(): AppCoroutineDispatchers
    fun provideBlockRepository(): BlockRepository
    fun provideContext(): Context
    fun userSettings(): UserSettingsRepository
    fun spaceManager(): SpaceManager
    fun globalSubscriptionManager(): GlobalSubscriptionManager
    fun debugAccountSelectTrace(): DebugAccountSelectTrace
    fun logger(): Logger
    fun eventProxy(): EventProxy
    fun remainingSpacesPreloader(): RemainingSpacesPreloader
    fun preferredSpaceIdHolder(): PreferredSpaceIdHolder
}