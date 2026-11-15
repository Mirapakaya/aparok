package com.aparokio.aparok.data.auth.event

import com.aparokio.aparok.core_models.Event
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.domain.chats.ChatEventChannel
import kotlinx.coroutines.flow.Flow

interface ChatEventRemoteChannel {
    fun observe(chat: Id): Flow<List<Event.Command.Chats>>
    fun subscribe(subscription: Id): Flow<List<Event.Command.Chats>>
    class Default(
        private val channel: ChatEventRemoteChannel
    ) : ChatEventChannel {

        override fun subscribe(subscribe: Id): Flow<List<Event.Command.Chats>> {
            return channel.subscribe(subscribe)
        }

        override fun observe(chat: Id): Flow<List<Event.Command.Chats>> {
            return channel.observe(chat)
        }
    }
}