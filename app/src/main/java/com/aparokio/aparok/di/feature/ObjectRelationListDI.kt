package com.aparokio.aparok.di.feature

import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.core_utils.di.scope.PerModal
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.`object`.UpdateDetail
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.domain.primitives.SetObjectTypeRecommendedFields
import com.aparokio.aparok.domain.relations.AddRelationToObject
import com.aparokio.aparok.domain.relations.AddToFeaturedRelations
import com.aparokio.aparok.domain.relations.DeleteRelationFromObject
import com.aparokio.aparok.domain.relations.RemoveFromFeaturedRelations
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.objects.LockedStateProvider
import com.aparokio.aparok.presentation.relations.ObjectRelationListViewModelFactory
import com.aparokio.aparok.presentation.relations.RelationListViewModel
import com.aparokio.aparok.presentation.relations.providers.ObjectRelationListProvider
import com.aparokio.aparok.presentation.util.Dispatcher
import com.aparokio.aparok.ui.primitives.ObjectFieldsFragment
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [ObjectRelationListModule::class])
@PerModal
interface ObjectRelationListComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun withVmParams(vmParams: RelationListViewModel.VmParams) : Builder
        fun module(module: ObjectRelationListModule): Builder
        fun build(): ObjectRelationListComponent
    }

    fun inject(fragment: ObjectFieldsFragment)
}

@Module
object ObjectRelationListModule {
    @JvmStatic
    @Provides
    @PerModal
    fun factory(
        vmParams: RelationListViewModel.VmParams,
        lockedStateProvider: LockedStateProvider,
        objectRelationListProvider: ObjectRelationListProvider,
        urlBuilder: UrlBuilder,
        dispatcher: Dispatcher<Payload>,
        updateDetail: UpdateDetail,
        addToFeaturedRelations: AddToFeaturedRelations,
        removeFromFeaturedRelations: RemoveFromFeaturedRelations,
        deleteRelationFromObject: DeleteRelationFromObject,
        analytics: Analytics,
        storeOfRelations: StoreOfRelations,
        storeOfObjectTypes: StoreOfObjectTypes,
        addRelationToObject: AddRelationToObject,
        analyticSpaceHelperDelegate: AnalyticSpaceHelperDelegate,
        fieldParser: FieldParser,
        userPermissionProvider: UserPermissionProvider,
        setObjectTypeRecommendedFields: SetObjectTypeRecommendedFields
    ): ObjectRelationListViewModelFactory {
        return ObjectRelationListViewModelFactory(
            vmParams = vmParams,
            lockedStateProvider = lockedStateProvider,
            objectRelationListProvider = objectRelationListProvider,
            urlBuilder = urlBuilder,
            dispatcher = dispatcher,
            updateDetail = updateDetail,
            addToFeaturedRelations = addToFeaturedRelations,
            removeFromFeaturedRelations = removeFromFeaturedRelations,
            deleteRelationFromObject = deleteRelationFromObject,
            analytics = analytics,
            storeOfRelations = storeOfRelations,
            storeOfObjectTypes = storeOfObjectTypes,
            addRelationToObject = addRelationToObject,
            analyticSpaceHelperDelegate = analyticSpaceHelperDelegate,
            fieldParser = fieldParser,
            userPermissionProvider = userPermissionProvider,
            setObjectTypeRecommendedFields = setObjectTypeRecommendedFields
        )
    }

    @JvmStatic
    @Provides
    @PerModal
    fun addToFeaturedRelations(repo: BlockRepository, dispatchers: AppCoroutineDispatchers): AddToFeaturedRelations =
        AddToFeaturedRelations(repo, dispatchers)

    @JvmStatic
    @Provides
    @PerModal
    fun removeFromFeaturedRelations(repo: BlockRepository, dispatchers: AppCoroutineDispatchers): RemoveFromFeaturedRelations =
        RemoveFromFeaturedRelations(repo = repo, dispatchers = dispatchers)

    @JvmStatic
    @Provides
    @PerModal
    fun deleteRelationFromObject(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): DeleteRelationFromObject =
        DeleteRelationFromObject(repo, dispatchers)

    @JvmStatic
    @Provides
    @PerModal
    fun provideTypeSetRecommendedFields(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetObjectTypeRecommendedFields = SetObjectTypeRecommendedFields(repo, dispatchers)
}