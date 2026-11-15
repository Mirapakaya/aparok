package com.aparokio.aparok.domain.event.interactor

import com.aparokio.aparok.core_models.multiplayer.SpaceSyncAndP2PStatusState
import kotlinx.coroutines.flow.Flow

interface SpaceSyncAndP2PStatusProvider {
    fun observe(): Flow<SpaceSyncAndP2PStatusState>
}