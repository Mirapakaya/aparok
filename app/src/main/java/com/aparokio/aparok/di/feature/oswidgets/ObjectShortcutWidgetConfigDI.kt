package com.aparokio.aparok.di.feature.oswidgets

import android.content.Context
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.account.AwaitAccountStartManager
import com.aparokio.aparok.domain.auth.interactor.LaunchAccount
import com.aparokio.aparok.domain.launch.PreferredSpaceIdHolder
import com.aparokio.aparok.domain.auth.interactor.LaunchWallet
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.device.PathProvider
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.platform.InitialParamsProvider
import com.aparokio.aparok.domain.search.SearchObjects
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.feature_os_widgets.persistence.OsWidgetIconCache
import com.aparokio.aparok.feature_os_widgets.persistence.OsWidgetObjectShortcutEntity
import com.aparokio.aparok.feature_os_widgets.persistence.OsWidgetsDataStore
import com.aparokio.aparok.feature_os_widgets.presentation.ObjectShortcutIconCache
import com.aparokio.aparok.feature_os_widgets.presentation.ObjectShortcutWidgetConfigStore
import com.aparokio.aparok.feature_os_widgets.presentation.ObjectShortcutWidgetConfigViewModel
import com.aparokio.aparok.feature_os_widgets.presentation.ObjectShortcutWidgetUpdater
import com.aparokio.aparok.feature_os_widgets.ui.OsObjectShortcutWidgetUpdater
import com.aparokio.aparok.ui.oswidgets.ObjectShortcutWidgetConfigActivity
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(
    dependencies = [ObjectShortcutWidgetConfigDependencies::class],
    modules = [ObjectShortcutWidgetConfigModule::class]
)
@PerScreen
interface ObjectShortcutWidgetConfigComponent {

    fun inject(activity: ObjectShortcutWidgetConfigActivity)

    @Component.Factory
    interface Factory {
        fun create(dependencies: ObjectShortcutWidgetConfigDependencies): ObjectShortcutWidgetConfigComponent
    }
}

@Module
object ObjectShortcutWidgetConfigModule {

    @JvmStatic
    @Provides
    @PerScreen
    fun provideObjectShortcutStore(context: Context): ObjectShortcutWidgetConfigStore {
        val appContext = context.applicationContext
        val dataStore = OsWidgetsDataStore(appContext)
        return object : ObjectShortcutWidgetConfigStore {
            override suspend fun save(config: OsWidgetObjectShortcutEntity) {
                dataStore.saveObjectShortcutConfig(config)
            }
        }
    }

    @JvmStatic
    @Provides
    @PerScreen
    fun provideObjectShortcutIconCache(context: Context): ObjectShortcutIconCache {
        val appContext = context.applicationContext
        val iconCache = OsWidgetIconCache(appContext)
        return object : ObjectShortcutIconCache {
            override suspend fun cacheForWidget(url: String, appWidgetId: Int): String? {
                return iconCache.cacheShortcutIcon(
                    url = url,
                    widgetId = appWidgetId,
                    prefix = OsWidgetIconCache.PREFIX_OBJECT
                )
            }
        }
    }

    @JvmStatic
    @Provides
    @PerScreen
    fun provideObjectShortcutWidgetUpdater(context: Context): ObjectShortcutWidgetUpdater {
        val appContext = context.applicationContext
        return ObjectShortcutWidgetUpdater { appWidgetId ->
            OsObjectShortcutWidgetUpdater.update(appContext, appWidgetId)
        }
    }

    @JvmStatic
    @Provides
    @PerScreen
    fun provideLaunchWallet(
        authRepository: AuthRepository,
        pathProvider: PathProvider
    ): LaunchWallet = LaunchWallet(
        repository = authRepository,
        pathProvider = pathProvider
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideLaunchAccount(
        authRepository: AuthRepository,
        pathProvider: PathProvider,
        configStorage: ConfigStorage,
        spaceManager: SpaceManager,
        initialParamsProvider: InitialParamsProvider,
        userSettings: UserSettingsRepository,
        awaitAccountStartManager: AwaitAccountStartManager,
        preferredSpaceIdHolder: PreferredSpaceIdHolder
    ): LaunchAccount = LaunchAccount(
        repository = authRepository,
        pathProvider = pathProvider,
        configStorage = configStorage,
        spaceManager = spaceManager,
        initialParamsProvider = initialParamsProvider,
        settings = userSettings,
        awaitAccountStartManager = awaitAccountStartManager,
        preferredSpaceIdHolder = preferredSpaceIdHolder
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideObjectShortcutViewModelFactory(
        spaceViews: SpaceViewSubscriptionContainer,
        urlBuilder: UrlBuilder,
        searchObjects: SearchObjects,
        configStore: ObjectShortcutWidgetConfigStore,
        iconCache: ObjectShortcutIconCache,
        widgetUpdater: ObjectShortcutWidgetUpdater,
        launchWallet: LaunchWallet,
        launchAccount: LaunchAccount
    ): ObjectShortcutWidgetConfigViewModel.Factory = ObjectShortcutWidgetConfigViewModel.Factory(
        spaceViews = spaceViews,
        urlBuilder = urlBuilder,
        searchObjects = searchObjects,
        configStore = configStore,
        iconCache = iconCache,
        widgetUpdater = widgetUpdater,
        launchWallet = launchWallet,
        launchAccount = launchAccount
    )
}

interface ObjectShortcutWidgetConfigDependencies : ComponentDependencies {
    fun context(): Context
    fun spaceViewSubscriptionContainer(): SpaceViewSubscriptionContainer
    fun urlBuilder(): UrlBuilder
    fun searchObjects(): SearchObjects
    fun authRepository(): AuthRepository
    fun pathProvider(): PathProvider
    fun configStorage(): ConfigStorage
    fun spaceManager(): SpaceManager
    fun metricsProvider(): InitialParamsProvider
    fun userSettingsRepository(): UserSettingsRepository
    fun awaitAccountStartManager(): AwaitAccountStartManager
    fun preferredSpaceIdHolder(): PreferredSpaceIdHolder
}
