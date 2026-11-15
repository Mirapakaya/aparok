package com.aparokio.aparok.di.main

import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences
import com.aparokio.aparok.app.AparokNotificationService
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.core_utils.notifications.NotificationPermissionManager
import com.aparokio.aparok.core_utils.notifications.NotificationPermissionManagerImpl
import com.aparokio.aparok.data.auth.event.NotificationsDateChannel
import com.aparokio.aparok.data.auth.event.NotificationsRemoteChannel
import com.aparokio.aparok.device.NotificationBuilderImpl
import com.aparokio.aparok.domain.account.AwaitAccountStartManager
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.chats.ChatsDetailsSubscriptionContainer
import com.aparokio.aparok.domain.chats.PushKeyChannel
import com.aparokio.aparok.domain.chats.PushKeySpaceViewChannel
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.notifications.NotificationBuilder
import com.aparokio.aparok.domain.notifications.PushKeyProvider
import com.aparokio.aparok.domain.notifications.SystemNotificationService
import com.aparokio.aparok.domain.resources.StringResourceProvider
import com.aparokio.aparok.domain.workspace.NotificationsChannel
import com.aparokio.aparok.middleware.EventProxy
import com.aparokio.aparok.middleware.interactor.NotificationsMiddlewareChannel
import com.aparokio.aparok.presentation.notifications.NotificationsProvider
import com.aparokio.aparok.presentation.notifications.PushKeyProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json

@Module
object NotificationsModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideNotificationsRemoteChannel(
        proxy: EventProxy
    ): NotificationsRemoteChannel = NotificationsMiddlewareChannel(
        eventsProxy = proxy
    )

    @JvmStatic
    @Provides
    @Singleton
    fun provideNotificationsChannel(
        channel: NotificationsRemoteChannel
    ): NotificationsChannel = NotificationsDateChannel(
        channel = channel
    )

    @JvmStatic
    @Provides
    @Singleton
    fun provideNotificator(
        context: Context
    ): SystemNotificationService = AparokNotificationService(context = context)

    @JvmStatic
    @Singleton
    @Provides
    fun provideNotificationsProvider(
        dispatchers: AppCoroutineDispatchers,
        @Named(ConfigModule.DEFAULT_APP_COROUTINE_SCOPE) scope: CoroutineScope,
        awaitAccountStartManager: AwaitAccountStartManager,
        notificationsChannel: NotificationsChannel
    ): NotificationsProvider = NotificationsProvider.Default(
        dispatchers = dispatchers,
        scope = scope,
        notificationsChannel = notificationsChannel,
        awaitAccountStartManager = awaitAccountStartManager,
    )

    @JvmStatic
    @Provides
    @Singleton
    fun providePushKeyProvider(
        @Named("encrypted") sharedPreferences: SharedPreferences,
        dispatchers: AppCoroutineDispatchers,
        @Named(ConfigModule.DEFAULT_APP_COROUTINE_SCOPE) scope: CoroutineScope,
        channel: PushKeyChannel,
        json: Json
    ): PushKeyProvider = PushKeyProviderImpl(
        sharedPreferences = sharedPreferences,
        dispatchers = dispatchers,
        scope = scope,
        channel = channel,
        json = json
    )

    @JvmStatic
    @Provides
    @Singleton
    fun providePushKeyChannel(
        spaceViewSubscriptionContainer: SpaceViewSubscriptionContainer
    ): PushKeyChannel = PushKeySpaceViewChannel(spaceViewSubscriptionContainer)

    @Provides
    @Singleton
    @JvmStatic
    fun provideNotificationPermissionManager(
        @Named("default") prefs: SharedPreferences,
        context: Context
    ): NotificationPermissionManager {
        return NotificationPermissionManagerImpl(
            sharedPreferences = prefs,
            context = context
        )
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideNotificationBuilder(
        context: Context,
        notificationManager: NotificationManager,
        stringResourceProvider: StringResourceProvider,
        urlBuilder: UrlBuilder,
        spaceViewSubscriptionContainer: SpaceViewSubscriptionContainer,
        chatsDetailsSubscriptionContainer: ChatsDetailsSubscriptionContainer
    ): NotificationBuilder = NotificationBuilderImpl(
        context = context,
        notificationManager = notificationManager,
        resourceProvider = stringResourceProvider,
        urlBuilder = urlBuilder,
        spaceViewSubscriptionContainer = spaceViewSubscriptionContainer,
        chatsDetailsSubscriptionContainer = chatsDetailsSubscriptionContainer
    )

    @JvmStatic
    @Provides
    @Singleton
    fun provideNotificationManager(
        context: Context
    ): NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
}