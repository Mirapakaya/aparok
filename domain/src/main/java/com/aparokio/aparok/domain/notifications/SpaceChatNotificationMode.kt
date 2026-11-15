package com.aparokio.aparok.domain.notifications

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_models.chats.NotificationState
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

/**
 * Use case for setting notification mode for a specific space.
 */
class SetSpaceNotificationMode @Inject constructor(
    private val repository: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<SetSpaceNotificationMode.Params, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Params) {
        repository.setSpaceNotificationMode(
            spaceViewId = params.spaceViewId,
            mode = params.mode
        )
    }

    data class Params(
        val spaceViewId: Id,
        val mode: NotificationState
    )
}

/**
 * Use case for setting notification mode for a specific chat.
 */
class SetChatNotificationMode @Inject constructor(
    private val repository: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<SetChatNotificationMode.Params, Payload>(dispatchers.io) {

    override suspend fun doWork(params: Params): Payload {
        val command = Command.SpaceChatsNotifications.SetForceModeIds(
            spaceId = params.space.id,
            chatIds = params.chatIds,
            mode = params.mode
        )
        return repository.setSpaceChatsNotifications(command)
    }

    data class Params(
        val space: SpaceId,
        val chatIds: List<Id>,
        val mode: NotificationState
    )
}

/**
 * Use case for resetting notification mode for a specific chat.
 */
class ResetSpaceChatNotification @Inject constructor(
    private val repository: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<ResetSpaceChatNotification.Params, Payload>(dispatchers.io) {

    override suspend fun doWork(params: Params): Payload {
        val command = Command.SpaceChatsNotifications.ResetIds(
            spaceId = params.space.id,
            chatIds = listOf(params.chatId)
        )
        return repository.resetSpaceChatsNotifications(command)
    }

    data class Params(
        val space: SpaceId,
        val chatId: Id
    )
}