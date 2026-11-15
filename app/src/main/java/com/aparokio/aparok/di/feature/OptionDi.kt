package com.aparokio.aparok.di.feature

import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_utils.di.scope.PerModal
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.`object`.SetObjectDetails
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.domain.relations.CreateRelationOption
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.relations.option.CreateOrEditOptionViewModel
import com.aparokio.aparok.presentation.relations.option.CreateOrEditOptionViewModelFactory
import com.aparokio.aparok.presentation.relations.providers.ObjectRelationProvider
import com.aparokio.aparok.presentation.relations.providers.ObjectValueProvider
import com.aparokio.aparok.presentation.util.Dispatcher
import com.aparokio.aparok.ui.relations.value.CreateOrEditOptionFragment
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import javax.inject.Named

//region OBJECT (layout BASIC, TASK etc.)
@PerModal
@Subcomponent(
    modules = [CreateOrEditOptionObjectModule::class]
)
interface CreateOrEditOptionObjectComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun params(params: CreateOrEditOptionViewModel.ViewModelParams): Builder
        fun build(): CreateOrEditOptionObjectComponent
    }

    fun inject(fragment: CreateOrEditOptionFragment)
}

@Module
object CreateOrEditOptionObjectModule {

    @JvmStatic
    @Provides
    @PerModal
    fun createRelationOption(
        repo: BlockRepository
    ): CreateRelationOption = CreateRelationOption(repo = repo)

    @JvmStatic
    @Provides
    @PerModal
    fun provideFactory(
        params: CreateOrEditOptionViewModel.ViewModelParams,
        @Named(ObjectRelationProvider.INTRINSIC_PROVIDER_TYPE) values: ObjectValueProvider,
        setObjectDetails: SetObjectDetails,
        dispatcher: Dispatcher<Payload>,
        spaceManager: SpaceManager,
        analytics: Analytics,
        createOption: CreateRelationOption,
        storeOfRelations: StoreOfRelations,
        analyticSpaceHelperDelegate: AnalyticSpaceHelperDelegate
    ): CreateOrEditOptionViewModelFactory = CreateOrEditOptionViewModelFactory(
        params = params,
        values = values,
        createOption = createOption,
        setObjectDetails = setObjectDetails,
        dispatcher = dispatcher,
        spaceManager = spaceManager,
        analytics = analytics,
        storeOfRelations = storeOfRelations,
        analyticSpaceHelperDelegate = analyticSpaceHelperDelegate
    )
}
//endregion

//region SET or COLLECTION (layout SET, COLLECTION )
@PerModal
@Subcomponent(
    modules = [CreateOrEditOptionSetModule::class]
)
interface CreateOrEditOptionSetComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun params(params: CreateOrEditOptionViewModel.ViewModelParams): Builder
        fun build(): CreateOrEditOptionSetComponent
    }

    fun inject(fragment: CreateOrEditOptionFragment)
}

@Module
object CreateOrEditOptionSetModule {

    @JvmStatic
    @Provides
    @PerModal
    fun createRelationOption(
        repo: BlockRepository
    ): CreateRelationOption = CreateRelationOption(repo = repo)

    @JvmStatic
    @Provides
    @PerModal
    fun provideFactory(
        params: CreateOrEditOptionViewModel.ViewModelParams,
        @Named(ObjectRelationProvider.INTRINSIC_PROVIDER_TYPE) values: ObjectValueProvider,
        setObjectDetails: SetObjectDetails,
        dispatcher: Dispatcher<Payload>,
        spaceManager: SpaceManager,
        analytics: Analytics,
        createOption: CreateRelationOption,
        storeOfRelations: StoreOfRelations,
        analyticSpaceHelperDelegate: AnalyticSpaceHelperDelegate
    ): CreateOrEditOptionViewModelFactory = CreateOrEditOptionViewModelFactory(
        params = params,
        values = values,
        createOption = createOption,
        setObjectDetails = setObjectDetails,
        dispatcher = dispatcher,
        spaceManager = spaceManager,
        analytics = analytics,
        storeOfRelations = storeOfRelations,
        analyticSpaceHelperDelegate = analyticSpaceHelperDelegate
    )
}
//endregion

//region DATA VIEW
@PerModal
@Subcomponent(
    modules = [CreateOrEditOptionDataViewModule::class]
)
interface CreateOrEditOptionDataViewComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun params(params: CreateOrEditOptionViewModel.ViewModelParams): Builder
        fun build(): CreateOrEditOptionDataViewComponent
    }

    fun inject(fragment: CreateOrEditOptionFragment)
}

@Module
object CreateOrEditOptionDataViewModule {

    @JvmStatic
    @Provides
    @PerModal
    fun createRelationOption(
        repo: BlockRepository
    ): CreateRelationOption = CreateRelationOption(repo = repo)

    @JvmStatic
    @Provides
    @PerModal
    fun provideFactory(
        params: CreateOrEditOptionViewModel.ViewModelParams,
        @Named(ObjectRelationProvider.DATA_VIEW_PROVIDER_TYPE) values: ObjectValueProvider,
        setObjectDetails: SetObjectDetails,
        dispatcher: Dispatcher<Payload>,
        spaceManager: SpaceManager,
        analytics: Analytics,
        createOption: CreateRelationOption,
        storeOfRelations: StoreOfRelations,
        analyticSpaceHelperDelegate: AnalyticSpaceHelperDelegate
    ): CreateOrEditOptionViewModelFactory = CreateOrEditOptionViewModelFactory(
        params = params,
        values = values,
        createOption = createOption,
        setObjectDetails = setObjectDetails,
        dispatcher = dispatcher,
        spaceManager = spaceManager,
        analytics = analytics,
        storeOfRelations = storeOfRelations,
        analyticSpaceHelperDelegate = analyticSpaceHelperDelegate
    )
}
//endregion