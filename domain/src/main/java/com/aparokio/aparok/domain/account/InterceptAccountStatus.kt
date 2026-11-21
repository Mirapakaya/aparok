package com.aparokio.aparok.domain.account

import com.aparokio.aparok.core_models.AccountStatus
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class InterceptAccountStatus(
    private val channel: AccountStatusChannel,
    private val dispatchers: AppCoroutineDispatchers
) : FlowUseCase<AccountStatus, BaseUseCase.None>() {
    override fun build(params: BaseUseCase.None?): Flow<AccountStatus> {
        return channel.observe().flowOn(dispatchers.io)
    }
}