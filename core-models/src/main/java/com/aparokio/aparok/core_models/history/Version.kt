package com.aparokio.aparok.core_models.history

import com.aparokio.aparok.core_models.Event
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.ObjectView
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_models.primitives.TimestampInSeconds

data class Version(
    val id: Id,
    val previousIds: List<Id>,
    val spaceMember: Id,
    val spaceMemberName: String,
    val timestamp: TimestampInSeconds,
    val groupId: Long
)

data class ShowVersionResponse(
    val payload: Payload?,
    val version: Version?,
    val traceId: Id
)

data class DiffVersionResponse(
    val historyEvents: List<Event.Command>,
    val objectView: ObjectView?
)
