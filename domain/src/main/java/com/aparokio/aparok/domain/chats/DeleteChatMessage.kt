package com.aparokio.aparok.domain.chats

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class DeleteChatMessage @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<Command.ChatCommand.DeleteMessage, Unit>(dispatchers.io) {
    override suspend fun doWork(params: Command.ChatCommand.DeleteMessage) {
        return repo.deleteChatMessage(command = params)
    }
}