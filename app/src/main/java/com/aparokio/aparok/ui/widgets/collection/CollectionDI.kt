package com.aparokio.aparok.ui.widgets.collection

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.interactor.Move
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.dashboard.interactor.SetObjectListIsFavorite
import com.aparokio.aparok.domain.debugging.Logger
import com.aparokio.aparok.domain.event.interactor.EventChannel
import com.aparokio.aparok.domain.event.interactor.InterceptEvents
import com.aparokio.aparok.domain.launch.GetDefaultObjectType
import com.aparokio.aparok.domain.library.StorelessSubscriptionContainer
import com.aparokio.aparok.domain.misc.DateProvider
import com.aparokio.aparok.domain.misc.DateTypeNameProvider
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.`object`.OpenObject
import com.aparokio.aparok.domain.objects.DeleteObjects
import com.aparokio.aparok.domain.objects.ObjectStore
import com.aparokio.aparok.domain.objects.SetObjectListIsArchived
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.page.CreateObject
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.domain.search.ObjectSearchSubscriptionContainer
import com.aparokio.aparok.domain.search.SubscriptionEventChannel
import com.aparokio.aparok.domain.templates.GetTemplates
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.util.Dispatcher
import com.aparokio.aparok.presentation.widgets.WidgetDispatchEvent
import com.aparokio.aparok.presentation.widgets.collection.ResourceProvider
import com.aparokio.aparok.presentation.widgets.collection.CollectionViewModel
import com.aparokio.aparok.ui.settings.RemoteFilesManageFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers

@Component(
    dependencies = [CollectionDependencies::class],
    modules = [CollectionModule::class]
)
@PerScreen
interface CollectionComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance vmParams: CollectionViewModel.VmParams,
            dependencies: CollectionDependencies
        ): CollectionComponent
    }

    fun inject(fragment: CollectionFragment)
    fun inject(fragment: RemoteFilesManageFragment)

}

@Module(includes = [CollectionModule.Declarations::class])
object CollectionModule {

    @JvmStatic
    @Provides
    @PerScreen
    fun openObject(
        repo: BlockRepository,
        settingsRepository: UserSettingsRepository,
        dispatchers: AppCoroutineDispatchers
    ): OpenObject = OpenObject(
        repo = repo,
        dispatchers = dispatchers,
        settings = settingsRepository
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideMoveUseCase(
        repo: BlockRepository,
        appCoroutineDispatchers: AppCoroutineDispatchers
    ): Move = Move(repo, appCoroutineDispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun objectSearchSubscriptionContainer(
        repo: BlockRepository,
        channel: SubscriptionEventChannel,
        store: ObjectStore,
        dispatchers: AppCoroutineDispatchers
    ): ObjectSearchSubscriptionContainer = ObjectSearchSubscriptionContainer(
        repo = repo,
        channel = channel,
        store = store,
        dispatchers = dispatchers
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun interceptEvents(channel: EventChannel): InterceptEvents = InterceptEvents(
        context = Dispatchers.IO,
        channel = channel
    )

    @JvmStatic
    @PerScreen
    @Provides
    fun getSetObjectListIsArchived(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetObjectListIsArchived = SetObjectListIsArchived(repo, dispatchers)

    @JvmStatic
    @PerScreen
    @Provides
    fun getSetObjectListIsFavorite(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetObjectListIsFavorite = SetObjectListIsFavorite(repo, dispatchers)

    @JvmStatic
    @PerScreen
    @Provides
    fun getDeleteObjects(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): DeleteObjects = DeleteObjects(repo, dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun getCreateObject(
        repo: BlockRepository,
        getDefaultObjectType: GetDefaultObjectType,
        dispatchers: AppCoroutineDispatchers
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
    fun provideGetTemplates(
        repo: BlockRepository,
        spaceManager: SpaceManager,
        dispatchers: AppCoroutineDispatchers
    ): GetTemplates = GetTemplates(
        repo = repo,
        spaceManager = spaceManager,
        dispatchers = dispatchers
    )

    @Module
    interface Declarations {
        @PerScreen
        @Binds
        fun factory(factory: CollectionViewModel.Factory): ViewModelProvider.Factory

        @PerScreen
        @Binds
        fun bindContainer(container: StorelessSubscriptionContainer.Impl): StorelessSubscriptionContainer
    }
}

interface CollectionDependencies : ComponentDependencies {
    fun dispatcherWidgets(): Dispatcher<WidgetDispatchEvent>
    fun dispatcherPayload(): Dispatcher<Payload>
    fun context(): Context
    fun blockRepo(): BlockRepository
    fun authRepo(): AuthRepository
    fun config(): ConfigStorage
    fun urlBuilder(): UrlBuilder
    fun objectStore(): ObjectStore
    fun subscriptionEventChannel(): SubscriptionEventChannel
    fun analytics(): Analytics
    fun eventChannel(): EventChannel
    fun userSettingsRepository(): UserSettingsRepository
    fun storeOfObjectTypes(): StoreOfObjectTypes
    fun dispatchers(): AppCoroutineDispatchers
    fun spaceManager(): SpaceManager
    fun logger(): Logger
    fun dateProvider(): DateProvider
    fun dateTypeNameProvider(): DateTypeNameProvider
    fun analyticsHelperDelegate(): AnalyticSpaceHelperDelegate
    fun provideUserPermissionProvider(): UserPermissionProvider
    fun fieldParser(): FieldParser
    fun resourceProvider(): ResourceProvider
    fun spaceViews(): SpaceViewSubscriptionContainer
}