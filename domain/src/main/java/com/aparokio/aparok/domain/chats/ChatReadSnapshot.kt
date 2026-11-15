package com.aparokio.aparok.domain.chats

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.chats.Chat

/** The message orders and chat state rendered together by the UI. */
@ConsistentCopyVisibility
data class ChatReadSnapshot internal constructor(
    internal val owner: Any,
    internal val orders: Map<Id, Id>,
    val state: Chat.State
)
