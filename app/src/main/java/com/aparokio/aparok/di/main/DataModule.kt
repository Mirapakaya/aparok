package com.aparokio.aparok.di.main

import android.content.Context
import android.content.SharedPreferences
import com.aparokio.aparok.app.DefaultAppActionManager
import com.aparokio.aparok.app.DefaultInitialParamsProvider
import com.aparokio.aparok.core_utils.tools.ThreadInfo
import com.aparokio.aparok.data.auth.repo.AuthCache
import com.aparokio.aparok.data.auth.repo.AuthCacheDataStore
import com.aparokio.aparok.data.auth.repo.AuthDataRepository
import com.aparokio.aparok.data.auth.repo.AuthDataStoreFactory
import com.aparokio.aparok.data.auth.repo.AuthRemote
import com.aparokio.aparok.data.auth.repo.AuthRemoteDataStore
import com.aparokio.aparok.data.auth.repo.DebugSettingsCache
import com.aparokio.aparok.data.auth.repo.InfrastructureDataRepository
import com.aparokio.aparok.data.auth.repo.UserSettingsCache
import com.aparokio.aparok.data.auth.repo.UserSettingsDataRepository
import com.aparokio.aparok.data.auth.repo.block.BlockDataRepository
import com.aparokio.aparok.data.auth.repo.block.BlockRemote
import com.aparokio.aparok.data.auth.repo.favorites.PersonalFavoritesDataRepository
import com.aparokio.aparok.data.auth.repo.unsplash.UnsplashDataRepository
import com.aparokio.aparok.data.auth.repo.unsplash.UnsplashRemote
import com.aparokio.aparok.data.auth.types.DefaultObjectTypesProvider
import com.aparokio.aparok.device.AppStateService
import com.aparokio.aparok.presentation.device.BuildProvider
import com.aparokio.aparok.presentation.device.DefaultBuildProvider
import com.aparokio.aparok.device.DefaultPathProvider
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.favorites.PersonalFavoritesRepository
import com.aparokio.aparok.domain.config.InfrastructureRepository
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.debugging.DebugConfig
import com.aparokio.aparok.domain.debugging.Logger
import com.aparokio.aparok.domain.device.PathProvider
import com.aparokio.aparok.domain.misc.AppActionManager
import com.aparokio.aparok.domain.`object`.ObjectTypesProvider
import com.aparokio.aparok.domain.objects.DefaultObjectStore
import com.aparokio.aparok.domain.objects.ObjectStore
import com.aparokio.aparok.domain.platform.InitialParamsProvider
import com.aparokio.aparok.domain.unsplash.UnsplashRepository
import com.aparokio.aparok.domain.invite.SpaceInviteLinkStore
import com.aparokio.aparok.domain.invite.SpaceInviteLinkStoreImpl
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.widgets.OsWidgetDataCleaner
import com.aparokio.aparok.domain.widgets.OsWidgetDataViewSync
import com.aparokio.aparok.domain.widgets.OsWidgetSpacesSync
import com.aparokio.aparok.domain.`object`.GetObject
import com.aparokio.aparok.domain.search.SearchObjects
import com.aparokio.aparok.feature_os_widgets.persistence.OsWidgetDataCleanerImpl
import com.aparokio.aparok.feature_os_widgets.persistence.OsWidgetDataViewSyncImpl
import com.aparokio.aparok.feature_os_widgets.persistence.OsWidgetSpacesSyncImpl
import com.aparokio.aparok.middleware.EventProxy
import com.aparokio.aparok.middleware.UnsplashMiddleware
import com.aparokio.aparok.middleware.auth.AuthMiddleware
import com.aparokio.aparok.middleware.block.BlockMiddleware
import com.aparokio.aparok.middleware.interactor.Middleware
import com.aparokio.aparok.middleware.interactor.MiddlewareFactory
import com.aparokio.aparok.middleware.interactor.MiddlewareProtobufLogger
import com.aparokio.aparok.middleware.interactor.ProtobufConverterProvider
import com.aparokio.aparok.middleware.service.MiddlewareService
import com.aparokio.aparok.middleware.service.MiddlewareServiceImplementation
import com.aparokio.aparok.persistence.db.AparokDatabase
import com.aparokio.aparok.device.providers.AppDefaultDateFormatProvider
import com.aparokio.aparok.device.providers.AppDefaultDateFormatProviderImpl
import com.aparokio.aparok.di.main.ConfigModule.DEFAULT_APP_COROUTINE_SCOPE
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.device.SetAppState
import com.aparokio.aparok.domain.misc.LocaleProvider
import com.aparokio.aparok.domain.network.NetworkModeProvider
import com.aparokio.aparok.persistence.repo.DefaultAuthCache
import com.aparokio.aparok.persistence.repo.DefaultDebugSettingsCache
import com.aparokio.aparok.persistence.repo.DefaultUserSettingsCache
import com.aparokio.aparok.presentation.util.downloader.UriFileProvider
import com.aparokio.aparok.providers.DefaultUriFileProvider
import com.aparokio.aparok.security.KeystoreManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope

