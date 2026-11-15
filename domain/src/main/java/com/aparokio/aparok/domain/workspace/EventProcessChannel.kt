package com.aparokio.aparok.domain.workspace

import com.aparokio.aparok.core_models.Process
import kotlinx.coroutines.flow.Flow

interface EventProcessImportChannel {
    fun observe(): Flow<List<Process.Event.Import>>
}

interface EventProcessDropFilesChannel {
    fun observe(): Flow<List<Process.Event.DropFiles>>
}

interface EventProcessMigrationChannel {
    fun observe(): Flow<List<Process.Event.Migration>>
}