package com.aparokio.aparok.presentation.relations.option

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.`object`.SetObjectDetails
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.domain.relations.CreateRelationOption
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.relations.providers.ObjectValueProvider
import com.aparokio.aparok.presentation.util.Dispatcher
import javax.inject.Inject

class CreateOrEditOptionViewModelFactory  @Inject constructor(
    private val params: CreateOrEditOptionViewModel.ViewModelParams,
    private val values: ObjectValueProvider,
    private val createOption: CreateRelationOption,
    private val setObjectDetails: SetObjectDetails,
    private val dispatcher: Dispatcher<Payload>,
    private val spaceManager: SpaceManager,
    private val analytics: Analytics,
    private val storeOfRelations: StoreOfRelations,
    private val analyticSpaceHelperDelegate: AnalyticSpaceHelperDelegate
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ) = CreateOrEditOptionViewModel(
        viewModelParams = params,
        values = values,
        createOption = createOption,
        setObjectDetails = setObjectDetails,
        dispatcher = dispatcher,
        spaceManager = spaceManager,
        analytics = analytics,
        storeOfRelations = storeOfRelations,
        analyticSpaceHelperDelegate = analyticSpaceHelperDelegate
    ) as T
}