@Module(includes = [DataModule.Bindings::class])
object DataModule {

    @JvmStatic
    @Provides
    fun providePathProvider(context: Context): PathProvider {
        return DefaultPathProvider(context)
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideAuthRepository(
        factory: AuthDataStoreFactory,
        debugConfig: DebugConfig
    ): AuthRepository {
        return AuthDataRepository(
            factory = factory,
            debugConfig = debugConfig
        )
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideAuthDataStoreFactory(
        authCacheDataStore: AuthCacheDataStore,
        authRemoteDataStore: AuthRemoteDataStore
    ): AuthDataStoreFactory {
        return AuthDataStoreFactory(
            cache = authCacheDataStore,
            remote = authRemoteDataStore
        )
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideAuthCacheDataStore(
        authCache: AuthCache
    ): AuthCacheDataStore {
        return AuthCacheDataStore(
            cache = authCache
        )
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideAuthCache(
        db: AparokDatabase,
        @Named("default") defaultPrefs: SharedPreferences,
        @Named("encrypted") encryptedPrefs: SharedPreferences,
        networkModeProvider: NetworkModeProvider
    ): AuthCache {
        return DefaultAuthCache(
            db = db,
            defaultPrefs = defaultPrefs,
            encryptedPrefs = encryptedPrefs,
            networkModeProvider = networkModeProvider
        )
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideDebugSettingsCache(
        @Named("default") prefs: SharedPreferences,
    ): DebugSettingsCache {
        return DefaultDebugSettingsCache(
            prefs = prefs
        )
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideInfrastructureRepository(
        cache: DebugSettingsCache
    ): InfrastructureRepository {
        return InfrastructureDataRepository(
            cache = cache
        )
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideAparokDatabase(context: Context): AparokDatabase {
        return AparokDatabase.get(context)
    }

    @JvmStatic
    @Provides
    @Singleton
    @Named("default")
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    }

    @JvmStatic
    @Provides
    @Singleton
    @Named("encrypted")
    fun provideEncryptedSharedPreferences(
        keystoreManager: KeystoreManager
    ): SharedPreferences = keystoreManager.initializeEncryptedPreferences()

    @JvmStatic
    @Provides
    @Singleton
    fun provideAuthRemoteDataStore(
        authRemote: AuthRemote
    ): AuthRemoteDataStore {
        return AuthRemoteDataStore(
            authRemote = authRemote
        )
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideAuthRemote(
        middleware: Middleware,
        proxy: EventProxy
    ): AuthRemote {
        return AuthMiddleware(
            middleware = middleware,
            events = proxy
        )
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideBlockRepository(
        blockRemote: BlockRemote,
        debugConfig: DebugConfig,
        logger: Logger
    ): BlockRepository {
        return BlockDataRepository(
            remote = blockRemote,
            debugConfig = debugConfig,
            logger = logger
        )
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideBlockRemote(
        middleware: Middleware
    ): BlockRemote = BlockMiddleware(middleware = middleware)

    @JvmStatic
    @Provides
    @Singleton
    fun providePersonalFavoritesRepository(
        blocks: BlockRepository
    ): PersonalFavoritesRepository = PersonalFavoritesDataRepository(blockRepo = blocks)

    @JvmStatic
    @Provides
    @Singleton
    fun provideMiddleware(
        service: MiddlewareService,
        factory: MiddlewareFactory,
        logger: MiddlewareProtobufLogger,
        protobufConverter: ProtobufConverterProvider,
        threadInfo: ThreadInfo
    ): Middleware = Middleware(
        service = service,
        factory = factory,
        logger = logger,
        protobufConverter = protobufConverter,
        threadInfo = threadInfo
    )

    @JvmStatic
    @Provides
    @Singleton
    fun provideMiddlewareFactory(): MiddlewareFactory = MiddlewareFactory()

    @JvmStatic
    @Provides
    @Singleton
    fun provideObjectTypesProvider(): ObjectTypesProvider {
        return DefaultObjectTypesProvider()
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideUserSettingsCache(
        @Named("default") prefs: SharedPreferences,
        context: Context,
        appDefaultDateFormatProvider: AppDefaultDateFormatProvider,
        osWidgetDataCleaner: OsWidgetDataCleaner
    ): UserSettingsCache = DefaultUserSettingsCache(
        prefs = prefs,
        context = context,
        appDefaultDateFormatProvider = appDefaultDateFormatProvider,
        osWidgetDataCleaner = osWidgetDataCleaner
    )

    @JvmStatic
    @Provides
    @Singleton
    fun provideOsWidgetDataCleaner(
        context: Context
    ): OsWidgetDataCleaner = OsWidgetDataCleanerImpl(context)

    @JvmStatic
    @Provides
    @Singleton
    fun provideUserSettingsRepo(
        cache: UserSettingsCache
    ): UserSettingsRepository = UserSettingsDataRepository(cache)

    @JvmStatic
    @Provides
    @Singleton
    fun provideOsWidgetSpacesSync(
        context: Context,
        urlBuilder: UrlBuilder
    ): OsWidgetSpacesSync = OsWidgetSpacesSyncWithUpdate(
        delegate = OsWidgetSpacesSyncImpl(context, urlBuilder),
        context = context
    )

    @JvmStatic
    @Provides
    @Singleton
    fun provideOsWidgetDataViewSync(
        context: Context,
        getObject: GetObject,
        searchObjects: SearchObjects,
        blockRepository: BlockRepository
    ): OsWidgetDataViewSync = OsWidgetDataViewSyncWithUpdate(
        delegate = OsWidgetDataViewSyncImpl(
            context = context,
            getObject = getObject,
            searchObjects = searchObjects,
            blockRepository = blockRepository
        ),
        context = context
    )

    @JvmStatic
    @Provides
    @Singleton
    fun provideAppActionManager(context: Context): AppActionManager = DefaultAppActionManager(
        context = context
    )

    @JvmStatic
    @Provides
    @Singleton
    fun provideObjectStore(): ObjectStore = DefaultObjectStore()

    //region Unsplash

    @JvmStatic
    @Provides
    @Singleton
    fun provideUnsplashRepo(
        remote: UnsplashRemote
    ): UnsplashRepository = UnsplashDataRepository(
        remote = remote
    )
    //endregion

    @JvmStatic
    @Provides
    @Singleton
    fun provideBuildProvider(): BuildProvider = DefaultBuildProvider()

    @JvmStatic
    @Provides
    @Singleton
    fun provideMetricsProvider(context: Context): InitialParamsProvider =
        DefaultInitialParamsProvider(context)

    @JvmStatic
    @Provides
    @Singleton
    fun provideFileProvider(
        context: Context,
        logger: Logger
    ): UriFileProvider = DefaultUriFileProvider(context, logger)

    @JvmStatic
    @Provides
    @Singleton
    fun provideAppDefaultDateFormatProvider(
        localeProvider: LocaleProvider
    ): AppDefaultDateFormatProvider = AppDefaultDateFormatProviderImpl(
        localeProvider = localeProvider
    )

    @JvmStatic
    @Provides
    @Singleton
    fun provideSetDeviceState(
        blockRepository: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetAppState = SetAppState(blockRepository, dispatchers)

    @JvmStatic
    @Provides
    @Singleton
    fun provideAppStateService(
        setAppState: SetAppState,
        @Named(DEFAULT_APP_COROUTINE_SCOPE) scope: CoroutineScope,
        userSettingsRepository: UserSettingsRepository
    ): AppStateService = AppStateService(
        setAppState = setAppState,
        coroutineScope = scope,
        settings = userSettingsRepository
    )

    @JvmStatic
    @Provides
    @Singleton
    fun provideSpaceInviteLinkStore(
    ): SpaceInviteLinkStore = SpaceInviteLinkStoreImpl()

    @Provides
    @Singleton
    fun provideKeystoreManager(context: Context): KeystoreManager = KeystoreManager(context)

    @Module
    interface Bindings {

        @Binds
        @Singleton
        fun bindUnsplashRemote(middleware: UnsplashMiddleware): UnsplashRemote

        @Binds
        @Singleton
        fun bindMiddlewareService(middleware: MiddlewareServiceImplementation): MiddlewareService
    }
}

const val ENCRYPTED_PREFS_NAME = "encrypted_prefs"
