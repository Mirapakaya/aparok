package com.aparokio.aparok.presentation.relations.value.tagstatus

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.`object`.UpdateDetail
import com.aparokio.aparok.domain.objects.StoreOfRelationOptions
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.domain.relations.DeleteRelationOptions
import com.aparokio.aparok.domain.relations.SetRelationOptionOrder
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.relations.providers.ObjectValueProvider
import com.aparokio.aparok.presentation.util.Dispatcher
import javax.inject.Inject

class TagOrStatusValueViewModelFactory @Inject constructor(
    private val params: TagOrStatusValueViewModel.ViewModelParams,
    private val values: ObjectValueProvider,
    private val dispatcher: Dispatcher<Payload>,
    private val setObjectDetails: UpdateDetail,
    private val analytics: Analytics,
    private val deleteRelationOptions: DeleteRelationOptions,
    private val setRelationOptionOrder: SetRelationOptionOrder,
    private val analyticSpaceHelperDelegate: AnalyticSpaceHelperDelegate,
    private val storeOfRelations: StoreOfRelations,
    private val storeOfRelationOptions: StoreOfRelationOptions
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ) = TagOrStatusValueViewModel(
        viewModelParams = params,
        values = values,
        dispatcher = dispatcher,
        setObjectDetails = setObjectDetails,
        analytics = analytics,
        deleteRelationOptions = deleteRelationOptions,
        setRelationOptionOrder = setRelationOptionOrder,
        analyticSpaceHelperDelegate = analyticSpaceHelperDelegate,
        storeOfRelations = storeOfRelations,
        storeOfRelationOptions = storeOfRelationOptions
    ) as T
}