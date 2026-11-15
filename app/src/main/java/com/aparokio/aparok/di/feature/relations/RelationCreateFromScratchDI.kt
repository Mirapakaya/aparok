package com.aparokio.aparok.di.feature.relations

import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_models.RelationFormat
import com.aparokio.aparok.core_utils.di.scope.CreateFromScratch
import com.aparokio.aparok.core_utils.di.scope.PerDialog
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.dataview.interactor.AddRelationToDataView
import com.aparokio.aparok.domain.dataview.interactor.UpdateDataViewViewer
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.domain.relations.AddRelationToObject
import com.aparokio.aparok.domain.relations.CreateRelation
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.relations.RelationCreateFromScratchForDataViewViewModel
import com.aparokio.aparok.presentation.relations.RelationCreateFromScratchForObjectBlockViewModel
import com.aparokio.aparok.presentation.relations.RelationCreateFromScratchForObjectViewModel
import com.aparokio.aparok.presentation.relations.model.CreateFromScratchState
import com.aparokio.aparok.presentation.relations.model.StateHolder
import com.aparokio.aparok.presentation.sets.state.ObjectState
import com.aparokio.aparok.presentation.util.Dispatcher
import com.aparokio.aparok.ui.relations.RelationCreateFromScratchForDataViewFragment
import com.aparokio.aparok.ui.relations.RelationCreateFromScratchForObjectBlockFragment
import com.aparokio.aparok.ui.relations.RelationCreateFromScratchForObjectFragment
import com.aparokio.aparok.ui.relations.RelationCreateFromScratchFormatPickerFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import kotlinx.coroutines.flow.MutableStateFlow

@Subcomponent(modules = [RelationCreateFromScratchForObjectModule::class])
@CreateFromScratch
interface RelationCreateFromScratchForObjectSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun module(module: RelationCreateFromScratchForObjectModule): Builder
        fun build(): RelationCreateFromScratchForObjectSubComponent
    }

    fun inject(fragment: RelationCreateFromScratchForObjectFragment)

    fun relationFormatPickerComponent(): RelationFormatPickerSubcomponent.Builder
    fun limitObjectTypeComponent(): LimitObjectTypeSubComponent.Builder
}

@Module
object RelationCreateFromScratchForObjectModule {

    @JvmStatic
    @Provides
    @CreateFromScratch
    fun provideViewModelFactory(
        addRelationToObject: AddRelationToObject,
        dispatcher: Dispatcher<Payload>,
        analytics: Analytics,
        createFromScratchState: StateHolder<CreateFromScratchState>,
        createRelation: CreateRelation,
        spaceManager: SpaceManager,
        analyticSpaceHelperDelegate: AnalyticSpaceHelperDelegate,
        storeOfRelations: StoreOfRelations
    ) = RelationCreateFromScratchForObjectViewModel.Factory(
        addRelationToObject = addRelationToObject,
        createRelation = createRelation,
        dispatcher = dispatcher,
        analytics = analytics,
        createFromScratchState = createFromScratchState,
        spaceManager = spaceManager,
        analyticSpaceHelperDelegate = analyticSpaceHelperDelegate,
        storeOfRelations = storeOfRelations
    )

    @JvmStatic
    @Provides
    @CreateFromScratch
    fun createRelation(
        repo: BlockRepository,
        storeOfRelations: StoreOfRelations
    ) = CreateRelation(
        repo = repo,
        storeOfRelations = storeOfRelations
    )

    @JvmStatic
    @Provides
    @CreateFromScratch
    fun provideState(): StateHolder<CreateFromScratchState> = StateHolder(
        initial = CreateFromScratchState(
            format = RelationFormat.OBJECT,
            limitObjectTypes = emptyList()
        )
    )
}

@Subcomponent(modules = [RelationCreateFromScratchForDataViewModule::class])
@CreateFromScratch
interface RelationCreateFromScratchForDataViewSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun module(module: RelationCreateFromScratchForDataViewModule): Builder
        fun build(): RelationCreateFromScratchForDataViewSubComponent
    }

    fun inject(fragment: RelationCreateFromScratchForDataViewFragment)

    fun relationFormatPickerComponent(): RelationFormatPickerSubcomponent.Builder
    fun limitObjectTypeComponent(): LimitObjectTypeSubComponent.Builder
}

