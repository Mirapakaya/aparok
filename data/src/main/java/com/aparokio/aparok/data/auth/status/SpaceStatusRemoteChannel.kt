package com.aparokio.aparok.data.auth.status

import com.aparokio.aparok.core_models.multiplayer.P2PStatusUpdate
import com.aparokio.aparok.core_models.multiplayer.SpaceSyncUpdate
import com.aparokio.aparok.domain.workspace.SyncAndP2PStatusChannel
import kotlinx.coroutines.flow.Flow

class SyncAndP2PStatusDataChannel(
    private val store: SyncAndP2PStatusEventsStore
) : SyncAndP2PStatusChannel {

    override fun p2pStatus(): Flow<Map<String, P2PStatusUpdate>> = store.p2pStatus
    override fun syncStatus(): Flow<Map<String, SpaceSyncUpdate>> = store.syncStatus
}