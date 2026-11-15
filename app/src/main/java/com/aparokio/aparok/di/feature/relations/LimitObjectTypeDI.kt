package com.aparokio.aparok.di.feature.relations

import com.aparokio.aparok.core_utils.di.scope.PerDialog
import com.aparokio.aparok.domain.search.SearchObjects
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.presentation.relations.LimitObjectTypeViewModel
import com.aparokio.aparok.presentation.relations.model.CreateFromScratchState
import com.aparokio.aparok.presentation.relations.model.StateHolder
import com.aparokio.aparok.ui.relations.LimitObjectTypeFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [LimitObjectTypeModule::class])
@PerDialog
interface LimitObjectTypeSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun module(module: LimitObjectTypeModule): Builder
        fun build(): LimitObjectTypeSubComponent
    }

    fun inject(fragment: LimitObjectTypeFragment)
}

@Module
object LimitObjectTypeModule {

    @JvmStatic
    @Provides
    @PerDialog
    fun provideViewModelFactory(
        searchObjects: SearchObjects,
        urlBuilder: UrlBuilder,
        state: StateHolder<CreateFromScratchState>,
        spaceManager: SpaceManager
    ): LimitObjectTypeViewModel.Factory = LimitObjectTypeViewModel.Factory(
        searchObjects = searchObjects,
        urlBuilder = urlBuilder,
        state = state,
        spaceManager = spaceManager
    )
}