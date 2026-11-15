package com.aparokio.aparok.di.feature.participant

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.debugging.Logger
import com.aparokio.aparok.domain.icon.SetDocumentImageIcon
import com.aparokio.aparok.domain.library.StorelessSubscriptionContainer
import com.aparokio.aparok.domain.misc.LocaleProvider
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.multiplayer.RemoveSpaceMembers
import com.aparokio.aparok.domain.multiplayer.SearchOneToOneChatByIdentity
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.`object`.SetObjectDetails
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.domain.search.SubscriptionEventChannel
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.membership.provider.MembershipProvider
import com.aparokio.aparok.presentation.profile.ParticipantViewModel
import com.aparokio.aparok.ui.profile.ParticipantFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(
    dependencies = [ParticipantComponentDependencies::class],
    modules = [
        ParticipantModule::class,
        ParticipantModule.Declarations::class
    ]
)
@PerScreen
interface ParticipantComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance vmParams: ParticipantViewModel.VmParams,
            dependencies: ParticipantComponentDependencies
        ): ParticipantComponent
    }

    fun inject(fragment: ParticipantFragment)

}

@Module
object ParticipantModule {

    @JvmStatic
    @Provides
    @PerScreen
    fun provideSetObjectDetails(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetObjectDetails = SetObjectDetails(
        repo,
        dispatchers
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideSetDocumentImageIconUseCase(
        repo: BlockRepository
    ): SetDocumentImageIcon = SetDocumentImageIcon(repo)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideStoreLessSubscriptionContainer(
        repo: BlockRepository,
        channel: SubscriptionEventChannel,
        dispatchers: AppCoroutineDispatchers,
        logger: Logger
    ): StorelessSubscriptionContainer = StorelessSubscriptionContainer.Impl(
        repo = repo,
        channel = channel,
        dispatchers = dispatchers,
        logger = logger
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideSearchOneToOneChatByIdentity(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SearchOneToOneChatByIdentity = SearchOneToOneChatByIdentity(
        repo = repo,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideRemoveSpaceMembers(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): RemoveSpaceMembers = RemoveSpaceMembers(
        repo = repo,
        dispatchers = dispatchers
    )

    @Module
    interface Declarations {
        @PerScreen
        @Binds
        fun bindViewModelFactory(
            factory: ParticipantViewModel.Factory
        ): ViewModelProvider.Factory
    }
}

interface ParticipantComponentDependencies : ComponentDependencies {
    fun blockRepository(): BlockRepository
    fun analytics(): Analytics
    fun urlBuilder(): UrlBuilder
    fun dispatchers(): AppCoroutineDispatchers
    fun analyticsHelper(): AnalyticSpaceHelperDelegate
    fun userSettingsRepository(): UserSettingsRepository
    fun logger(): Logger
    fun localeProvider(): LocaleProvider
    fun userPermissionProvider(): UserPermissionProvider
    fun fieldsProvider(): FieldParser
    fun provideMembershipProvider(): MembershipProvider
    fun subEventChannel(): SubscriptionEventChannel
    fun provideConfigStorage(): ConfigStorage
    fun spaceViewSubscriptionContainer(): SpaceViewSubscriptionContainer
    fun spaceManager(): SpaceManager
}