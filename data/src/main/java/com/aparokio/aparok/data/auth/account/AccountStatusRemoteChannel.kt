package com.aparokio.aparok.data.auth.account

import com.aparokio.aparok.core_models.AccountStatus
import kotlinx.coroutines.flow.Flow

interface AccountStatusRemoteChannel {
    fun observe(): Flow<AccountStatus>
}