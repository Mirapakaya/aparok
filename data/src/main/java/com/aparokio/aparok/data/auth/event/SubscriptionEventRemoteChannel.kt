package com.aparokio.aparok.data.auth.event

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.SubscriptionEvent
import kotlinx.coroutines.flow.Flow

interface SubscriptionEventRemoteChannel {
    fun subscribe(subscriptions: List<Id>): Flow<List<SubscriptionEvent>>
}