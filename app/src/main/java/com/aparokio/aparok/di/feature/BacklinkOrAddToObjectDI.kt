package com.aparokio.aparok.di.feature

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.interactor.sets.GetObjectTypes
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.misc.DateProvider
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.domain.search.SearchObjects
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.linking.BackLinkOrAddToObjectViewModelFactory
import com.aparokio.aparok.presentation.search.ObjectSearchViewModel
import com.aparokio.aparok.ui.linking.BacklinkOrAddToObjectFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides


@PerScreen
@Component(
    dependencies = [BacklinkOrAddToObjectDependencies::class],
    modules = [
        BackLinkToObjectModule::class,
        BackLinkToObjectModule.Declarations::class
    ]
)
interface BacklinkOrAddToObjectComponent {

    @Component.Builder
    interface Builder {

        fun withDependencies(dependency: BacklinkOrAddToObjectDependencies): Builder

        @BindsInstance
        fun withParams(params: ObjectSearchViewModel.VmParams): Builder

        fun build(): BacklinkOrAddToObjectComponent
    }

    fun inject(fragment: BacklinkOrAddToObjectFragment)
}

@Module
object BackLinkToObjectModule {

    @JvmStatic
    @Provides
    @PerScreen
    fun provideGetObjectTypesUseCase(
        repository: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): GetObjectTypes = GetObjectTypes(repository, dispatchers)

    @Module
    interface Declarations {

        @PerScreen
        @Binds
        fun bindViewModelFactory(factory: BackLinkOrAddToObjectViewModelFactory): ViewModelProvider.Factory
    }
}

interface BacklinkOrAddToObjectDependencies : ComponentDependencies {
    fun authRepository(): AuthRepository
    fun blockRepository(): BlockRepository
    fun urlBuilder(): UrlBuilder
    fun dispatchers(): AppCoroutineDispatchers
    fun analytics(): Analytics
    fun analyticSpaceHelper(): AnalyticSpaceHelperDelegate
    fun searchObjects(): SearchObjects
    fun dateProvider(): DateProvider
    fun fieldParser(): FieldParser
    fun storeOfObjectTypes(): StoreOfObjectTypes
    fun spaceViews(): SpaceViewSubscriptionContainer
}
