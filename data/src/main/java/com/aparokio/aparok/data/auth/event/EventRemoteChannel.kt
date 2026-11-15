package com.aparokio.aparok.data.auth.event

import com.aparokio.aparok.core_models.Event
import kotlinx.coroutines.flow.Flow

interface EventRemoteChannel {
    fun observeEvents(context: String? = null): Flow<List<Event>>
}