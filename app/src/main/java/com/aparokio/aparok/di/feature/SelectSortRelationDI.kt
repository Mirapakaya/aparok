package com.aparokio.aparok.di.feature

import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_utils.di.scope.PerModal
import com.aparokio.aparok.domain.dataview.interactor.UpdateDataViewViewer
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.presentation.sets.SelectSortRelationViewModel
import com.aparokio.aparok.presentation.sets.state.ObjectState
import com.aparokio.aparok.presentation.util.Dispatcher
import com.aparokio.aparok.ui.sets.modals.sort.SelectSortRelationFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import kotlinx.coroutines.flow.MutableStateFlow

@Subcomponent(modules = [SelectSortRelationModule::class])
@PerModal
interface SelectSortRelationSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun module(module: SelectSortRelationModule): Builder
        fun build(): SelectSortRelationSubComponent
    }

    fun inject(fragment: SelectSortRelationFragment)
}

@Module
object SelectSortRelationModule {
    @JvmStatic
    @Provides
    @PerModal
    fun provideSelectSortRelationViewModelFactory(
        state: MutableStateFlow<ObjectState>,
        dispatcher: Dispatcher<Payload>,
        updateDataViewViewer: UpdateDataViewViewer,
        storeOfRelations: StoreOfRelations,
        analytics: Analytics
    ): SelectSortRelationViewModel.Factory = SelectSortRelationViewModel.Factory(
        objectState = state,
        dispatcher = dispatcher,
        updateDataViewViewer = updateDataViewViewer,
        storeOfRelations = storeOfRelations,
        analytics = analytics
    )
}