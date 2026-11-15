package com.aparokio.aparok.domain.chats

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Event
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

/**
 * returns message ID and payload commands.
 */
class AddChatMessage @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<Command.ChatCommand.AddMessage, Pair<Id, List<Event.Command.Chats>>>(dispatchers.io) {

    override suspend fun doWork(params: Command.ChatCommand.AddMessage): Pair<Id, List<Event.Command.Chats>> {
        return repo.addChatMessage(params)
    }
}