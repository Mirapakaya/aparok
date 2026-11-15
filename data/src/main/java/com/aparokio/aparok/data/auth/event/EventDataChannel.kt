package com.aparokio.aparok.data.auth.event

import com.aparokio.aparok.core_models.Event
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.domain.event.interactor.EventChannel
import kotlinx.coroutines.flow.Flow

class EventDataChannel(private val remote: EventRemoteChannel) : EventChannel {

    override fun observeEvents(context: Id?): Flow<List<Event>> = remote.observeEvents(context)

}