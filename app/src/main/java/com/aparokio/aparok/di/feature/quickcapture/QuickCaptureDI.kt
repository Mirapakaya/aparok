package com.aparokio.aparok.di.feature.quickcapture

import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.ai.TypeSuggestionEngine
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.debugging.Logger
import com.aparokio.aparok.domain.event.interactor.SpaceSyncAndP2PStatusProvider
import com.aparokio.aparok.domain.library.StorelessSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.multiplayer.ParticipantSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.ui.quickcapture.QuickCaptureFragment
import dagger.Component
import javax.inject.Named
import kotlinx.coroutines.CoroutineScope
import com.aparokio.aparok.di.main.ConfigModule

@Component(
    dependencies = [QuickCaptureDependencies::class]
)
@PerScreen
interface QuickCaptureComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: QuickCaptureDependencies): QuickCaptureComponent
    }

    fun inject(fragment: QuickCaptureFragment)
}

interface QuickCaptureDependencies : ComponentDependencies {
    fun blockRepository(): BlockRepository
    fun appCoroutineDispatchers(): AppCoroutineDispatchers
    fun analytics(): Analytics
    fun urlBuilder(): UrlBuilder
    fun spaceViewSubscriptionContainer(): SpaceViewSubscriptionContainer
    fun userSettingsRepository(): UserSettingsRepository
    fun spaceManager(): SpaceManager
    fun userPermissionProvider(): UserPermissionProvider
    fun storeOfObjectTypes(): StoreOfObjectTypes
    fun provideStorelessSubscriptionContainer(): StorelessSubscriptionContainer
    fun analyticsHelper(): AnalyticSpaceHelperDelegate
    fun spaceSyncAndP2PStatusProvider(): SpaceSyncAndP2PStatusProvider
    fun typeSuggestionEngine(): TypeSuggestionEngine
    fun participantSubscriptionContainer(): ParticipantSubscriptionContainer
    fun authRepository(): AuthRepository
    @Named(ConfigModule.DEFAULT_APP_COROUTINE_SCOPE)
    fun applicationCoroutineScope(): CoroutineScope
    fun logger(): Logger
}
