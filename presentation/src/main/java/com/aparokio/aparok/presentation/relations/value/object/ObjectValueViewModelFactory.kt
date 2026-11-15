package com.aparokio.aparok.presentation.relations.value.`object`

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.`object`.DuplicateObject
import com.aparokio.aparok.domain.`object`.UpdateDetail
import com.aparokio.aparok.domain.objects.SetObjectListIsArchived
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.domain.search.SearchObjects
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.relations.providers.ObjectValueProvider
import com.aparokio.aparok.presentation.util.Dispatcher
import javax.inject.Inject

class ObjectValueViewModelFactory @Inject constructor(
    private val params: ObjectValueViewModel.ViewModelParams,
    private val values: ObjectValueProvider,
    private val dispatcher: Dispatcher<Payload>,
    private val setObjectDetails: UpdateDetail,
    private val analytics: Analytics,
    private val spaceManager: SpaceManager,
    private val objectSearch: SearchObjects,
    private val urlBuilder: UrlBuilder,
    private val storeOfObjectTypes: StoreOfObjectTypes,
    private val objectListIsArchived: SetObjectListIsArchived,
    private val duplicateObject: DuplicateObject,
    private val analyticSpaceHelperDelegate: AnalyticSpaceHelperDelegate,
    private val storeOfRelations: StoreOfRelations,
    private val fieldParser: FieldParser,
    private val spaceViews: SpaceViewSubscriptionContainer
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ) = ObjectValueViewModel(
        viewModelParams = params,
        values = values,
        dispatcher = dispatcher,
        setObjectDetails = setObjectDetails,
        analytics = analytics,
        spaceManager = spaceManager,
        objectSearch = objectSearch,
        urlBuilder = urlBuilder,
        storeOfObjectTypes = storeOfObjectTypes,
        objectListIsArchived = objectListIsArchived,
        duplicateObject = duplicateObject,
        analyticSpaceHelperDelegate = analyticSpaceHelperDelegate,
        storeOfRelations = storeOfRelations,
        fieldParser = fieldParser,
        spaceViews = spaceViews
    ) as T
}