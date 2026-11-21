package com.aparokio.aparok.di.feature.search

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.`object`.DuplicateObject
import com.aparokio.aparok.domain.misc.DeepLinkResolver
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.objects.StoreOfRelationOptions
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.common.PayloadDelegator
import com.aparokio.aparok.presentation.search.GlobalSearchViewModel
import com.aparokio.aparok.ui.search.GlobalSearchFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(
    dependencies = [GlobalSearchDependencies::class],
    modules = [
        GlobalSearchModule::class,
        GlobalSearchModule.Declarations::class
    ]
)
@PerScreen
interface GlobalSearchComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance vmParams: GlobalSearchViewModel.VmParams,
            dependencies: GlobalSearchDependencies
        ): GlobalSearchComponent
    }

    fun inject(fragment: GlobalSearchFragment)
    fun getViewModel(): GlobalSearchViewModel
}

@Module
object GlobalSearchModule {

    @JvmStatic
    @Provides
    @PerScreen
    fun provideDuplicateObject(
        repo: BlockRepository
    ): DuplicateObject = DuplicateObject(repo = repo)

    @Module
    interface Declarations {
        @Binds
        @PerScreen
        fun bindViewModelFactory(factory: GlobalSearchViewModel.Factory): ViewModelProvider.Factory
    }
}

interface GlobalSearchDependencies : ComponentDependencies {
    fun urlBuilder(): UrlBuilder
    fun repo(): BlockRepository
    fun dispatchers(): AppCoroutineDispatchers
    fun storeOfObjectTypes(): StoreOfObjectTypes
    fun storeOfRelations(): StoreOfRelations
    fun analytics(): Analytics
    fun analyticsHelper(): AnalyticSpaceHelperDelegate
    fun userSettingsRepository(): UserSettingsRepository
    fun fieldParser(): FieldParser
    fun spaceViews(): SpaceViewSubscriptionContainer
    fun storeOfRelationOptions(): StoreOfRelationOptions
    fun deepLinkResolver(): DeepLinkResolver
    fun spaceManager(): SpaceManager
    fun payloadDelegator(): PayloadDelegator
}
