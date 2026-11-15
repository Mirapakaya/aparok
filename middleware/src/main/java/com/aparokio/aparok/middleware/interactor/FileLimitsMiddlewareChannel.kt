package com.aparokio.aparok.middleware.interactor
import com.aparokio.aparok.middleware.EventGroup

import com.aparokio.aparok.core_models.FileLimitsEvent
import com.aparokio.aparok.data.auth.event.FileLimitsRemoteChannel
import com.aparokio.aparok.middleware.EventProxy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

class FileLimitsMiddlewareChannel(
    private val events: EventProxy
) : FileLimitsRemoteChannel {

    override fun observe(): Flow<List<FileLimitsEvent>> =
        events.flow(EventGroup.FILE)
            .mapNotNull { emission ->
                emission.messages.mapNotNull { message ->
                    when {
                        message.fileSpaceUsage != null -> {
                            val event = message.fileSpaceUsage
                            checkNotNull(event)
                            FileLimitsEvent.SpaceUsage(
                                space = event.spaceId,
                                bytesUsage = event.bytesUsage
                            )
                        }
                        message.fileLocalUsage != null -> {
                            val event = message.fileLocalUsage
                            checkNotNull(event)
                            FileLimitsEvent.LocalUsage(
                                bytesUsage = event.localBytesUsage
                            )
                        }
                        message.fileLimitReached!= null -> {
                            val event = message.fileLimitReached
                            checkNotNull(event)
                            FileLimitsEvent.FileLimitReached(
                                fileId = event.fileId,
                                spaceId = event.spaceId
                            )
                        }
                        message.fileLimitUpdated != null -> {
                            val event = message.fileLimitUpdated
                            checkNotNull(event)
                            FileLimitsEvent.FileLimitUpdated(
                                bytesLimit = event.bytesLimit
                            )
                        }
                        else -> null
                    }
                }
            }
}