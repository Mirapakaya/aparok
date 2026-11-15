package com.aparokio.aparok.domain.chats

import com.aparokio.aparok.core_models.Event
import com.aparokio.aparok.core_models.Id
import kotlinx.coroutines.flow.Flow

interface ChatEventChannel {
    fun observe(chat: Id): Flow<List<Event.Command.Chats>>
    fun subscribe(subscribe: Id): Flow<List<Event.Command.Chats>>
}