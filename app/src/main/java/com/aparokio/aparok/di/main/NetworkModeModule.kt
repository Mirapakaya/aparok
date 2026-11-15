package com.aparokio.aparok.di.main

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.aparokio.aparok.device.network_type.NetworkConnectionStatusImpl
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.device.NetworkConnectionStatus
import com.aparokio.aparok.domain.network.NetworkModeProvider
import com.aparokio.aparok.persistence.networkmode.DefaultNetworkModeProvider
import com.aparokio.aparok.persistence.networkmode.DefaultNetworkModeProvider.NetworkModeConstants.NAMED_NETWORK_MODE_PREFS
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope

@Module
object NetworkModeModule {

    @JvmStatic
    @Provides
    @Singleton
    @Named(NAMED_NETWORK_MODE_PREFS)
    fun provideNetworkModeSharedPreferences(
        context: Context
    ): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    @JvmStatic
    @Provides
    @Singleton
    fun provider(
        @Named(NAMED_NETWORK_MODE_PREFS) sharedPreferences: SharedPreferences
    ): NetworkModeProvider = DefaultNetworkModeProvider(sharedPreferences)

    @JvmStatic
    @Provides
    @Singleton
    fun provideNetworkConnectionStatus(
        context: Context,
        dispatcher: AppCoroutineDispatchers,
        blockRepository: BlockRepository,
        @Named(ConfigModule.DEFAULT_APP_COROUTINE_SCOPE) scope: CoroutineScope
    ): NetworkConnectionStatus = NetworkConnectionStatusImpl(
        context = context,
        dispatchers = dispatcher,
        coroutineScope = scope,
        blockRepository = blockRepository
    )
}