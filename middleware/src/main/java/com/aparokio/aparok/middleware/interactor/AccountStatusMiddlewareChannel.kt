package com.aparokio.aparok.middleware.interactor
import com.aparokio.aparok.middleware.EventGroup

import com.aparokio.aparok.core_models.AccountStatus
import com.aparokio.aparok.data.auth.account.AccountStatusRemoteChannel
import com.aparokio.aparok.middleware.EventProxy
import com.aparokio.aparok.middleware.mappers.core
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

class AccountStatusMiddlewareChannel(
    private val events: EventProxy
) : AccountStatusRemoteChannel {

    override fun observe(): Flow<AccountStatus> = events.flow(EventGroup.ACCOUNT).mapNotNull { e ->
        val updates = e.messages.filter { m ->
            m.accountUpdate != null
        }
        val lastUpdate = updates.lastOrNull()?.accountUpdate

        lastUpdate?.status?.core()
    }
}