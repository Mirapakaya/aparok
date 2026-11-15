package com.aparokio.aparok.domain.payments

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

/**
 * Second step of the membership code-redemption flow: redeem the validated code and activate the
 * membership. The any-name is sent empty (name selection is not part of this flow), matching desktop.
 */
class RedeemMembershipCode @Inject constructor(
    dispatchers: AppCoroutineDispatchers,
    private val repo: BlockRepository
) : ResultInteractor<RedeemMembershipCode.Params, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Params) {
        val command = Command.Membership.CodeRedeem(
            code = params.code
        )
        repo.membershipCodeRedeem(command)
    }

    data class Params(
        val code: String
    )
}
