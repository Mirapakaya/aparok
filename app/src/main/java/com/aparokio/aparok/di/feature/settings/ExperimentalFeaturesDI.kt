package com.aparokio.aparok.di.feature.settings

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.presentation.settings.ExperimentalFeaturesViewModel
import com.aparokio.aparok.ui.settings.ExperimentalFeaturesFragment
import dagger.Binds
import dagger.Component
import dagger.Module

@Component(
    dependencies = [ExperimentalFeaturesDependencies::class],
    modules = [
        ExperimentalFeaturesModule::class,
        ExperimentalFeaturesModule.Declarations::class
    ]
)
@PerScreen
interface ExperimentalFeaturesComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: ExperimentalFeaturesDependencies): ExperimentalFeaturesComponent
    }

    fun inject(fragment: ExperimentalFeaturesFragment)
}

@Module
object ExperimentalFeaturesModule {
    @Module
    interface Declarations {
        @PerScreen
        @Binds
        fun bindViewModelFactory(
            factory: ExperimentalFeaturesViewModel.Factory
        ): ViewModelProvider.Factory
    }
}

interface ExperimentalFeaturesDependencies : ComponentDependencies {
    fun userSettingsRepository(): UserSettingsRepository
}
