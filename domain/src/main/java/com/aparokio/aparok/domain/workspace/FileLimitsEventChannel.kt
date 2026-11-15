package com.aparokio.aparok.domain.workspace

import com.aparokio.aparok.core_models.FileLimitsEvent
import kotlinx.coroutines.flow.Flow

interface FileLimitsEventChannel {
    fun observe() : Flow<List<FileLimitsEvent>>
}