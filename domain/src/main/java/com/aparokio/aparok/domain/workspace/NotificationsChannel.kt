package com.aparokio.aparok.domain.workspace

import com.aparokio.aparok.core_models.Notification
import kotlinx.coroutines.flow.Flow

interface NotificationsChannel {
    fun observe(): Flow<List<Notification.Event>>
}