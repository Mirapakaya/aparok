package com.aparokio.aparok.middleware.interactor
import com.aparokio.aparok.middleware.EventGroup

import com.aparokio.aparok.core_models.Notification
import com.aparokio.aparok.data.auth.event.NotificationsRemoteChannel
import com.aparokio.aparok.middleware.EventProxy
import com.aparokio.aparok.middleware.mappers.toCoreModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.mapNotNull

class NotificationsMiddlewareChannel(
    private val eventsProxy: EventProxy
) : NotificationsRemoteChannel {

    override fun observe(): Flow<List<Notification.Event>> {
        return eventsProxy.flow(EventGroup.NOTIFICATIONS)
            .mapNotNull { emission ->
                emission.messages.mapNotNull { message ->
                    when {
                        message.notificationUpdate != null -> {
                            val event = message.notificationUpdate
                            checkNotNull(event)
                            Notification.Event.Update(
                                notification = event.notification?.toCoreModel()
                            )
                        }
                        message.notificationSend != null -> {
                            val event = message.notificationSend
                            checkNotNull(event)
                            Notification.Event.Send(
                                notification = event.notification?.toCoreModel()
                            )
                        }
                        else -> null
                    }
                }
            }.filter { events -> events.isNotEmpty() }
    }
}