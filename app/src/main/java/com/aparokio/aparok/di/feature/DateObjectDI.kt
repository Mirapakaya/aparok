package com.aparokio.aparok.di.feature

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.debugging.Logger
import com.aparokio.aparok.domain.event.interactor.SpaceSyncAndP2PStatusProvider
import com.aparokio.aparok.domain.launch.GetDefaultObjectType
import com.aparokio.aparok.domain.library.StorelessSubscriptionContainer
import com.aparokio.aparok.domain.misc.DateProvider
import com.aparokio.aparok.domain.misc.LocaleProvider
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.`object`.GetObject
import com.aparokio.aparok.domain.`object`.SetObjectDetails
import com.aparokio.aparok.domain.objects.GetDateObjectByTimestamp
import com.aparokio.aparok.domain.objects.SetObjectListIsArchived
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.domain.page.CreateObject
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.domain.relations.GetObjectRelationListById
import com.aparokio.aparok.domain.search.SearchObjects
import com.aparokio.aparok.domain.search.SubscriptionEventChannel
import com.aparokio.aparok.domain.spaces.ClearLastOpenedSpace
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.feature_date.viewmodel.DateObjectVMFactory
import com.aparokio.aparok.feature_date.viewmodel.DateObjectVmParams
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.navigation.backstack.BackHistoryDelegate
import com.aparokio.aparok.presentation.navigation.backstack.NavigationBackStackInspector
import com.aparokio.aparok.presentation.vault.ExitToVaultDelegate
import com.aparokio.aparok.ui.date.DateObjectFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(
    dependencies = [DateObjectDependencies::class],
    modules = [
        DateObjectModule::class,
        DateObjectModule.Declarations::class
    ]
)
@PerScreen
interface DateObjectComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance vmParams: DateObjectVmParams,
            dependencies: DateObjectDependencies
        ): DateObjectComponent
    }

    fun inject(fragment: DateObjectFragment)
}

@Module
object DateObjectModule {

    @JvmStatic
    @Provides
    @PerScreen
    fun getObject(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers,
        settings: UserSettingsRepository,
    ): GetObject = GetObject(
        repo = repo,
        dispatchers = dispatchers,
        settings = settings
    )

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
    fun searchObjects(
        repo: BlockRepository
    ): SearchObjects = SearchObjects(repo = repo)

    @JvmStatic
    @Provides
    @PerScreen
    fun createObject(
        repo: BlockRepository,
        getDefaultObjectType: GetDefaultObjectType,
        dispatchers: AppCoroutineDispatchers,
    ): CreateObject = CreateObject(
        repo = repo,
        getDefaultObjectType = getDefaultObjectType,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideGetDefaultPageType(
        userSettingsRepository: UserSettingsRepository,
        blockRepository: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): GetDefaultObjectType = GetDefaultObjectType(
        userSettingsRepository = userSettingsRepository,
        blockRepository = blockRepository,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideRelationListWithValue(
        repository: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): GetObjectRelationListById = GetObjectRelationListById(repository, dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideUpdateDetailUseCase(
        repository: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetObjectDetails = SetObjectDetails(repository, dispatchers)

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
            factory: DateObjectVMFactory
        ): ViewModelProvider.Factory

    }
}

interface DateObjectDependencies : ComponentDependencies {
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
    fun provideDateProvider(): DateProvider
    fun provideSpaceSyncAndP2PStatusProvider(): SpaceSyncAndP2PStatusProvider
    fun provideUserSettingsRepository(): UserSettingsRepository
    fun fieldParser(): FieldParser
    fun provideGetDateObjectByTimestamp(): GetDateObjectByTimestamp
    fun provideSpaceViews(): SpaceViewSubscriptionContainer
    fun spaceManager(): SpaceManager
    fun navigationBackStackInspector(): NavigationBackStackInspector
}