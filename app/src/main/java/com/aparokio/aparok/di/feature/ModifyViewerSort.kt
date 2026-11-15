package com.aparokio.aparok.di.feature;

import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_utils.di.scope.PerModal
import com.aparokio.aparok.domain.dataview.interactor.UpdateDataViewViewer
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.presentation.sets.sort.ModifyViewerSortViewModel
import com.aparokio.aparok.presentation.sets.state.ObjectState
import com.aparokio.aparok.presentation.util.Dispatcher
import com.aparokio.aparok.ui.sets.modals.sort.ModifyViewerSortFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import kotlinx.coroutines.flow.MutableStateFlow

@Subcomponent(modules = [ModifyViewerSortModule::class])
@PerModal
interface ModifyViewerSortSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun module(module: ModifyViewerSortModule): Builder
        fun build(): ModifyViewerSortSubComponent
    }

    fun inject(fragment: ModifyViewerSortFragment)
}

@Module
object ModifyViewerSortModule {
    @JvmStatic
    @Provides
    @PerModal
    fun provideViewModelFactory(
        state: MutableStateFlow<ObjectState>,
        dispatcher: Dispatcher<Payload>,
        updateDataViewViewer: UpdateDataViewViewer,
        analytics: Analytics,
        storeOfRelations: StoreOfRelations
    ): ModifyViewerSortViewModel.Factory = ModifyViewerSortViewModel.Factory(
        state = state,
        dispatcher = dispatcher,
        updateDataViewViewer = updateDataViewViewer,
        analytics = analytics,
        storeOfRelations = storeOfRelations
    )
}
