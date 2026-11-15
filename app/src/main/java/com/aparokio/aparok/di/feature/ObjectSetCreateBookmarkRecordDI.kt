package com.aparokio.aparok.di.feature;

import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_utils.di.scope.PerDialog
import com.aparokio.aparok.core_utils.tools.UrlValidator
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.collections.AddObjectToCollection
import com.aparokio.aparok.domain.misc.DateProvider
import com.aparokio.aparok.domain.objects.CreateBookmarkObject
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.presentation.sets.ObjectSetCreateBookmarkRecordViewModel
import com.aparokio.aparok.presentation.sets.ObjectSetSession
import com.aparokio.aparok.presentation.sets.state.ObjectState
import com.aparokio.aparok.presentation.util.Dispatcher
import com.aparokio.aparok.ui.sets.modals.SetObjectCreateBookmarkRecordFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import kotlinx.coroutines.flow.MutableStateFlow

@Subcomponent(modules = [ObjectSetCreateBookmarkRecordModule::class])
@PerDialog
interface ObjectSetCreateBookmarkRecordSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun module(module: ObjectSetCreateBookmarkRecordModule): Builder
        fun build(): ObjectSetCreateBookmarkRecordSubComponent
    }

    fun inject(fragment: SetObjectCreateBookmarkRecordFragment)
}

@Module
object ObjectSetCreateBookmarkRecordModule {

    @JvmStatic
    @Provides
    @PerDialog
    fun provideObjectSetRecordViewModelFactory(
        createBookmarkObject: CreateBookmarkObject,
        urlValidator: UrlValidator,
        spaceManager: SpaceManager,
        objectState: MutableStateFlow<ObjectState>,
        dispatcher: Dispatcher<Payload>,
        addObjectToCollection: AddObjectToCollection,
        session: ObjectSetSession,
        storeOfRelations: StoreOfRelations,
        dateProvider: DateProvider
    ) = ObjectSetCreateBookmarkRecordViewModel.Factory(
        createBookmarkObject = createBookmarkObject,
        urlValidator = urlValidator,
        spaceManager = spaceManager,
        objectState = objectState,
        dispatcher = dispatcher,
        addObjectToCollection = addObjectToCollection,
        session = session,
        storeOfRelations = storeOfRelations,
        dateProvider = dateProvider
    )

    @JvmStatic
    @Provides
    @PerDialog
    fun provideCreateBookmarkObjectUseCase(
        repo: BlockRepository
    ): CreateBookmarkObject = CreateBookmarkObject(
        repo = repo
    )
}