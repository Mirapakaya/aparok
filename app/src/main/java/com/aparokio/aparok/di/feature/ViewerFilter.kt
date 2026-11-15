package com.aparokio.aparok.di.feature;

import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.dataview.interactor.UpdateDataViewViewer
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.presentation.sets.ObjectSetDatabase
import com.aparokio.aparok.presentation.sets.filter.ViewerFilterViewModel
import com.aparokio.aparok.presentation.sets.state.ObjectState
import com.aparokio.aparok.presentation.util.Dispatcher
import com.aparokio.aparok.ui.sets.ViewerFilterFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import javax.inject.Scope
import kotlinx.coroutines.flow.MutableStateFlow

@Subcomponent(modules = [ViewerFilterModule::class])
@ViewerFilterByScope
interface ViewerFilterSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun module(module: ViewerFilterModule): Builder
        fun build(): ViewerFilterSubComponent
    }

    fun inject(fragment: ViewerFilterFragment)
}

@Module
object ViewerFilterModule {

    @JvmStatic
    @Provides
    @ViewerFilterByScope
    fun provideViewerFilterViewModelFactory(
        state: MutableStateFlow<ObjectState>,
        dispatcher: Dispatcher<Payload>,
        updateDataViewViewer: UpdateDataViewViewer,
        urlBuilder: UrlBuilder,
        analytics: Analytics,
        storeOfRelations: StoreOfRelations,
        db: ObjectSetDatabase,
        fieldParser: FieldParser,
        storeOfObjectTypes: StoreOfObjectTypes
    ): ViewerFilterViewModel.Factory = ViewerFilterViewModel.Factory(
        state = state,
        dispatcher = dispatcher,
        updateDataViewViewer = updateDataViewViewer,
        urlBuilder = urlBuilder,
        analytics = analytics,
        storeOfRelations = storeOfRelations,
        objectSetDatabase = db,
        fieldParser = fieldParser,
        storeOfObjectTypes = storeOfObjectTypes
    )
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewerFilterByScope