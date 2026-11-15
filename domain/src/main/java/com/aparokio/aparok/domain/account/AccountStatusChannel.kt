package com.aparokio.aparok.domain.account

import com.aparokio.aparok.core_models.AccountStatus
import kotlinx.coroutines.flow.Flow

interface AccountStatusChannel {
    fun observe(): Flow<AccountStatus>
}