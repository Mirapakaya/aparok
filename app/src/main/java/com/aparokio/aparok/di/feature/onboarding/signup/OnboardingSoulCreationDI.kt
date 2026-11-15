package com.aparokio.aparok.di.feature.onboarding.signup

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.CrashReporter
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.account.AwaitAccountStartManager
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.deeplink.PendingIntentStore
import com.aparokio.aparok.domain.device.PathProvider
import com.aparokio.aparok.domain.misc.LocaleProvider
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.payments.SetMembershipEmail
import com.aparokio.aparok.domain.platform.InitialParamsProvider
import com.aparokio.aparok.domain.resources.StringResourceProvider
import com.aparokio.aparok.domain.search.ProfileSubscriptionManager
import com.aparokio.aparok.domain.search.RelationsSubscriptionManager
import com.aparokio.aparok.domain.spaces.SpaceDeletedStatusWatcher
import com.aparokio.aparok.domain.subscriptions.GlobalSubscriptionManager
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.presentation.onboarding.signup.OnboardingSetProfileNameViewModel
import com.aparokio.aparok.presentation.onboarding.signup.OnboardingEmailAndSelectionViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Component(
    dependencies = [OnboardingSoulCreationDependencies::class],
    modules = [
        OnboardingSoulCreationModule::class,
        OnboardingSoulCreationModule.Declarations::class
    ]
)
@SoulCreationScreenScope
interface OnboardingSoulCreationComponent {

    @Component.Factory
    interface Builder {
        fun create(dependencies: OnboardingSoulCreationDependencies): OnboardingSoulCreationComponent
    }

    fun getViewModel(): OnboardingSetProfileNameViewModel
    fun getEmailAndSelectionViewModel(): OnboardingEmailAndSelectionViewModel
}

@Module
object OnboardingSoulCreationModule {

    @JvmStatic
    @Provides
    @SoulCreationScreenScope
    fun provideSetMembershipEmail(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetMembershipEmail = SetMembershipEmail(repo = repo, dispatchers = dispatchers)

    @Module
    interface Declarations {
        @Binds
        @SoulCreationScreenScope
        fun bindViewModelFactory(factory: OnboardingSetProfileNameViewModel.Factory): ViewModelProvider.Factory
        
        @Binds
        @SoulCreationScreenScope
        fun bindEmailAndSelectionViewModelFactory(factory: OnboardingEmailAndSelectionViewModel.Factory): ViewModelProvider.Factory
    }
}

interface OnboardingSoulCreationDependencies : ComponentDependencies {
    fun blockRepository(): BlockRepository
    fun dispatchers(): AppCoroutineDispatchers
    fun configStorage(): ConfigStorage
    fun analytics(): Analytics
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
    fun provideProfileContainer() :ProfileSubscriptionManager
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class SoulCreationScreenScope