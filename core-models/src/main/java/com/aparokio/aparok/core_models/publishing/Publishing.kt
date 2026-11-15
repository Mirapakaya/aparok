package com.aparokio.aparok.core_models.publishing

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Struct
import com.aparokio.aparok.core_models.primitives.Space

object Publishing {

    enum class Status {
        CREATED,
        PUBLISHED
    }

    class State(
        val obj: Id,
        val space: Space,
        val uri: String,
        val status: Status,
        val timestamp: Long,
        val size: Long,
        val version: String,
        val details: Struct,
        val showJoinSpaceButton: Boolean
    )

}