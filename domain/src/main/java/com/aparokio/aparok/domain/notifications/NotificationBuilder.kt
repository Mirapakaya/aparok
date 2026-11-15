package com.aparokio.aparok.domain.notifications

import com.aparokio.aparok.core_models.DecryptedPushContent
import com.aparokio.aparok.core_models.Id

interface NotificationBuilder {
    suspend fun buildAndNotify(message: DecryptedPushContent.Message, spaceId: Id, groupId: String)
    fun clearNotificationChannel(spaceId: String, chatId: String)
    fun clearNotificationsByGroupId(groupId: String)
}