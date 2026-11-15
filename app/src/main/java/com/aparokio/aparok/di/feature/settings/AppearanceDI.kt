package com.aparokio.aparok.di.feature.settings

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.theme.GetTheme
import com.aparokio.aparok.domain.theme.SetTheme
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.ui.settings.AppearanceFragment
import com.aparokio.aparok.ui_settings.appearance.AppearanceViewModel
import dagger.Component
import com.aparokio.aparok.ui_settings.appearance.ThemeApplicator
import com.aparokio.aparok.ui_settings.appearance.ThemeApplicatorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Component(
    dependencies = [AppearanceDependencies::class],
    modules = [
        AppearanceModule::class,
        AppearanceModule.Declarations::class
    ]
)
@PerScreen
interface AppearanceComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: AppearanceDependencies): AppearanceComponent
    }

    fun inject(fragment: AppearanceFragment)
}

@Module
object AppearanceModule {

    @JvmStatic
    @Provides
    @PerScreen
    fun provideGetThemeUseCase(repo: UserSettingsRepository): GetTheme = GetTheme(repo)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideSetThemeUseCase(repo: UserSettingsRepository): SetTheme = SetTheme(repo)

    @Module
    interface Declarations {

        @PerScreen
        @Binds
        fun bindViewModelFactory(
            factory: AppearanceViewModel.Factory
        ): ViewModelProvider.Factory

        @PerScreen
        @Binds
        fun bindThemeApplicator(applicator: ThemeApplicatorImpl): ThemeApplicator
    }
}

interface AppearanceDependencies : ComponentDependencies {
    fun userUserSettingsRepository(): UserSettingsRepository
    fun analytics(): Analytics
    fun analyticSpaceHelper(): AnalyticSpaceHelperDelegate
}