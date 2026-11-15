package com.aparokio.aparok.di.feature.search

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.chats.ChatsDetailsSubscriptionContainer
import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.multiplayer.ParticipantSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.objects.CrossSpaceObjectTypesContainer
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.domain.workspace.DeepLinkToObjectDelegate
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.feature_search.presentation.SearchViewModel
import com.aparokio.aparok.ui.search.v2.SearchV2Fragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(
    dependencies = [SearchV2Dependencies::class],
    modules = [
        SearchV2Module::class,
        SearchV2Module.Declarations::class
    ]
)
@PerScreen
interface SearchV2Component {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance vmParams: SearchViewModel.VmParams,
            dependencies: SearchV2Dependencies
        ): SearchV2Component
    }

    fun inject(fragment: SearchV2Fragment)

    /** For embedded use (the chat attach-object sheet). */
    fun searchViewModel(): SearchViewModel
}

@Module
object SearchV2Module {

    @JvmStatic
    @Provides
    @PerScreen
    fun provideDeepLinkToObjectDelegate(
        default: DeepLinkToObjectDelegate.Default
    ): DeepLinkToObjectDelegate = default

    @JvmStatic
    @Provides
    @PerScreen
    fun provideSearchViewModel(
        factory: SearchViewModel.Factory
    ): SearchViewModel = factory.create(SearchViewModel::class.java)

    @Module
    interface Declarations {
        @Binds
        @PerScreen
        fun bindViewModelFactory(factory: SearchViewModel.Factory): ViewModelProvider.Factory
    }
}

interface SearchV2Dependencies : ComponentDependencies {
    fun repo(): BlockRepository
    fun userSettingsRepository(): UserSettingsRepository
    fun dispatchers(): AppCoroutineDispatchers
    fun urlBuilder(): UrlBuilder
    fun fieldParser(): FieldParser
    fun configStorage(): ConfigStorage
    fun spaceManager(): SpaceManager
    fun userPermissionProvider(): UserPermissionProvider
    fun spaceViews(): SpaceViewSubscriptionContainer
    fun participants(): ParticipantSubscriptionContainer
    fun chatsDetails(): ChatsDetailsSubscriptionContainer
    fun crossSpaceObjectTypes(): CrossSpaceObjectTypesContainer
    fun storeOfObjectTypes(): StoreOfObjectTypes
}
