package com.aparokio.aparok.domain.chats

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.chats.Chat
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class GetChatMessagesByIds @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<Command.ChatCommand.GetMessagesByIds, List<Chat.Message>>(dispatchers.io) {

    override suspend fun doWork(params: Command.ChatCommand.GetMessagesByIds): List<Chat.Message> {
        return repo.getChatMessagesByIds(params)
    }
}