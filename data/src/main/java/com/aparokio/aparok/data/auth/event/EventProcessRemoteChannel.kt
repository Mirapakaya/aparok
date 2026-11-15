package com.aparokio.aparok.data.auth.event

import com.aparokio.aparok.core_models.Process
import com.aparokio.aparok.domain.workspace.EventProcessDropFilesChannel
import com.aparokio.aparok.domain.workspace.EventProcessImportChannel
import com.aparokio.aparok.domain.workspace.EventProcessMigrationChannel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

interface EventProcessImportRemoteChannel {
    fun observe(): Flow<List<Process.Event.Import>>
}

class EventProcessImportDateChannel(
    private val channel: EventProcessImportRemoteChannel
) : EventProcessImportChannel {

    override fun observe(): Flow<List<Process.Event.Import>> {
        return channel.observe()
    }
}

interface EventProcessDropFilesRemoteChannel {
    fun observe(): Flow<List<Process.Event.DropFiles>>
}

class EventProcessDropFilesDateChannel(
    private val channel: EventProcessDropFilesRemoteChannel
) : EventProcessDropFilesChannel {

    override fun observe(): Flow<List<Process.Event.DropFiles>> {
        return channel.observe()
    }
}

interface EventProcessMigrationRemoteChannel {
    fun observe(): Flow<List<Process.Event.Migration>>
}

class EventProcessMigrationDateChannel @Inject constructor(
    private val channel: EventProcessMigrationRemoteChannel
) : EventProcessMigrationChannel {

    override fun observe(): Flow<List<Process.Event.Migration>> {
        return channel.observe()
    }
}
