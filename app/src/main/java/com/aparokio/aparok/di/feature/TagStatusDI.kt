package com.aparokio.aparok.di.feature

import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_utils.di.scope.PerModal
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.`object`.UpdateDetail
import com.aparokio.aparok.domain.objects.StoreOfRelationOptions
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.domain.relations.DeleteRelationOptions
import com.aparokio.aparok.domain.relations.SetRelationOptionOrder
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.relations.providers.ObjectRelationProvider
import com.aparokio.aparok.presentation.relations.providers.ObjectValueProvider
import com.aparokio.aparok.presentation.relations.value.tagstatus.TagOrStatusValueViewModel
import com.aparokio.aparok.presentation.relations.value.tagstatus.TagOrStatusValueViewModelFactory
import com.aparokio.aparok.presentation.util.Dispatcher
import com.aparokio.aparok.ui.relations.value.TagOrStatusValueFragment
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import javax.inject.Named

//region OBJECT (layout BASIC, TASK etc.)
@PerModal
@Subcomponent(
    modules = [TagOrStatusValueObjectModule::class]
)
interface TagOrStatusValueObjectComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun params(params: TagOrStatusValueViewModel.ViewModelParams): Builder
        fun build(): TagOrStatusValueObjectComponent
    }

    fun inject(fragment: TagOrStatusValueFragment)
}

@Module
object TagOrStatusValueObjectModule {

    @JvmStatic
    @Provides
    @PerModal
    fun provideDeleteRelationOptions(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): DeleteRelationOptions = DeleteRelationOptions(
        repo = repo,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerModal
    fun provideSetRelationOptionOrder(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetRelationOptionOrder = SetRelationOptionOrder(
        repo = repo,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerModal
    fun provideFactory(
        @Named(ObjectRelationProvider.INTRINSIC_PROVIDER_TYPE) values: ObjectValueProvider,
        setObjectDetails: UpdateDetail,
        dispatcher: Dispatcher<Payload>,
        analytics: Analytics,
        params: TagOrStatusValueViewModel.ViewModelParams,
        deleteRelationOptions: DeleteRelationOptions,
        setRelationOptionOrder: SetRelationOptionOrder,
        analyticSpaceHelperDelegate: AnalyticSpaceHelperDelegate,
        storeOfRelations: StoreOfRelations,
        storeOfRelationOptions: StoreOfRelationOptions
    ): TagOrStatusValueViewModelFactory = TagOrStatusValueViewModelFactory(
        params = params,
        values = values,
        setObjectDetails = setObjectDetails,
        dispatcher = dispatcher,
        analytics = analytics,
        deleteRelationOptions = deleteRelationOptions,
        setRelationOptionOrder = setRelationOptionOrder,
        analyticSpaceHelperDelegate = analyticSpaceHelperDelegate,
        storeOfRelations = storeOfRelations,
        storeOfRelationOptions = storeOfRelationOptions
    )
}
//endregion

//region SET or COLLECTION (layout SET, COLLECTION )
@PerModal
@Subcomponent(
    modules = [TagOrStatusValueSetModule::class]
)
interface TagOrStatusValueSetComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun params(params: TagOrStatusValueViewModel.ViewModelParams): Builder
        fun build(): TagOrStatusValueSetComponent
    }

    fun inject(fragment: TagOrStatusValueFragment)
}

@Module
object TagOrStatusValueSetModule {

    @JvmStatic
    @Provides
    @PerModal
    fun provideDeleteRelationOptions(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): DeleteRelationOptions = DeleteRelationOptions(
        repo = repo,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerModal
    fun provideSetRelationOptionOrder(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetRelationOptionOrder = SetRelationOptionOrder(
        repo = repo,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerModal
    fun provideFactory(
        @Named(ObjectRelationProvider.INTRINSIC_PROVIDER_TYPE) values: ObjectValueProvider,
        setObjectDetails: UpdateDetail,
        dispatcher: Dispatcher<Payload>,
        analytics: Analytics,
        params: TagOrStatusValueViewModel.ViewModelParams,
        deleteRelationOptions: DeleteRelationOptions,
        setRelationOptionOrder: SetRelationOptionOrder,
        analyticSpaceHelperDelegate: AnalyticSpaceHelperDelegate,
        storeOfRelations: StoreOfRelations,
        storeOfRelationOptions: StoreOfRelationOptions
    ): TagOrStatusValueViewModelFactory = TagOrStatusValueViewModelFactory(
        params = params,
        values = values,
        setObjectDetails = setObjectDetails,
        dispatcher = dispatcher,
        analytics = analytics,
        deleteRelationOptions = deleteRelationOptions,
        setRelationOptionOrder = setRelationOptionOrder,
        analyticSpaceHelperDelegate = analyticSpaceHelperDelegate,
        storeOfRelations = storeOfRelations,
        storeOfRelationOptions = storeOfRelationOptions
    )
}
//endregion

//region DATA VIEW
@PerModal
@Subcomponent(
    modules = [TagOrStatusValueDataViewModule::class]
)
interface TagOrStatusValueDataViewComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun params(params: TagOrStatusValueViewModel.ViewModelParams): Builder
        fun build(): TagOrStatusValueDataViewComponent
    }

    fun inject(fragment: TagOrStatusValueFragment)
}

@Module
object TagOrStatusValueDataViewModule {

    @JvmStatic
    @Provides
    @PerModal
    fun provideDeleteRelationOptions(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): DeleteRelationOptions = DeleteRelationOptions(
        repo = repo,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerModal
    fun provideSetRelationOptionOrder(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetRelationOptionOrder = SetRelationOptionOrder(
        repo = repo,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerModal
    fun provideFactory(
        @Named(ObjectRelationProvider.DATA_VIEW_PROVIDER_TYPE) values: ObjectValueProvider,
        setObjectDetails: UpdateDetail,
        dispatcher: Dispatcher<Payload>,
        analytics: Analytics,
        params: TagOrStatusValueViewModel.ViewModelParams,
        deleteRelationOptions: DeleteRelationOptions,
        setRelationOptionOrder: SetRelationOptionOrder,
        analyticSpaceHelperDelegate: AnalyticSpaceHelperDelegate,
        storeOfRelations: StoreOfRelations,
        storeOfRelationOptions: StoreOfRelationOptions
    ): TagOrStatusValueViewModelFactory = TagOrStatusValueViewModelFactory(
        params = params,
        values = values,
        setObjectDetails = setObjectDetails,
        dispatcher = dispatcher,
        analytics = analytics,
        deleteRelationOptions = deleteRelationOptions,
        setRelationOptionOrder = setRelationOptionOrder,
        analyticSpaceHelperDelegate = analyticSpaceHelperDelegate,
        storeOfRelations = storeOfRelations,
        storeOfRelationOptions = storeOfRelationOptions
    )
}
//endregion