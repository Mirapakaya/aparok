package com.aparokio.aparok.domain.chats

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.chats.Chat
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class GetChatMessages @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
): ResultInteractor<Command.ChatCommand.GetMessages, Command.ChatCommand.GetMessages.Response>(dispatchers.io) {


    override suspend fun doWork(params: Command.ChatCommand.GetMessages): Command.ChatCommand.GetMessages.Response {
        return repo.getChatMessages(params)
    }
}