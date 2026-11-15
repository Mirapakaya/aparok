package com.aparokio.aparok.di.feature;

import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_utils.di.scope.PerModal
import com.aparokio.aparok.device.DefaultGradientCollectionProvider
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.cover.GetCoverGradientCollection
import com.aparokio.aparok.domain.cover.RemoveDocCover
import com.aparokio.aparok.domain.cover.SetDocCoverColor
import com.aparokio.aparok.domain.cover.SetDocCoverGradient
import com.aparokio.aparok.domain.cover.SetDocCoverImage
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.presentation.editor.cover.SelectCoverObjectSetViewModel
import com.aparokio.aparok.presentation.editor.cover.SelectCoverObjectViewModel
import com.aparokio.aparok.presentation.util.Dispatcher
import com.aparokio.aparok.ui.editor.cover.SelectCoverObjectFragment
import com.aparokio.aparok.ui.editor.cover.SelectCoverObjectSetFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [SelectCoverObjectModule::class])
@PerModal
interface SelectCoverObjectSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun module(module: SelectCoverObjectModule): Builder
        fun build(): SelectCoverObjectSubComponent
    }

    fun inject(fragment: SelectCoverObjectFragment)
}

@Module
object SelectCoverObjectModule {

    @JvmStatic
    @Provides
    @PerModal
    fun provideSetDocCoverColorUseCase(
        repo: BlockRepository
    ): SetDocCoverColor = SetDocCoverColor(repo)

    @JvmStatic
    @Provides
    @PerModal
    fun provideSetDocCoverGradientUseCase(
        repo: BlockRepository
    ): SetDocCoverGradient = SetDocCoverGradient(repo)

    @JvmStatic
    @Provides
    @PerModal
    fun provideRemoveDocCoverUseCase(
        repo: BlockRepository
    ): RemoveDocCover = RemoveDocCover(repo)

    @JvmStatic
    @Provides
    @PerModal
    fun provideSelectDocCoverViewModelFactory(
        setCoverImage: SetDocCoverImage,
        setCoverColor: SetDocCoverColor,
        setCoverGradient: SetDocCoverGradient,
        removeCover: RemoveDocCover,
        dispatcher: Dispatcher<Payload>,
        getCoverGradientCollection: GetCoverGradientCollection,
        analytics: Analytics,
        spaceManager: SpaceManager
    ): SelectCoverObjectViewModel.Factory = SelectCoverObjectViewModel.Factory(
        setCoverImage = setCoverImage,
        setCoverColor = setCoverColor,
        setCoverGradient = setCoverGradient,
        removeCover = removeCover,
        dispatcher = dispatcher,
        getCoverGradientCollection = getCoverGradientCollection,
        analytics = analytics,
        spaceManager = spaceManager
    )

    @JvmStatic
    @Provides
    @PerModal
    fun provideGetCoverGradientCollectionUseCase(
    ): GetCoverGradientCollection = GetCoverGradientCollection(DefaultGradientCollectionProvider)
}

@Subcomponent(modules = [SelectCoverObjectSetModule::class])
@PerModal
interface SelectCoverObjectSetSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun module(module: SelectCoverObjectSetModule): Builder
        fun build(): SelectCoverObjectSetSubComponent
    }

    fun inject(fragment: SelectCoverObjectSetFragment)
}

@Module
object SelectCoverObjectSetModule {

    @JvmStatic
    @Provides
    @PerModal
    fun provideSetDocCoverColorUseCase(
        repo: BlockRepository
    ): SetDocCoverColor = SetDocCoverColor(repo)

    @JvmStatic
    @Provides
    @PerModal
    fun provideSetDocCoverGradientUseCase(
        repo: BlockRepository
    ): SetDocCoverGradient = SetDocCoverGradient(repo)

    @JvmStatic
    @Provides
    @PerModal
    fun provideRemoveDocCoverUseCase(
        repo: BlockRepository
    ): RemoveDocCover = RemoveDocCover(repo)

    @JvmStatic
    @Provides
    @PerModal
    fun provideSelectDocCoverViewModelFactory(
        setCoverImage: SetDocCoverImage,
        setCoverColor: SetDocCoverColor,
        setCoverGradient: SetDocCoverGradient,
        removeCover: RemoveDocCover,
        dispatcher: Dispatcher<Payload>,
        getCoverGradientCollection: GetCoverGradientCollection,
        analytics: Analytics,
        spaceManager: SpaceManager
    ): SelectCoverObjectSetViewModel.Factory = SelectCoverObjectSetViewModel.Factory(
        setCoverImage = setCoverImage,
        setCoverColor = setCoverColor,
        setCoverGradient = setCoverGradient,
        removeCover = removeCover,
        dispatcher = dispatcher,
        getCoverGradientCollection = getCoverGradientCollection,
        analytics = analytics,
        spaceManager = spaceManager
    )

    @JvmStatic
    @Provides
    @PerModal
    fun provideGetCoverGradientCollectionUseCase(
    ): GetCoverGradientCollection = GetCoverGradientCollection(DefaultGradientCollectionProvider)
}