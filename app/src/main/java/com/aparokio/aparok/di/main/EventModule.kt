package com.aparokio.aparok.di.main

import com.aparokio.aparok.core_utils.tools.FeatureToggles
import com.aparokio.aparok.data.auth.account.AccountStatusDataChannel
import com.aparokio.aparok.data.auth.account.AccountStatusRemoteChannel
import com.aparokio.aparok.data.auth.event.ChatEventRemoteChannel
import com.aparokio.aparok.data.auth.event.EventDataChannel
import com.aparokio.aparok.data.auth.event.EventRemoteChannel
import com.aparokio.aparok.data.auth.event.FileLimitsDataChannel
import com.aparokio.aparok.data.auth.event.FileLimitsRemoteChannel
import com.aparokio.aparok.data.auth.event.SubscriptionDataChannel
import com.aparokio.aparok.data.auth.event.SubscriptionEventRemoteChannel
import com.aparokio.aparok.data.auth.status.SyncAndP2PStatusEventsStore
import com.aparokio.aparok.di.main.ConfigModule.DEFAULT_APP_COROUTINE_SCOPE
import com.aparokio.aparok.domain.account.AccountStatusChannel
import com.aparokio.aparok.domain.chats.ChatEventChannel
import com.aparokio.aparok.domain.event.interactor.EventChannel
import com.aparokio.aparok.domain.search.SubscriptionEventChannel
import com.aparokio.aparok.domain.workspace.FileLimitsEventChannel
import com.aparokio.aparok.middleware.EventProxy
import com.aparokio.aparok.middleware.interactor.AccountStatusMiddlewareChannel
import com.aparokio.aparok.middleware.interactor.DefaultMiddlewareEventRegistrar
import com.aparokio.aparok.middleware.interactor.EventHandler
import com.aparokio.aparok.middleware.interactor.EventHandlerChannel
import com.aparokio.aparok.middleware.interactor.EventHandlerChannelImpl
import com.aparokio.aparok.middleware.interactor.FileLimitsMiddlewareChannel
import com.aparokio.aparok.middleware.interactor.MiddlewareEventChannel
import com.aparokio.aparok.middleware.interactor.MiddlewareEventRegistrar
import com.aparokio.aparok.middleware.interactor.MiddlewareProtobufLogger
import com.aparokio.aparok.middleware.interactor.MiddlewareSubscriptionEventChannel
import com.aparokio.aparok.middleware.interactor.SyncAndP2PStatusEventsStoreImpl
import com.aparokio.aparok.middleware.interactor.events.ChatEventMiddlewareChannel
import com.aparokio.aparok.presentation.common.PayloadDelegator
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope

@Module(includes = [EventModule.Bindings::class])
object EventModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideEventChannel(
        channel: EventDataChannel
    ): EventChannel = channel

    @JvmStatic
    @Provides
    @Singleton
    fun provideEventDataChannel(
        remote: EventRemoteChannel
    ): EventDataChannel = EventDataChannel(remote)

    @JvmStatic
    @Provides
    @Singleton
    fun provideEventRemoteChannel(
        proxy: EventProxy,
        featureToggles: FeatureToggles
    ): EventRemoteChannel = MiddlewareEventChannel(events = proxy, featureToggles = featureToggles)

    @JvmStatic
    @Provides
    @Singleton
    fun provideAccountStatusChannel(
        channel: AccountStatusDataChannel
    ) : AccountStatusChannel = channel

    @JvmStatic
    @Provides
    @Singleton
    fun provideAccountStatusDataChannel(
        channel: AccountStatusRemoteChannel
    ) = AccountStatusDataChannel(
        remote = channel
    )

    @JvmStatic
    @Provides
    @Singleton
    fun provideAccountStatusRemoteChannel(
        proxy: EventProxy
    ) : AccountStatusRemoteChannel = AccountStatusMiddlewareChannel(
        events = proxy
    )

    @JvmStatic
    @Provides
    @Singleton
    fun provideSubscriptionEventChannel(
        channel: SubscriptionDataChannel
    ): SubscriptionEventChannel = channel

    @JvmStatic
    @Provides
    @Singleton
    fun provideSubscriptionEventDataChannel(
        remote: SubscriptionEventRemoteChannel
    ): SubscriptionDataChannel = SubscriptionDataChannel(remote)

    @JvmStatic
    @Provides
    @Singleton
    fun provideSubscriptionEventRemoteChannel(
        proxy: EventProxy,
        @Named(DEFAULT_APP_COROUTINE_SCOPE) scope: CoroutineScope
    ): SubscriptionEventRemoteChannel = MiddlewareSubscriptionEventChannel(
        events = proxy,
        scope = scope
    )

    @JvmStatic
    @Provides
    @Singleton
    fun provideFileLimitsRemoteChannel(
        proxy: EventProxy
    ): FileLimitsRemoteChannel = FileLimitsMiddlewareChannel(events = proxy)

    @JvmStatic
    @Provides
    @Singleton
    fun provideFileLimitsDataChannel(
        channel: FileLimitsRemoteChannel
    ): FileLimitsEventChannel = FileLimitsDataChannel(channel = channel)

    @JvmStatic
    @Provides
    @Singleton
    fun provideEventHandler(
        logger: MiddlewareProtobufLogger,
        @Named(DEFAULT_APP_COROUTINE_SCOPE) scope: CoroutineScope,
        channel: EventHandlerChannel,
        syncP2PStore: SyncAndP2PStatusEventsStore,
        registrar: MiddlewareEventRegistrar
    ): EventProxy = EventHandler(
        scope = scope,
        logger = logger,
        channel = channel,
        syncP2PStore = syncP2PStore,
        registrar = registrar
    )

    @JvmStatic
    @Provides
    @Singleton
    fun provideMiddlewareEventRegistrar(): MiddlewareEventRegistrar = DefaultMiddlewareEventRegistrar()

    @JvmStatic
    @Provides
    @Singleton
    fun provideSyncAndP2PStatusEventsSubscription(
        @Named(DEFAULT_APP_COROUTINE_SCOPE) scope: CoroutineScope,
        channel: EventHandlerChannel
    ): SyncAndP2PStatusEventsStore = SyncAndP2PStatusEventsStoreImpl(
        scope = scope,
        channel = channel
    )

    @JvmStatic
    @Provides
    @Singleton
    fun provideDefaultEventChannel(): EventHandlerChannel = EventHandlerChannelImpl()

    //region Chats

    @JvmStatic
    @Provides
    @Singleton
    fun provideChatEventChannel(
        channel: ChatEventRemoteChannel.Default
    ): ChatEventChannel = channel

    @JvmStatic
    @Provides
    @Singleton
    fun provideChatEventDataChannel(
        remote: ChatEventRemoteChannel
    ): ChatEventRemoteChannel.Default = ChatEventRemoteChannel.Default(
        channel = remote
    )

    @JvmStatic
    @Provides
    @Singleton
    fun provideChatEventMWChannel(
        proxy: EventProxy
    ): ChatEventRemoteChannel = ChatEventMiddlewareChannel(
        proxy
    )

    //endregion

    @Module
    interface Bindings {

        @Binds
        @Singleton
        fun payloadDelegator(default: PayloadDelegator.Default): PayloadDelegator
    }
}