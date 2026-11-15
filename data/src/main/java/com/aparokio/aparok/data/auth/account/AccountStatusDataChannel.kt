package com.aparokio.aparok.data.auth.account

import com.aparokio.aparok.core_models.AccountStatus
import com.aparokio.aparok.domain.account.AccountStatusChannel
import kotlinx.coroutines.flow.Flow

class AccountStatusDataChannel(
    private val remote: AccountStatusRemoteChannel
) : AccountStatusChannel {
    override fun observe(): Flow<AccountStatus> = remote.observe()
}