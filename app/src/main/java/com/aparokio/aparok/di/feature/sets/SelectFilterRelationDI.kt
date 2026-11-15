package com.aparokio.aparok.di.feature.sets

import com.aparokio.aparok.core_utils.di.scope.PerModal
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.presentation.sets.SelectFilterRelationViewModel
import com.aparokio.aparok.presentation.sets.state.ObjectState
import com.aparokio.aparok.ui.sets.modals.filter.SelectFilterRelationFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import kotlinx.coroutines.flow.MutableStateFlow

@Subcomponent(modules = [SelectFilterRelationModule::class])
@PerModal
interface SelectFilterRelationSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun module(module: SelectFilterRelationModule): Builder
        fun build(): SelectFilterRelationSubComponent
    }

    fun inject(fragment: SelectFilterRelationFragment)
}

@Module
object SelectFilterRelationModule {
    @JvmStatic
    @Provides
    @PerModal
    fun provideSelectSortRelationViewModelFactory(
        state: MutableStateFlow<ObjectState>,
        storeOfRelations: StoreOfRelations
    ): SelectFilterRelationViewModel.Factory = SelectFilterRelationViewModel.Factory(
        objectState = state,
        storeOfRelations = storeOfRelations
    )
}