@Module
object RelationCreateFromScratchForDataViewModule {

    @JvmStatic
    @Provides
    @CreateFromScratch
    fun provideViewModelFactory(
        state: MutableStateFlow<ObjectState>,
        updateDataViewViewer: UpdateDataViewViewer,
        dispatcher: Dispatcher<Payload>,
        analytics: Analytics,
        createFromScratchState: StateHolder<CreateFromScratchState>,
        createRelation: CreateRelation,
        addRelationToDataView: AddRelationToDataView,
        spaceManager: SpaceManager,
        analyticSpaceHelperDelegate: AnalyticSpaceHelperDelegate,
        storeOfRelations: StoreOfRelations
    ) = RelationCreateFromScratchForDataViewViewModel.Factory(
        addRelationToDataView = addRelationToDataView,
        dispatcher = dispatcher,
        objectState = state,
        updateDataViewViewer = updateDataViewViewer,
        analytics = analytics,
        createFromScratchState = createFromScratchState,
        createRelation = createRelation,
        spaceManager = spaceManager,
        analyticSpaceHelperDelegate = analyticSpaceHelperDelegate,
        storeOfRelations = storeOfRelations
    )

    @JvmStatic
    @Provides
    @CreateFromScratch
    fun provideState(): StateHolder<CreateFromScratchState> = StateHolder(
        initial = CreateFromScratchState(
            format = RelationFormat.OBJECT,
            limitObjectTypes = emptyList()
        )
    )

    @JvmStatic
    @Provides
    @CreateFromScratch
    fun createRelation(
        repo: BlockRepository,
        storeOfRelations: StoreOfRelations
    ) = CreateRelation(
        repo = repo,
        storeOfRelations = storeOfRelations
    )

    @JvmStatic
    @Provides
    @CreateFromScratch
    fun addRelationToDataView(repo: BlockRepository) = AddRelationToDataView(repo)
}

@Subcomponent(modules = [RelationCreateFromScratchForObjectBlockModule::class])
@CreateFromScratch
interface RelationCreateFromScratchForObjectBlockSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun module(module: RelationCreateFromScratchForObjectBlockModule): Builder
        fun build(): RelationCreateFromScratchForObjectBlockSubComponent
    }

    fun inject(fragment: RelationCreateFromScratchForObjectBlockFragment)

    fun relationFormatPickerComponent(): RelationFormatPickerSubcomponent.Builder
    fun limitObjectTypeComponent(): LimitObjectTypeSubComponent.Builder
}

@Module
object RelationCreateFromScratchForObjectBlockModule {

    @JvmStatic
    @Provides
    @CreateFromScratch
    fun provideViewModelFactory(
        addRelationToObject: AddRelationToObject,
        createRelation: CreateRelation,
        dispatcher: Dispatcher<Payload>,
        analytics: Analytics,
        createFromScratchState: StateHolder<CreateFromScratchState>,
        spaceManager: SpaceManager,
        analyticSpaceHelperDelegate: AnalyticSpaceHelperDelegate,
        storeOfRelations: StoreOfRelations
    ) = RelationCreateFromScratchForObjectBlockViewModel.Factory(
        addRelationToObject = addRelationToObject,
        createRelation = createRelation,
        dispatcher = dispatcher,
        analytics = analytics,
        createFromScratchState = createFromScratchState,
        spaceManager = spaceManager,
        analyticSpaceHelperDelegate = analyticSpaceHelperDelegate,
        storeOfRelations = storeOfRelations
    )

    @JvmStatic
    @Provides
    @CreateFromScratch
    fun provideState(): StateHolder<CreateFromScratchState> = StateHolder(
        initial = CreateFromScratchState(
            format = RelationFormat.OBJECT,
            limitObjectTypes = emptyList()
        )
    )

    @JvmStatic
    @Provides
    @CreateFromScratch
    fun createRelation(
        repo: BlockRepository,
        storeOfRelations: StoreOfRelations
    ) = CreateRelation(
        repo = repo,
        storeOfRelations = storeOfRelations
    )
}

@Subcomponent
@PerDialog
interface RelationFormatPickerSubcomponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): RelationFormatPickerSubcomponent
    }

    fun inject(fragment: RelationCreateFromScratchFormatPickerFragment)
}

