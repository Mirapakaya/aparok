package com.aparokio.aparok.di.feature.multiplayer

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_utils.di.scope.PerDialog
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.membership.provider.MembershipProvider
import com.aparokio.aparok.presentation.multiplayer.SpaceJoinRequestViewModel
import com.aparokio.aparok.ui.multiplayer.SpaceJoinRequestFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module

@Component(
    dependencies = [SpaceJoinRequestDependencies::class],
    modules = [
        SpaceJoinRequestModule::class,
        SpaceJoinRequestModule.Declarations::class
    ]
)
@PerDialog
interface SpaceJoinRequestComponent {

    @Component.Builder
    interface Builder {
        fun withDependencies(dependencies: SpaceJoinRequestDependencies): Builder
        @BindsInstance
        fun withParams(params: SpaceJoinRequestViewModel.VmParams): Builder
        fun build(): SpaceJoinRequestComponent
    }

    fun inject(fragment: SpaceJoinRequestFragment)
}

@Module
object SpaceJoinRequestModule {

    @Module
    interface Declarations {
        @PerScreen
        @Binds
        fun bindViewModelFactory(factory: SpaceJoinRequestViewModel.Factory): ViewModelProvider.Factory
    }

}

interface SpaceJoinRequestDependencies : ComponentDependencies {
    fun blockRepository(): BlockRepository
    fun urlBuilder(): UrlBuilder
    fun dispatchers(): AppCoroutineDispatchers
    fun analytics(): Analytics
    fun analyticSpaceHelper(): AnalyticSpaceHelperDelegate
    fun permissions(): UserPermissionProvider
    fun provideSpaceViewContainer(): SpaceViewSubscriptionContainer
    fun provideMembershipProvider(): MembershipProvider
}