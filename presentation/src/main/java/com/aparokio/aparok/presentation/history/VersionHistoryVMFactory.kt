package com.aparokio.aparok.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.domain.history.GetVersions
import com.aparokio.aparok.domain.history.SetVersion
import com.aparokio.aparok.domain.history.ShowVersion
import com.aparokio.aparok.domain.misc.DateProvider
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.domain.search.SearchObjects
import com.aparokio.aparok.presentation.editor.render.DefaultBlockViewRenderer
import com.aparokio.aparok.presentation.history.VersionHistoryViewModel.VmParams
import com.aparokio.aparok.presentation.sets.state.ObjectStateReducer
import javax.inject.Inject

class VersionHistoryVMFactory @Inject constructor(
    private val vmParams: VmParams,
    private val getVersions: GetVersions,
    private val objectSearch: SearchObjects,
    private val dateProvider: DateProvider,
    private val analytics: Analytics,
    private val urlBuilder: UrlBuilder,
    private val showVersion: ShowVersion,
    private val setVersion: SetVersion,
    private val renderer: DefaultBlockViewRenderer,
    private val setStateReducer: ObjectStateReducer,
    private val storeOfRelations: StoreOfRelations,
    private val storeOfObjectTypes: StoreOfObjectTypes
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VersionHistoryViewModel(
            vmParams = vmParams,
            getVersions = getVersions,
            objectSearch = objectSearch,
            dateProvider = dateProvider,
            analytics = analytics,
            urlBuilder = urlBuilder,
            showVersion = showVersion,
            renderer = renderer,
            setVersion = setVersion,
            setStateReducer = setStateReducer,
            storeOfRelations = storeOfRelations,
            storeOfObjectTypes = storeOfObjectTypes
        ) as T
    }
}