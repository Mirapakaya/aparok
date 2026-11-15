package com.aparokio.aparok.di.feature.settings

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.auth.interactor.GetAccount
import com.aparokio.aparok.domain.auth.interactor.GetLibraryVersion
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.ui.settings.AboutAppFragment
import com.aparokio.aparok.ui_settings.about.AboutAppViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Component(
    modules = [AboutAppModule::class],
    dependencies = [AboutAppDependencies::class]
)
@AboutAppScope
interface AboutAppComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: AboutAppDependencies): AboutAppComponent
    }

    fun inject(fragment: AboutAppFragment)
}

@Module
object AboutAppModule {

    @JvmStatic
    @Provides
    @AboutAppScope
    fun provideGetAccountUseCase(
        repo: AuthRepository,
        dispatcher: AppCoroutineDispatchers
    ): GetAccount = GetAccount(repo = repo, dispatcher = dispatcher)

    @JvmStatic
    @Provides
    @AboutAppScope
    fun provideGetVersion(
        repo: AuthRepository
    ): GetLibraryVersion = GetLibraryVersion(repo)

    @Module
    interface Declarations {

        @AboutAppScope
        @Binds
        fun bindViewModelFactory(
            factory: AboutAppViewModel.Factory
        ): ViewModelProvider.Factory
    }
}

interface AboutAppDependencies : ComponentDependencies {
    fun authRepo(): AuthRepository
    fun configStorage(): ConfigStorage
    fun analytics(): Analytics
    fun dispatchers(): AppCoroutineDispatchers
    fun analyticSpaceHelper(): AnalyticSpaceHelperDelegate
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AboutAppScope