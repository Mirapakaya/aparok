package com.aparokio.aparok.data.auth.event

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.domain.search.SubscriptionEventChannel

class SubscriptionDataChannel(
    private val remote: SubscriptionEventRemoteChannel
) : SubscriptionEventChannel {
    override fun subscribe(subscriptions: List<Id>) = remote.subscribe(subscriptions)
}