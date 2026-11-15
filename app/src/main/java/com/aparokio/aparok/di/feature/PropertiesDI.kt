package com.aparokio.aparok.di.feature

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_utils.di.scope.PerModal
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.dataview.SetDataViewProperties
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.`object`.SetObjectDetails
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.domain.primitives.SetObjectTypeRecommendedFields
import com.aparokio.aparok.domain.relations.CreateRelation
import com.aparokio.aparok.domain.resources.StringResourceProvider
import com.aparokio.aparok.feature_properties.EditTypePropertiesViewModelFactory
import com.aparokio.aparok.feature_properties.add.EditTypePropertiesVmParams
import com.aparokio.aparok.presentation.util.Dispatcher
import com.aparokio.aparok.ui.primitives.EditTypePropertiesFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

//region EDIT OBJECT TYPE PROPERTIES SCREEN
@PerModal
@Component(
    modules = [
        EditTypePropertiesModule::class,
        EditTypePropertiesModule.Declarations::class
    ],
    dependencies = [EditTypePropertiesDependencies::class]
)
interface EditTypePropertiesComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance vmParams: EditTypePropertiesVmParams,
            dependencies: EditTypePropertiesDependencies
        ): EditTypePropertiesComponent
    }

    fun inject(fragment: EditTypePropertiesFragment)
}

@Module
object EditTypePropertiesModule {

    @JvmStatic
    @Provides
    @PerModal
    fun provideTypeSetRecommendedFields(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetObjectTypeRecommendedFields = SetObjectTypeRecommendedFields(repo, dispatchers)

    @JvmStatic
    @Provides
    @PerModal
    fun createRelation(
        repo: BlockRepository,
        storeOfRelations: StoreOfRelations,
    ) = CreateRelation(repo, storeOfRelations)

    @JvmStatic
    @Provides
    @PerModal
    fun provideSetObjectDetails(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetObjectDetails = SetObjectDetails(repo, dispatchers)

    @JvmStatic
    @Provides
    @PerModal
    fun provideSetDataViewProperties(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetDataViewProperties = SetDataViewProperties(repo, dispatchers)

    @Module
    interface Declarations {
        @PerModal
        @Binds
        fun bindViewModelFactory(
            factory: EditTypePropertiesViewModelFactory
        ): ViewModelProvider.Factory
    }
}

interface EditTypePropertiesDependencies : ComponentDependencies {
    fun provideStringResourceProvider(): StringResourceProvider
    fun provideStoreOfRelations(): StoreOfRelations
    fun provideStoreOfObjectTypes(): StoreOfObjectTypes
    fun provideBlockRepository(): BlockRepository
    fun provideAppCoroutineDispatchers(): AppCoroutineDispatchers
    fun urlBuilder(): UrlBuilder
    fun dispatcher(): Dispatcher<Payload>
}
//endregion