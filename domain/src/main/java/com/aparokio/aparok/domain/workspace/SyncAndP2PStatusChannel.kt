package com.aparokio.aparok.domain.workspace

import com.aparokio.aparok.core_models.multiplayer.P2PStatusUpdate
import com.aparokio.aparok.core_models.multiplayer.SpaceSyncUpdate
import kotlinx.coroutines.flow.Flow

interface SyncAndP2PStatusChannel {
    fun p2pStatus(): Flow<Map<String, P2PStatusUpdate>>
    fun syncStatus(): Flow<Map<String, SpaceSyncUpdate>>
}