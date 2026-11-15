package com.aparokio.aparok.data.auth.event

import com.aparokio.aparok.core_models.FileLimitsEvent
import com.aparokio.aparok.domain.workspace.FileLimitsEventChannel
import kotlinx.coroutines.flow.Flow

class FileLimitsDataChannel(
    private val channel: FileLimitsRemoteChannel
) : FileLimitsEventChannel {

    override fun observe(): Flow<List<FileLimitsEvent>> {
        return channel.observe()
    }
}