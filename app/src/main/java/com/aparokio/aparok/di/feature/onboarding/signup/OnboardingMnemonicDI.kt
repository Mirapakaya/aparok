package com.aparokio.aparok.di.feature.onboarding.signup

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.auth.interactor.GetMnemonic
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.device.NetworkConnectionStatus
import com.aparokio.aparok.domain.network.NetworkModeProvider
import com.aparokio.aparok.presentation.onboarding.signup.OnboardingMnemonicViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Component(
    dependencies = [OnboardingMnemonicDependencies::class],
    modules = [
        OnboardingMnemonicModule::class,
        OnboardingMnemonicModule.Declarations::class
    ]
)
@MnemonicScreenScope
interface OnboardingMnemonicComponent {

    @Component.Factory
    interface Builder {
        fun create(dependencies: OnboardingMnemonicDependencies): OnboardingMnemonicComponent
    }

    fun getViewModel(): OnboardingMnemonicViewModel
}

@Module
object OnboardingMnemonicModule {

    @JvmStatic
    @Provides
    @MnemonicScreenScope
    fun provideGetMnemonicUseCase(
        authRepository: AuthRepository,
    ): GetMnemonic = GetMnemonic(
        repository = authRepository
    )
    
    @Module
    interface Declarations {

        @Binds
        @MnemonicScreenScope
        fun bindViewModelFactory(factory: OnboardingMnemonicViewModel.Factory): ViewModelProvider.Factory

    }
}

interface OnboardingMnemonicDependencies : ComponentDependencies {
    fun authRepository(): AuthRepository
    fun analytics(): Analytics
    fun networkModeProvider(): NetworkModeProvider
    fun networkConnectionStatus(): NetworkConnectionStatus
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class MnemonicScreenScope