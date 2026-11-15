package com.aparokio.aparok.domain.payments

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.membership.MembershipTierData
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class GetMembershipTiers @Inject constructor(
    dispatchers: AppCoroutineDispatchers,
    private val repo: BlockRepository
) : ResultInteractor<GetMembershipTiers.Params, List<MembershipTierData>>(dispatchers.io) {

    override suspend fun doWork(params: Params): List<MembershipTierData> {
        val command = Command.Membership.GetTiers(
            locale = params.locale,
            noCache = params.noCache
        )
        return repo.membershipGetTiers(command)
    }

    data class Params(
        val locale: String,
        val noCache: Boolean
    )
}