package com.aparokio.aparok.di.feature.notifications

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_utils.di.scope.PerDialog
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.notifications.NotificationsProvider
import com.aparokio.aparok.presentation.notifications.NotificationsViewModelFactory
import com.aparokio.aparok.ui.notifications.NotificationsFragment
import dagger.Binds
import dagger.Component
import dagger.Module

@Component(
    dependencies = [NotificationDependencies::class],
    modules = [
        NotificationsModule::class,
        NotificationsModule.Declarations::class
    ]
)
@PerDialog
interface NotificationComponent {
    @Component.Factory
    interface Factory {
        fun create(dependency: NotificationDependencies): NotificationComponent
    }

    fun inject(fragment: NotificationsFragment)
}

@Module
object NotificationsModule {
    @Module
    interface Declarations {
        @PerDialog
        @Binds
        fun factory(factory: NotificationsViewModelFactory): ViewModelProvider.Factory
    }
}

interface NotificationDependencies : ComponentDependencies {
    fun repo(): BlockRepository
    fun analytics(): Analytics
    fun notificationsProvider(): NotificationsProvider
    fun spaceManager(): SpaceManager
    fun userSettingsRepository(): UserSettingsRepository
    fun provideDispatchers(): AppCoroutineDispatchers
    fun analyticSpaceHelper(): AnalyticSpaceHelperDelegate
}