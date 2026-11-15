package com.aparokio.aparok.feature_allcontent.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.domain.all_content.RestoreAllContentState
import com.aparokio.aparok.domain.all_content.UpdateAllContentState
import com.aparokio.aparok.domain.event.interactor.SpaceSyncAndP2PStatusProvider
import com.aparokio.aparok.domain.library.StorelessSubscriptionContainer
import com.aparokio.aparok.domain.misc.LocaleProvider
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.objects.SetObjectListIsArchived
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.page.CreateObject
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.domain.search.SearchObjects
import com.aparokio.aparok.domain.workspace.RemoveObjectsFromWorkspace
import com.aparokio.aparok.feature_allcontent.presentation.AllContentViewModel.VmParams
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import javax.inject.Inject

class AllContentViewModelFactory @Inject constructor(
    private val vmParams: VmParams,
    private val storeOfObjectTypes: StoreOfObjectTypes,
    private val urlBuilder: UrlBuilder,
    private val analytics: Analytics,
    private val analyticSpaceHelperDelegate: AnalyticSpaceHelperDelegate,
    private val storelessSubscriptionContainer: StorelessSubscriptionContainer,
    private val updateAllContentState: UpdateAllContentState,
    private val restoreAllContentState: RestoreAllContentState,
    private val searchObjects: SearchObjects,
    private val localeProvider: LocaleProvider,
    private val createObject: CreateObject,
    private val setObjectListIsArchived: SetObjectListIsArchived,
    private val removeObjectsFromWorkspace: RemoveObjectsFromWorkspace,
    private val userPermissionProvider: UserPermissionProvider,
    private val fieldParser: FieldParser,
    private val spaceViews: SpaceViewSubscriptionContainer,
    private val spaceSyncAndP2PStatusProvider: SpaceSyncAndP2PStatusProvider
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        AllContentViewModel(
            vmParams = vmParams,
            storeOfObjectTypes = storeOfObjectTypes,
            urlBuilder = urlBuilder,
            analytics = analytics,
            analyticSpaceHelperDelegate = analyticSpaceHelperDelegate,
            storelessSubscriptionContainer = storelessSubscriptionContainer,
            restoreAllContentState = restoreAllContentState,
            updateAllContentState = updateAllContentState,
            searchObjects = searchObjects,
            localeProvider = localeProvider,
            createObject = createObject,
            setObjectListIsArchived = setObjectListIsArchived,
            removeObjectsFromWorkspace = removeObjectsFromWorkspace,
            userPermissionProvider = userPermissionProvider,
            fieldParser = fieldParser,
            spaceViews = spaceViews,
            spaceSyncAndP2PStatusProvider = spaceSyncAndP2PStatusProvider
        ) as T
}