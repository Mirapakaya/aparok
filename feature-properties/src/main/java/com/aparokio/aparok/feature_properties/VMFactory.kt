package com.aparokio.aparok.feature_properties

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.dataview.SetDataViewProperties
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.`object`.SetObjectDetails
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.domain.primitives.SetObjectTypeRecommendedFields
import com.aparokio.aparok.domain.relations.CreateRelation
import com.aparokio.aparok.domain.resources.StringResourceProvider
import com.aparokio.aparok.feature_properties.add.EditTypePropertiesVmParams
import com.aparokio.aparok.presentation.util.Dispatcher
import javax.inject.Inject

class EditTypePropertiesViewModelFactory @Inject constructor(
    private val vmParams: EditTypePropertiesVmParams,
    private val storeOfRelations: StoreOfRelations,
    private val stringResourceProvider: StringResourceProvider,
    private val createRelation: CreateRelation,
    private val setObjectDetails: SetObjectDetails,
    private val storeOfObjectTypes: StoreOfObjectTypes,
    private val setObjectTypeRecommendedFields: SetObjectTypeRecommendedFields,
    private val setDataViewProperties: SetDataViewProperties,
    private val dispatcher: Dispatcher<Payload>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        EditTypePropertiesViewModel(
            vmParams = vmParams,
            storeOfRelations = storeOfRelations,
            stringResourceProvider = stringResourceProvider,
            createRelation = createRelation,
            setObjectDetails = setObjectDetails,
            storeOfObjectTypes = storeOfObjectTypes,
            setObjectTypeRecommendedFields = setObjectTypeRecommendedFields,
            setDataViewProperties = setDataViewProperties,
            dispatcher = dispatcher
        ) as T
}