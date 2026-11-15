package com.aparokio.aparok.di.feature.cover

import com.aparokio.aparok.core_utils.di.scope.PerDialog
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.cover.SetDocCoverImage
import com.aparokio.aparok.domain.unsplash.SearchUnsplashImage
import com.aparokio.aparok.domain.unsplash.UnsplashRepository
import com.aparokio.aparok.presentation.common.Action
import com.aparokio.aparok.presentation.common.Delegator
import com.aparokio.aparok.presentation.editor.cover.UnsplashViewModel
import com.aparokio.aparok.ui.editor.cover.UnsplashBaseFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [UnsplashModule::class])
@PerDialog
interface UnsplashSubComponent {

    @Subcomponent.Builder
    interface Builder {
        fun module(module: UnsplashModule): Builder
        fun build(): UnsplashSubComponent
    }

    fun inject(fragment: UnsplashBaseFragment)
}

@Module
object UnsplashModule {

    @JvmStatic
    @Provides
    @PerDialog
    fun provideViewModelFactory(
        search: SearchUnsplashImage,
        delegator: Delegator<Action>
    ): UnsplashViewModel.Factory {
        return UnsplashViewModel.Factory(
            search = search,
            delegator = delegator
        )
    }

    @JvmStatic
    @Provides
    @PerDialog
    fun provideSearch(repo: UnsplashRepository): SearchUnsplashImage = SearchUnsplashImage(
        repo = repo
    )

    @JvmStatic
    @Provides
    @PerDialog
    fun provideSetDocCoverImageUseCase(
        repo: BlockRepository
    ): SetDocCoverImage = SetDocCoverImage(repo)
}