package com.aparokio.aparok.di.feature.widgets

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.presentation.widgets.CreateChatObjectViewModel
import com.aparokio.aparok.ui.widgets.CreateChatObjectFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module

@Component(
    dependencies = [CreateChatObjectDependencies::class],
    modules = [
        CreateChatObjectModule::class,
        CreateChatObjectModule.Declarations::class
    ]
)
@PerScreen
interface CreateChatObjectComponent {
    @Component.Factory
    interface Builder {
        fun create(
            @BindsInstance vmParams: CreateChatObjectViewModel.VmParams,
            dependencies: CreateChatObjectDependencies
        ): CreateChatObjectComponent
    }

    fun inject(fragment: CreateChatObjectFragment)
}

@Module
object CreateChatObjectModule {
    @Module
    interface Declarations {
        @Binds
        @PerScreen
        fun bindViewModelFactory(factory: CreateChatObjectViewModel.Factory): ViewModelProvider.Factory
    }
}

interface CreateChatObjectDependencies : ComponentDependencies {
    fun analytics(): Analytics
    fun repo(): BlockRepository
    fun dispatchers(): AppCoroutineDispatchers
    fun settings(): UserSettingsRepository
}
