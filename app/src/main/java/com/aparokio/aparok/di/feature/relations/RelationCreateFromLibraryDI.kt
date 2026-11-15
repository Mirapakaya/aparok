package com.aparokio.aparok.di.feature.relations

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.core_models.RelationFormat
import com.aparokio.aparok.core_utils.di.scope.CreateFromScratch
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.objects.DefaultStoreOfRelations
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.domain.relations.CreateRelation
import com.aparokio.aparok.domain.search.SearchObjects
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.presentation.relations.RelationCreateFromLibraryViewModel
import com.aparokio.aparok.presentation.relations.model.CreateFromScratchState
import com.aparokio.aparok.presentation.relations.model.StateHolder
import com.aparokio.aparok.ui.relations.RelationCreateFromLibraryFragment
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(
    dependencies = [RelationCreateFromLibraryDependencies::class],
    modules = [
        RelationCreateFromLibraryModule::class,
        RelationCreateFromLibraryModule.Declarations::class
    ]
)
@PerScreen
@CreateFromScratch
interface RelationCreateFromLibraryComponent {

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: RelationCreateFromLibraryDependencies
        ): RelationCreateFromLibraryComponent
    }

    fun relationFormatPickerComponent(): RelationFormatPickerSubcomponent.Builder
    fun limitObjectTypeComponent(): LimitObjectTypeSubComponent.Builder

    fun inject(fragment: RelationCreateFromLibraryFragment)
}

@Module
object RelationCreateFromLibraryModule {

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
    fun createRelation(
        repo: BlockRepository,
        storeOfRelations: StoreOfRelations
    ) = CreateRelation(
        repo = repo,
        storeOfRelations = storeOfRelations
    )

    @JvmStatic
    @Provides
    fun provideSearchObjects(
        repo: BlockRepository
    ) = SearchObjects(
        repo = repo
    )

    @JvmStatic
    @Provides
    fun provideStoreOfRelations(): StoreOfRelations = DefaultStoreOfRelations()

    @Module
    interface Declarations {
        @PerScreen
        @Binds
        fun bindViewModelFactory(factory: RelationCreateFromLibraryViewModel.Factory): ViewModelProvider.Factory
    }

}

interface RelationCreateFromLibraryDependencies : ComponentDependencies {
    fun blockRepository(): BlockRepository
    fun urlBuilder(): UrlBuilder
    fun spaceManager(): SpaceManager
}