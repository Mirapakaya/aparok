package com.aparokio.aparok.domain.event.interactor

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Event
import kotlinx.coroutines.flow.Flow

interface EventChannel {
    fun observeEvents(context: Id? = null): Flow<List<Event>>
}