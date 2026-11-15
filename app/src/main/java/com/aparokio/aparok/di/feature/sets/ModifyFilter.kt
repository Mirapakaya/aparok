package com.aparokio.aparok.di.feature.sets;

import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_utils.di.scope.PerModal
import com.aparokio.aparok.domain.dataview.interactor.UpdateDataViewViewer
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.domain.objects.options.GetOptions
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.domain.search.SearchObjects
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.sets.ObjectSetDatabase
import com.aparokio.aparok.presentation.sets.filter.FilterViewModel
import com.aparokio.aparok.presentation.sets.state.ObjectState
import com.aparokio.aparok.presentation.util.Dispatcher
import com.aparokio.aparok.ui.sets.modals.filter.ModifyFilterFromInputFieldValueFragment
import com.aparokio.aparok.ui.sets.modals.filter.ModifyFilterFromSelectedValueFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import kotlinx.coroutines.flow.MutableStateFlow

@Subcomponent(modules = [ModifyFilterModule::class])
@PerModal
interface ModifyFilterSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun module(module: ModifyFilterModule): Builder
        fun build(): ModifyFilterSubComponent
    }

    fun inject(fragment: ModifyFilterFromInputFieldValueFragment)
    fun inject(fragment: ModifyFilterFromSelectedValueFragment)
    fun createPickConditionComponent(): PickFilterConditionSubComponent.Builder
}

@Module
object ModifyFilterModule {

    @JvmStatic
    @Provides
    @PerModal
    fun provideViewModelFactory(
        state: MutableStateFlow<ObjectState>,
        dispatcher: Dispatcher<Payload>,
        updateDataViewViewer: UpdateDataViewViewer,
        searchObjects: SearchObjects,
        urlBuilder: UrlBuilder,
        storeOfObjectTypes: StoreOfObjectTypes,
        analytics: Analytics,
        objectSetDatabase: ObjectSetDatabase,
        getOptions: GetOptions,
        storeOfRelations: StoreOfRelations,
        spaceManager: SpaceManager,
        fieldParser: FieldParser,
        spaceViews: SpaceViewSubscriptionContainer,
        analyticSpaceHelperDelegate: AnalyticSpaceHelperDelegate
    ): FilterViewModel.Factory = FilterViewModel.Factory(
        objectState = state,
        dispatcher = dispatcher,
        updateDataViewViewer = updateDataViewViewer,
        searchObjects = searchObjects,
        urlBuilder = urlBuilder,
        storeOfObjectTypes = storeOfObjectTypes,
        objectSetDatabase = objectSetDatabase,
        storeOfRelations = storeOfRelations,
        analytics = analytics,
        getOptions = getOptions,
        spaceManager = spaceManager,
        fieldParser = fieldParser,
        spaceViews = spaceViews,
        analyticSpaceHelperDelegate = analyticSpaceHelperDelegate
    )
}
