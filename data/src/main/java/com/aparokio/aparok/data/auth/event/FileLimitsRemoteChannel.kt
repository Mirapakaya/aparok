package com.aparokio.aparok.data.auth.event

import com.aparokio.aparok.core_models.FileLimitsEvent
import kotlinx.coroutines.flow.Flow

interface FileLimitsRemoteChannel {
    fun observe(): Flow<List<FileLimitsEvent>>
}