package com.aparokio.aparok.di.feature

import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_utils.di.scope.PerModal
import com.aparokio.aparok.domain.dataview.interactor.UpdateDataViewViewer
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.domain.relations.DeleteRelationFromDataView
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.relations.ObjectSetSettingsViewModel
import com.aparokio.aparok.presentation.sets.state.ObjectState
import com.aparokio.aparok.presentation.util.Dispatcher
import com.aparokio.aparok.ui.sets.modals.ObjectSetSettingsFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import kotlinx.coroutines.flow.MutableStateFlow

@Subcomponent(modules = [ObjectSetSettingsModule::class])
@PerModal
interface ObjectSetSettingsSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun module(module: ObjectSetSettingsModule): Builder
        fun build(): ObjectSetSettingsSubComponent
    }

    fun inject(fragment: ObjectSetSettingsFragment)
}

@Module
object ObjectSetSettingsModule {

    @JvmStatic
    @Provides
    @PerModal
    fun provideViewModelFactory(
        state: MutableStateFlow<ObjectState>,
        dispatcher: Dispatcher<Payload>,
        updateDataViewViewer: UpdateDataViewViewer,
        store: StoreOfRelations,
        analytics: Analytics,
        deleteRelationFromDataView: DeleteRelationFromDataView,
        analyticSpaceHelperDelegate: AnalyticSpaceHelperDelegate,
        spaceManager: SpaceManager
    ): ObjectSetSettingsViewModel.Factory = ObjectSetSettingsViewModel.Factory(
        objectState = state,
        dispatcher = dispatcher,
        updateDataViewViewer = updateDataViewViewer,
        analytics = analytics,
        store = store,
        deleteRelationFromDataView = deleteRelationFromDataView,
        analyticSpaceHelperDelegate = analyticSpaceHelperDelegate,
        spaceManager = spaceManager
    )
}