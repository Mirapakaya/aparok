package com.aparokio.aparok.di.feature.sets

import com.aparokio.aparok.core_utils.di.scope.PerDialog
import com.aparokio.aparok.presentation.sets.filter.PickFilterConditionViewModel
import com.aparokio.aparok.ui.sets.modals.PickFilterConditionFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [PickConditionModule::class])
@PerDialog
interface PickFilterConditionSubComponent {

    @Subcomponent.Builder
    interface Builder {

        fun module(module: PickConditionModule): Builder
        fun build(): PickFilterConditionSubComponent
    }

    fun inject(fragment: PickFilterConditionFragment)

}

@Module
object PickConditionModule {

    @JvmStatic
    @Provides
    @PerDialog
    fun provideFactory(): PickFilterConditionViewModel.Factory =
        PickFilterConditionViewModel.Factory()
}