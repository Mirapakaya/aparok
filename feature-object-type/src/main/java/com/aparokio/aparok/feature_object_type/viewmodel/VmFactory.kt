package com.aparokio.aparok.feature_object_type.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.block.interactor.UpdateText
import com.aparokio.aparok.domain.dataview.SetDataViewProperties
import com.aparokio.aparok.domain.event.interactor.SpaceSyncAndP2PStatusProvider
import com.aparokio.aparok.domain.library.StorelessSubscriptionContainer
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.`object`.DuplicateObjects
import com.aparokio.aparok.domain.`object`.GetObject
import com.aparokio.aparok.domain.`object`.SetObjectDetails
import com.aparokio.aparok.domain.objects.DeleteObjects
import com.aparokio.aparok.domain.objects.SetObjectListIsArchived
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.domain.primitives.GetObjectTypeConflictingFields
import com.aparokio.aparok.domain.primitives.SetObjectTypeRecommendedFields
import com.aparokio.aparok.domain.relations.AddToFeaturedRelations
import com.aparokio.aparok.domain.relations.RemoveFromFeaturedRelations
import com.aparokio.aparok.domain.resources.StringResourceProvider
import com.aparokio.aparok.domain.search.SearchObjects
import com.aparokio.aparok.domain.templates.CreateTemplate
import com.aparokio.aparok.domain.widgets.CreateWidget
import com.aparokio.aparok.domain.widgets.DeleteWidget
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.feature_object_type.ui.ObjectTypeVmParams
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.navigation.backstack.BackHistoryDelegate
import com.aparokio.aparok.presentation.vault.ExitToVaultDelegate
import com.aparokio.aparok.presentation.editor.cover.CoverImageHashProvider
import com.aparokio.aparok.presentation.util.Dispatcher
import javax.inject.Inject

class ObjectTypeVMFactory @Inject constructor(
    private val vmParams: ObjectTypeVmParams,
    private val analytics: Analytics,
    private val urlBuilder: UrlBuilder,
    private val analyticSpaceHelperDelegate: AnalyticSpaceHelperDelegate,
    private val userPermissionProvider: UserPermissionProvider,
    private val storeOfRelations: StoreOfRelations,
    private val storeOfObjectTypes: StoreOfObjectTypes,
    private val storelessSubscriptionContainer: StorelessSubscriptionContainer,
    private val spaceSyncAndP2PStatusProvider: SpaceSyncAndP2PStatusProvider,
    private val fieldParser: FieldParser,
    private val coverImageHashProvider: CoverImageHashProvider,
    private val deleteObjects: DeleteObjects,
    private val setObjectDetails: SetObjectDetails,
    private val stringResourceProvider: StringResourceProvider,
    private val createTemplate: CreateTemplate,
    private val duplicateObjects: DuplicateObjects,
    private val getObjectTypeConflictingFields: GetObjectTypeConflictingFields,
    private val objectTypeSetRecommendedFields: SetObjectTypeRecommendedFields,
    private val setDataViewProperties: SetDataViewProperties,
    private val dispatcher: Dispatcher<Payload>,
    private val setObjectListIsArchived: SetObjectListIsArchived,
    private val createWidget: CreateWidget,
    private val deleteWidget: DeleteWidget,
    private val spaceManager: SpaceManager,
    private val getObject: GetObject,
    private val addToFeaturedRelations: AddToFeaturedRelations,
    private val removeFromFeaturedRelations: RemoveFromFeaturedRelations,
    private val updateText: UpdateText,
    private val searchObjects: SearchObjects,
    private val backHistoryDelegate: BackHistoryDelegate,
    private val exitToVaultDelegate: ExitToVaultDelegate
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        ObjectTypeViewModel(
            vmParams = vmParams,
            analytics = analytics,
            urlBuilder = urlBuilder,
            analyticSpaceHelperDelegate = analyticSpaceHelperDelegate,
            userPermissionProvider = userPermissionProvider,
            storeOfRelations = storeOfRelations,
            storeOfObjectTypes = storeOfObjectTypes,
            storelessSubscriptionContainer = storelessSubscriptionContainer,
            spaceSyncAndP2PStatusProvider = spaceSyncAndP2PStatusProvider,
            fieldParser = fieldParser,
            coverImageHashProvider = coverImageHashProvider,
            deleteObjects = deleteObjects,
            setObjectDetails = setObjectDetails,
            stringResourceProvider = stringResourceProvider,
            createTemplate = createTemplate,
            duplicateObjects = duplicateObjects,
            getObjectTypeConflictingFields = getObjectTypeConflictingFields,
            objectTypeSetRecommendedFields = objectTypeSetRecommendedFields,
            setDataViewProperties = setDataViewProperties,
            dispatcher = dispatcher,
            setObjectListIsArchived = setObjectListIsArchived,
            createWidget = createWidget,
            deleteWidget = deleteWidget,
            spaceManager = spaceManager,
            getObject = getObject,
            addToFeaturedRelations = addToFeaturedRelations,
            removeFromFeaturedRelations = removeFromFeaturedRelations,
            updateText = updateText,
            searchObjects = searchObjects,
            backHistoryDelegate = backHistoryDelegate,
            exitToVaultDelegate = exitToVaultDelegate
        ) as T
}