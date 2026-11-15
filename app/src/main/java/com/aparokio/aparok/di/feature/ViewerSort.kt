package com.aparokio.aparok.di.feature;

import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_utils.di.scope.PerModal
import com.aparokio.aparok.domain.dataview.interactor.UpdateDataViewViewer
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.presentation.sets.sort.ViewerSortViewModel
import com.aparokio.aparok.presentation.sets.state.ObjectState
import com.aparokio.aparok.presentation.util.Dispatcher
import com.aparokio.aparok.ui.sets.modals.sort.ViewerSortFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import kotlinx.coroutines.flow.MutableStateFlow

@Subcomponent(modules = [ViewerSortModule::class])
@PerModal
interface ViewerSortSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun module(module: ViewerSortModule): Builder
        fun build(): ViewerSortSubComponent
    }

    fun inject(fragment: ViewerSortFragment)
}

@Module
object ViewerSortModule {
    @JvmStatic
    @Provides
    @PerModal
    fun provideViewModelFactory(
        state: MutableStateFlow<ObjectState>,
        updateDataViewViewer: UpdateDataViewViewer,
        dispatcher: Dispatcher<Payload>,
        analytics: Analytics,
        storeOfRelations: StoreOfRelations
    ): ViewerSortViewModel.Factory = ViewerSortViewModel.Factory(
        state = state,
        updateDataViewViewer = updateDataViewViewer,
        dispatcher = dispatcher,
        analytics = analytics,
        storeOfRelations = storeOfRelations
    )
}
