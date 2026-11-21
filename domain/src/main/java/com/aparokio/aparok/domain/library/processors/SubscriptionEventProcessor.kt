package com.aparokio.aparok.domain.library.processors

import com.aparokio.aparok.core_models.SubscriptionEvent
import com.aparokio.aparok.domain.library.SubscriptionObject

interface SubscriptionEventProcessor<T: SubscriptionEvent> {
    fun process(
        event: T,
        dataItems: MutableList<SubscriptionObject>
    ): MutableList<SubscriptionObject>
}