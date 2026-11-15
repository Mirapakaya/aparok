package com.aparokio.aparok.di.feature

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_utils.di.scope.CreateFromScratch
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.interactor.UpdateText
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.dataview.SetDataViewProperties
import com.aparokio.aparok.domain.debugging.Logger
import com.aparokio.aparok.domain.event.interactor.EventChannel
import com.aparokio.aparok.domain.event.interactor.SpaceSyncAndP2PStatusProvider
import com.aparokio.aparok.domain.library.StorelessSubscriptionContainer
import com.aparokio.aparok.domain.misc.LocaleProvider
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.`object`.DuplicateObjects
import com.aparokio.aparok.domain.`object`.SetObjectDetails
import com.aparokio.aparok.domain.objects.DeleteObjects
import com.aparokio.aparok.domain.objects.SetObjectListIsArchived
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.domain.primitives.GetObjectTypeConflictingFields
import com.aparokio.aparok.domain.primitives.SetObjectTypeHeaderRecommendedFields
import com.aparokio.aparok.domain.primitives.SetObjectTypeRecommendedFields
import com.aparokio.aparok.domain.relations.AddToFeaturedRelations
import com.aparokio.aparok.domain.relations.CreateRelation
import com.aparokio.aparok.domain.relations.RemoveFromFeaturedRelations
import com.aparokio.aparok.domain.resources.StringResourceProvider
import com.aparokio.aparok.domain.search.SearchObjects
import com.aparokio.aparok.domain.search.SubscriptionEventChannel
import com.aparokio.aparok.domain.spaces.ClearLastOpenedSpace
import com.aparokio.aparok.domain.types.CreateObjectType
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.feature_object_type.ui.ObjectTypeVmParams
import com.aparokio.aparok.feature_object_type.viewmodel.CreateObjectTypeVMFactory
import com.aparokio.aparok.feature_object_type.viewmodel.CreateTypeVmParams
import com.aparokio.aparok.feature_object_type.viewmodel.ObjectTypeVMFactory
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.editor.cover.CoverImageHashProvider
import com.aparokio.aparok.presentation.navigation.backstack.BackHistoryDelegate
import com.aparokio.aparok.presentation.navigation.backstack.NavigationBackStackInspector
import com.aparokio.aparok.presentation.vault.ExitToVaultDelegate
import com.aparokio.aparok.feature_properties.space.SpacePropertiesViewModel
import com.aparokio.aparok.feature_properties.space.SpacePropertiesVmFactory
import com.aparokio.aparok.presentation.types.SpaceTypesViewModel
import com.aparokio.aparok.presentation.types.SpaceTypesVmFactory
import com.aparokio.aparok.presentation.util.Dispatcher
import com.aparokio.aparok.providers.DefaultCoverImageHashProvider
import com.aparokio.aparok.ui.primitives.CreateTypeFragment
import com.aparokio.aparok.ui.primitives.ObjectTypeFieldsFragment
import com.aparokio.aparok.ui.primitives.ObjectTypeFragment
import com.aparokio.aparok.ui.primitives.SpacePropertiesFragment
import com.aparokio.aparok.ui.primitives.SpaceTypesFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(
    dependencies = [ObjectTypeDependencies::class],
    modules = [
        ObjectTypeModule::class,
        ObjectTypeModule.Declarations::class
    ]
)
@PerScreen
interface ObjectTypeComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance vmParams: ObjectTypeVmParams,
            dependencies: ObjectTypeDependencies
        ): ObjectTypeComponent
    }

    fun inject(fragment: ObjectTypeFragment)
    fun inject(fragment: ObjectTypeFieldsFragment)
}

@Module
object ObjectTypeModule {

