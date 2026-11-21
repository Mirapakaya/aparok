package com.aparokio.aparok.data.auth.event

import com.aparokio.aparok.core_models.Notification
import com.aparokio.aparok.domain.workspace.NotificationsChannel
import kotlinx.coroutines.flow.Flow

interface NotificationsRemoteChannel {
    fun observe(): Flow<List<Notification.Event>>
}

class NotificationsDateChannel(
    private val channel: NotificationsRemoteChannel
) : NotificationsChannel {

    override fun observe(): Flow<List<Notification.Event>> {
        return channel.observe()
    }
}