package com.aparokio.aparok.domain.payments

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.membership.NameServiceNameType
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class IsMembershipNameValid @Inject constructor(
    dispatchers: AppCoroutineDispatchers,
    private val repo: BlockRepository
) : ResultInteractor<IsMembershipNameValid.Params, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Params) {
        val command = Command.Membership.IsNameValid(
            tier = params.tier,
            name = params.name,
            nameType = params.nameType
        )
        repo.membershipIsNameValid(command)
    }

    data class Params(
        val tier: Int,
        val name: String,
        val nameType: NameServiceNameType = NameServiceNameType.ANY_NAME
    )
}