    @JvmStatic
    @Provides
    @PerScreen
    fun provideSetDataViewProperties(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetDataViewProperties = SetDataViewProperties(repo, dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideStoreLessSubscriptionContainer(
        repo: BlockRepository,
        channel: SubscriptionEventChannel,
        dispatchers: AppCoroutineDispatchers,
        logger: Logger
    ): StorelessSubscriptionContainer = StorelessSubscriptionContainer.Impl(
        repo = repo,
        channel = channel,
        dispatchers = dispatchers,
        logger = logger
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideUpdateDetailUseCase(
        repository: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetObjectDetails = SetObjectDetails(repository, dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun coverHashProvider(): CoverImageHashProvider = DefaultCoverImageHashProvider()

    @JvmStatic
    @PerScreen
    @Provides
    fun getDeleteObjects(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): DeleteObjects = DeleteObjects(repo, dispatchers)

    @JvmStatic
    @PerScreen
    @Provides
    fun getObjectTypeConflictingFields(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): GetObjectTypeConflictingFields = GetObjectTypeConflictingFields(repo, dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideDuplicateObjectsListUseCase(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): DuplicateObjects = DuplicateObjects(
        repo = repo,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideTypeSetRecommendedFields(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetObjectTypeRecommendedFields = SetObjectTypeRecommendedFields(repo, dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideTypeSetHeaderRecommendedFields(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetObjectTypeHeaderRecommendedFields =
        SetObjectTypeHeaderRecommendedFields(repo, dispatchers)

    @JvmStatic
    @PerScreen
    @Provides
    fun getSetObjectListIsArchived(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetObjectListIsArchived = SetObjectListIsArchived(repo, dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideAddToFeaturedRelations(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): AddToFeaturedRelations = AddToFeaturedRelations(repo, dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideRemoveFromFeaturedRelations(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): RemoveFromFeaturedRelations = RemoveFromFeaturedRelations(repo, dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideUpdateBlockUseCase(
        repo: BlockRepository
    ): UpdateText = UpdateText(
        repo = repo
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideBackHistoryDelegate(
        inspector: NavigationBackStackInspector,
        searchObjects: SearchObjects,
        fieldParser: FieldParser,
        dispatchers: AppCoroutineDispatchers
    ): BackHistoryDelegate = BackHistoryDelegate.Default(
        inspector = inspector,
        searchObjects = searchObjects,
        fieldParser = fieldParser,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideExitToVaultDelegate(
        spaceManager: SpaceManager,
        clearLastOpenedSpace: ClearLastOpenedSpace
    ): ExitToVaultDelegate = ExitToVaultDelegate.Default(
        spaceManager = spaceManager,
        clearLastOpenedSpace = clearLastOpenedSpace
    )

    @Module
    interface Declarations {
        @PerScreen
        @Binds
        fun bindViewModelFactory(
            factory: ObjectTypeVMFactory
        ): ViewModelProvider.Factory
    }
}

interface ObjectTypeDependencies : ComponentDependencies {
    fun blockRepository(): BlockRepository
    fun analytics(): Analytics
    fun urlBuilder(): UrlBuilder
    fun dispatchers(): AppCoroutineDispatchers
    fun storeOfObjectTypes(): StoreOfObjectTypes
    fun analyticsHelper(): AnalyticSpaceHelperDelegate
    fun subEventChannel(): SubscriptionEventChannel
    fun logger(): Logger
    fun localeProvider(): LocaleProvider
    fun config(): ConfigStorage
    fun userPermissionProvider(): UserPermissionProvider
    fun provideStoreOfRelations(): StoreOfRelations
    fun provideSpaceSyncAndP2PStatusProvider(): SpaceSyncAndP2PStatusProvider
    fun provideUserSettingsRepository(): UserSettingsRepository
    fun fieldParser(): FieldParser
    fun provideEventChannel(): EventChannel
    fun provideStringResourceProvider(): StringResourceProvider
    fun dispatcher(): Dispatcher<Payload>
    fun spaceManager(): SpaceManager
    fun navigationBackStackInspector(): NavigationBackStackInspector
}

//region Space Types Screen
@Component(
    dependencies = [SpaceTypesDependencies::class],
    modules = [
        SpaceTypesModule::class,
        SpaceTypesModule.Declarations::class
    ]
)
@PerScreen
interface SpaceTypesComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance vmParams: SpaceTypesViewModel.VmParams,
            dependencies: SpaceTypesDependencies
        ): SpaceTypesComponent
    }

    fun inject(fragment: SpaceTypesFragment)
}

@Module
object SpaceTypesModule {

    @JvmStatic
    @PerScreen
    @Provides
    fun getSetObjectListIsArchived(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetObjectListIsArchived = SetObjectListIsArchived(repo, dispatchers)

    @Module
    interface Declarations {
        @PerScreen
        @Binds
        fun bindViewModelFactory(
            factory: SpaceTypesVmFactory
        ): ViewModelProvider.Factory
    }
}

interface SpaceTypesDependencies : ComponentDependencies {
    fun stringResourceProvider(): StringResourceProvider
    fun blockRepository(): BlockRepository
    fun analyticsHelper(): AnalyticSpaceHelperDelegate
    fun analytics(): Analytics
    fun dispatchers(): AppCoroutineDispatchers
    fun storeOfObjectTypes(): StoreOfObjectTypes
    fun userPermissionProvider(): UserPermissionProvider
    fun fieldParser(): FieldParser
    fun spaceViewSubscriptionContainer(): SpaceViewSubscriptionContainer
}
//endregion

//region Space Properties Screen
@Component(
    dependencies = [SpacePropertiesDependencies::class],
    modules = [
        SpacePropertiesModule::class,
        SpacePropertiesModule.Declarations::class
    ]
)
@PerScreen
interface SpacePropertiesComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance vmParams: SpacePropertiesViewModel.VmParams,
            dependencies: SpacePropertiesDependencies
        ): SpacePropertiesComponent
    }

    fun inject(fragment: SpacePropertiesFragment)
}

@Module
object SpacePropertiesModule {

    @JvmStatic
    @PerScreen
    @Provides
    fun getSetObjectListIsArchived(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetObjectListIsArchived = SetObjectListIsArchived(repo, dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun createRelation(
        repo: BlockRepository,
        storeOfRelations: StoreOfRelations
    ) = CreateRelation(
        repo = repo,
        storeOfRelations = storeOfRelations
    )

    @Module
    interface Declarations {
        @PerScreen
        @Binds
        fun bindViewModelFactory(
            factory: SpacePropertiesVmFactory
        ): ViewModelProvider.Factory
    }
}

interface SpacePropertiesDependencies : ComponentDependencies {
    fun stringResourceProvider(): StringResourceProvider
    fun blockRepository(): BlockRepository
    fun analyticsHelper(): AnalyticSpaceHelperDelegate
    fun analytics(): Analytics
    fun dispatchers(): AppCoroutineDispatchers
    fun storeOfObjectTypes(): StoreOfObjectTypes
    fun storeOfRelations(): StoreOfRelations
    fun userPermissionProvider(): UserPermissionProvider
    fun fieldParser(): FieldParser
}
//endregion

//region Create Type Screen
@Component(
    dependencies = [CreateObjectTypeDependencies::class],
    modules = [
        CreateObjectTypeModule::class,
        CreateObjectTypeModule.Declarations::class
    ]
)
@PerScreen
interface CreateObjectTypeComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance vmParams: CreateTypeVmParams,
            dependencies: CreateObjectTypeDependencies
        )
                : CreateObjectTypeComponent
    }

    fun inject(fragment: CreateTypeFragment)
}

@Module
object CreateObjectTypeModule {

    @Provides
    @PerScreen
    @JvmStatic
    fun createObjectType(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): CreateObjectType = CreateObjectType(repo, dispatchers)

    @Module
    interface Declarations {
        @PerScreen
        @Binds
        fun bindViewModelFactory(
            factory: CreateObjectTypeVMFactory
        ): ViewModelProvider.Factory
    }
}

interface CreateObjectTypeDependencies : ComponentDependencies {
    fun stringResourceProvider(): StringResourceProvider
    fun blockRepository(): BlockRepository
    fun analyticsHelper(): AnalyticSpaceHelperDelegate
    fun analytics(): Analytics
    fun dispatchers(): AppCoroutineDispatchers
}
//endregion