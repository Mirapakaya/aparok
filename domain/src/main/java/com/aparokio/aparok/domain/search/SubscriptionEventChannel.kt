package com.aparokio.aparok.domain.search

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.SubscriptionEvent
import kotlinx.coroutines.flow.Flow

/**
 * Channel for events related to changes and transformations of objects.
 * @see SubscriptionEvent
 */
interface SubscriptionEventChannel {
    fun subscribe(subscriptions: List<Id>): Flow<List<SubscriptionEvent>>
}