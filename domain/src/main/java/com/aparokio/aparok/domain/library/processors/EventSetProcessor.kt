package com.aparokio.aparok.domain.library.processors

import com.aparokio.aparok.core_models.ObjectWrapper
import com.aparokio.aparok.core_models.SubscriptionEvent
import com.aparokio.aparok.domain.library.SubscriptionObject

class EventSetProcessor : SubscriptionEventProcessor<SubscriptionEvent.Set> {

    override fun process(
        event: SubscriptionEvent.Set,
        dataItems: MutableList<SubscriptionObject>
    ): MutableList<SubscriptionObject> = with(dataItems) {
        val indexOfItem = indexOfFirst { it.id == event.target }
        if (indexOfItem != -1) {
            set(
                indexOfItem,
                SubscriptionObject(
                    event.target,
                    ObjectWrapper.Basic(event.data)
                )
            )
        } else {
            add(
                0,
                SubscriptionObject(
                    event.target,
                    ObjectWrapper.Basic(event.data)
                )
            )
        }
        return this
    }

}