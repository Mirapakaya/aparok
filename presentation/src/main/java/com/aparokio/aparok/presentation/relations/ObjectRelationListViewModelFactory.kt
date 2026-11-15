package com.aparokio.aparok.presentation.relations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_models.UrlBuilder
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
import com.aparokio.aparok.presentation.relations.RelationListViewModel.VmParams
import com.aparokio.aparok.presentation.relations.providers.ObjectRelationListProvider
import com.aparokio.aparok.presentation.util.Dispatcher

class ObjectRelationListViewModelFactory(
    private val vmParams: VmParams,
    private val objectRelationListProvider: ObjectRelationListProvider,
    private val lockedStateProvider: LockedStateProvider,
    private val urlBuilder: UrlBuilder,
    private val dispatcher: Dispatcher<Payload>,
    private val updateDetail: UpdateDetail,
    private val addToFeaturedRelations: AddToFeaturedRelations,
    private val removeFromFeaturedRelations: RemoveFromFeaturedRelations,
    private val deleteRelationFromObject: DeleteRelationFromObject,
    private val analytics: Analytics,
    private val storeOfRelations: StoreOfRelations,
    private val storeOfObjectTypes: StoreOfObjectTypes,
    private val addRelationToObject: AddRelationToObject,
    private val analyticSpaceHelperDelegate: AnalyticSpaceHelperDelegate,
    private val fieldParser: FieldParser,
    private val userPermissionProvider: UserPermissionProvider,
    private val setObjectTypeRecommendedFields: SetObjectTypeRecommendedFields
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RelationListViewModel(
            vmParams = vmParams,
            objectRelationListProvider = objectRelationListProvider,
            lockedStateProvider = lockedStateProvider,
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
        ) as T
    }
}