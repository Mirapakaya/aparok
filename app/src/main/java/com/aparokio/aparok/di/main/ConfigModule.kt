package com.aparokio.aparok.di.main

import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.data.auth.repo.config.GatewayProvider
import com.aparokio.aparok.domain.account.AwaitAccountStartManager
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.config.CacheStorage
import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.domain.config.Gateway
import com.aparokio.aparok.domain.config.TechSpaceProvider
import com.aparokio.aparok.domain.auth.interactor.PreloadRemainingSpaces
import com.aparokio.aparok.domain.debugging.Logger
import com.aparokio.aparok.domain.launch.PreferredSpaceIdHolder
import com.aparokio.aparok.domain.launch.RemainingSpacesPreloader
import com.aparokio.aparok.domain.misc.UrlBuilderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@Module
object ConfigModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideGateway(
        configStorage: ConfigStorage,
        logger: Logger
    ): Gateway = GatewayProvider(
        configStorage = configStorage,
        logger = logger
    )

    @JvmStatic
    @Provides
    @Singleton
    fun provideUrlBuilder(
        getGateway: Gateway
    ): UrlBuilder = UrlBuilderImpl(
        gateway = getGateway
    )

    @JvmStatic
    @Provides
    @Singleton
    fun provideConfigProvider(): ConfigStorage = CacheStorage()

    @JvmStatic
    @Provides
    @Singleton
    fun provideTechSpaceProvider(
        configStorage: ConfigStorage
    ): TechSpaceProvider = configStorage

    @JvmStatic
    @Provides
    @Singleton
    fun dispatchers(): AppCoroutineDispatchers = AppCoroutineDispatchers(
        io = Dispatchers.IO,
        computation = Dispatchers.Default,
        main = Dispatchers.Main
    )

    @JvmStatic
    @Provides
    @Singleton
    @Named(DEFAULT_APP_COROUTINE_SCOPE)
    fun applicationCoroutineScope() : CoroutineScope = CoroutineScope(
        SupervisorJob() + Dispatchers.Default
    )

    @JvmStatic
    @Provides
    @Singleton
    fun awaitAccountStartedManager(): AwaitAccountStartManager = AwaitAccountStartManager.Default

    @JvmStatic
    @Provides
    @Singleton
    fun preferredSpaceIdHolder(): PreferredSpaceIdHolder = PreferredSpaceIdHolder.Default

    @JvmStatic
    @Provides
    @Singleton
    fun remainingSpacesPreloader(
        preloadRemainingSpaces: PreloadRemainingSpaces
    ): RemainingSpacesPreloader = RemainingSpacesPreloader(preloadRemainingSpaces)

    const val DEFAULT_APP_COROUTINE_SCOPE = "Default application coroutine scope"
}