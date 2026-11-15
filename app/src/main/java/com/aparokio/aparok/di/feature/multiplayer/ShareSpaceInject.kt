package com.aparokio.aparok.di.feature.multiplayer

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_utils.di.scope.PerDialog
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.clipboard.Clipboard
import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.domain.config.TechSpaceProvider
import com.aparokio.aparok.domain.debugging.Logger
import com.aparokio.aparok.domain.invite.SpaceInviteLinkStore
import com.aparokio.aparok.domain.library.StorelessSubscriptionContainer
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.resources.StringResourceProvider
import com.aparokio.aparok.domain.search.ProfileSubscriptionManager
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.membership.provider.MembershipProvider
import com.aparokio.aparok.presentation.multiplayer.ShareSpaceViewModel
import com.aparokio.aparok.presentation.spaces.SpaceGradientProvider
import com.aparokio.aparok.ui.multiplayer.ShareSpaceFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(
    dependencies = [ShareSpaceDependencies::class],
    modules = [
        ShareSpaceModule::class,
        ShareSpaceModule.Declarations::class
    ]
)
@PerDialog
interface ShareSpaceComponent {
    @Component.Builder
    interface Builder {
        fun withDependencies(dependencies: ShareSpaceDependencies): Builder
        @BindsInstance
        fun withParams(params: ShareSpaceViewModel.VmParams): Builder
        fun build(): ShareSpaceComponent
    }

    fun inject(fragment: ShareSpaceFragment)
}

@Module
object ShareSpaceModule {

    @PerDialog
    @Provides
    fun gradientProvider(): SpaceGradientProvider = SpaceGradientProvider.Default

    @Module
    interface Declarations {
        @PerScreen
        @Binds
        fun bindViewModelFactory(factory: ShareSpaceViewModel.Factory): ViewModelProvider.Factory
    }
}

interface ShareSpaceDependencies : ComponentDependencies {
    fun blockRepository(): BlockRepository
    fun auth() : AuthRepository
    fun urlBuilder(): UrlBuilder
    fun spaceManager(): SpaceManager
    fun dispatchers(): AppCoroutineDispatchers
    fun container(): StorelessSubscriptionContainer
    fun config(): ConfigStorage
    fun techSpaceProvider(): TechSpaceProvider
    fun permissions(): UserPermissionProvider
    fun analytics(): Analytics
    fun analyticSpaceHelper(): AnalyticSpaceHelperDelegate
    fun provideMembershipProvider(): MembershipProvider
    fun spaceViewSubscriptionContainer(): SpaceViewSubscriptionContainer
    fun clipboard(): Clipboard
    fun logger(): Logger
    fun spaceInviteLinkStore(): SpaceInviteLinkStore
    fun stringResourceProvider(): StringResourceProvider
    fun profileContainer(): ProfileSubscriptionManager
}