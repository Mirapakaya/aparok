package com.aparokio.aparok.domain.chats

import com.aparokio.aparok.core_models.chats.PushKeyUpdate
import kotlinx.coroutines.flow.Flow

interface PushKeyChannel {
    fun observe(): Flow<PushKeyUpdate>
}