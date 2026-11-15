package com.aparokio.aparok.domain.library.processors

import com.aparokio.aparok.core_models.SubscriptionEvent
import com.aparokio.aparok.domain.library.SubscriptionObject

class EventRemoveProcessor : SubscriptionEventProcessor<SubscriptionEvent.Remove> {

    override fun process(
        event: SubscriptionEvent.Remove,
        dataItems: MutableList<SubscriptionObject>
    ): MutableList<SubscriptionObject> = with(dataItems) {
        retainAll {
            it.id != event.target
        }
        return this
    }

}