package com.aparokio.aparok.domain.payments

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

/**
 * First step of the membership code-redemption flow: validate the code and learn which tier it
 * unlocks. Returns the `requestedTier`. Throws [com.aparokio.aparok.core_models.membership.MembershipErrors.CodeGetInfo]
 * on a non-NULL backend error.
 */
class GetMembershipCodeInfo @Inject constructor(
    dispatchers: AppCoroutineDispatchers,
    private val repo: BlockRepository
) : ResultInteractor<GetMembershipCodeInfo.Params, Int>(dispatchers.io) {

    override suspend fun doWork(params: Params): Int {
        val command = Command.Membership.CodeGetInfo(
            code = params.code
        )
        return repo.membershipCodeGetInfo(command)
    }

    data class Params(
        val code: String
    )
}
