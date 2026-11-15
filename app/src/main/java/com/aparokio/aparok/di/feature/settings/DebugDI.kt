package com.aparokio.aparok.di.feature.settings

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.device.PathProvider
import com.aparokio.aparok.presentation.settings.DebugViewModel
import com.aparokio.aparok.presentation.util.downloader.UriFileProvider
import com.aparokio.aparok.ui.settings.DebugFragment
import dagger.Binds
import dagger.Component
import dagger.Module

@Component(
    dependencies = [DebugDependencies::class],
    modules = [
        DebugModule::class,
        DebugModule.Declarations::class
    ]
)
@PerScreen
interface DebugComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: DebugDependencies): DebugComponent
    }

    fun inject(fragment: DebugFragment)
}

@Module
object DebugModule {
    @Module
    interface Declarations {
        @PerScreen
        @Binds
        fun bindViewModelFactory(
            factory: DebugViewModel.Factory
        ): ViewModelProvider.Factory
    }
}

interface DebugDependencies : ComponentDependencies {
    fun path(): PathProvider
    fun auth(): AuthRepository
    fun repo(): BlockRepository
    fun dispatchers(): AppCoroutineDispatchers
    fun uriFileProvider(): UriFileProvider
    fun userSettingsRepository(): UserSettingsRepository
}