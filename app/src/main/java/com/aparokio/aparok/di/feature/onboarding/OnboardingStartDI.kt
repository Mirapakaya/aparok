package com.aparokio.aparok.di.feature.onboarding

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.CrashReporter
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.di.feature.onboarding.signup.SoulCreationScreenScope
import com.aparokio.aparok.domain.account.AwaitAccountStartManager
import com.aparokio.aparok.domain.auth.interactor.CreateAccount
import com.aparokio.aparok.domain.auth.interactor.SetupWallet
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.deeplink.PendingIntentStore
import com.aparokio.aparok.domain.device.PathProvider
import com.aparokio.aparok.domain.misc.LocaleProvider
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.`object`.ImportGetStartedUseCase
import com.aparokio.aparok.domain.platform.InitialParamsProvider
import com.aparokio.aparok.domain.resources.StringResourceProvider
import com.aparokio.aparok.domain.search.RelationsSubscriptionManager
import com.aparokio.aparok.domain.spaces.SpaceDeletedStatusWatcher
import com.aparokio.aparok.domain.subscriptions.GlobalSubscriptionManager
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.presentation.onboarding.OnboardingStartViewModel
import com.aparokio.aparok.presentation.spaces.SpaceGradientProvider
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Component(
    dependencies = [OnboardingStartDependencies::class],
    modules = [
        OnboardingStartModule::class,
        OnboardingStartModule.Declarations::class
    ]
)
@AuthScreenScope
interface OnboardingStartComponent {
    @Component.Factory
    interface Builder {
        fun create(dependencies: OnboardingStartDependencies): OnboardingStartComponent
    }

    fun getViewModel(): OnboardingStartViewModel
}

@Module
object OnboardingStartModule {

    @JvmStatic
    @Provides
    @AuthScreenScope
    fun gradientProvider(): SpaceGradientProvider = SpaceGradientProvider.Default

    @JvmStatic
    @Provides
    @AuthScreenScope
    fun provideCreateAccountUseCase(
        authRepository: AuthRepository,
        configStorage: ConfigStorage,
        initialParamsProvider: InitialParamsProvider,
        awaitAccountStartManager: AwaitAccountStartManager,
        spaceManager: SpaceManager,
        dispatchers: AppCoroutineDispatchers
    ): CreateAccount = CreateAccount(
        repository = authRepository,
        configStorage = configStorage,
        initialParamsProvider = initialParamsProvider,
        dispatcher = dispatchers,
        awaitAccountStartManager = awaitAccountStartManager,
        spaceManager = spaceManager
    )

    @JvmStatic
    @Provides
    @AuthScreenScope
    fun provideSetupWalletUseCase(
        authRepository: AuthRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetupWallet = SetupWallet(
        repository = authRepository,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @AuthScreenScope
    fun provideSetupSkipUseCase(
        repository: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ) = ImportGetStartedUseCase(
        repo = repository,
        dispatchers = dispatchers
    )

    @Module
    interface Declarations {
        @Binds
        @AuthScreenScope
        fun bindViewModelFactory(factory: OnboardingStartViewModel.Factory): ViewModelProvider.Factory
    }
}

interface OnboardingStartDependencies : ComponentDependencies {
    fun analytics(): Analytics
    fun blockRepository(): BlockRepository
    fun dispatchers(): AppCoroutineDispatchers
    fun configStorage(): ConfigStorage
    fun authRepository(): AuthRepository
    fun userSettings(): UserSettingsRepository
    fun relationsSubscriptionManager(): RelationsSubscriptionManager
    fun pathProvider(): PathProvider
    fun metricsProvider(): InitialParamsProvider
    fun crashReporter(): CrashReporter
    fun spaceManager(): SpaceManager
    fun spaceStatusWatcher(): SpaceDeletedStatusWatcher
    fun localeProvider(): LocaleProvider
    fun userPermissionProvider(): UserPermissionProvider
    fun awaitAccountStartManager(): AwaitAccountStartManager
    fun globalSubscriptionManager(): GlobalSubscriptionManager
    fun stringResourceProvider(): StringResourceProvider
    fun providePendingIntentStore(): PendingIntentStore
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthScreenScope