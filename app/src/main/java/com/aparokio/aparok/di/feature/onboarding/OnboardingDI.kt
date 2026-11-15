package com.aparokio.aparok.di.feature.onboarding

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.presentation.onboarding.OnboardingViewModel
import com.aparokio.aparok.presentation.util.downloader.UriFileProvider
import com.aparokio.aparok.ui.onboarding.OnboardingFragment
import dagger.Binds
import dagger.Component
import dagger.Module

@Component(
    dependencies = [OnboardingDependencies::class],
    modules = [
        OnboardingModule::class,
        OnboardingModule.Declarations::class
    ]
)
@PerScreen
interface OnboardingComponent {
    @Component.Factory
    interface Builder {
        fun create(dependencies: OnboardingDependencies): OnboardingComponent
    }

    fun inject(fragment: OnboardingFragment)
}

@Module
object OnboardingModule {
    @Module
    interface Declarations {
        @Binds
        @AuthScreenScope
        fun bindViewModelFactory(factory: OnboardingViewModel.Factory): ViewModelProvider.Factory
    }
}

interface OnboardingDependencies : ComponentDependencies {
    fun analytics(): Analytics
    fun context(): Context
    fun uriFileProvider(): UriFileProvider
}