package com.aparokio.aparok.domain.payments

import com.aparokio.aparok.core_models.membership.Membership
import com.aparokio.aparok.core_models.membership.MembershipPaymentMethod
import com.aparokio.aparok.core_models.membership.NameServiceNameType
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class GetMembershipStatus @Inject constructor(
    dispatchers: AppCoroutineDispatchers,
    private val repo: BlockRepository
) : ResultInteractor<GetMembershipStatus.Params, Membership?>(dispatchers.io) {

    override suspend fun doWork(params: Params): Membership? {
        return Membership(
            tier = 0,
            membershipStatusModel = Membership.Status.STATUS_ACTIVE,
            dateStarted = 0L,
            dateEnds = Long.MAX_VALUE,
            isAutoRenew = true,
            paymentMethod = MembershipPaymentMethod.METHOD_NONE,
            nameServiceName = "",
            nameServiceType = NameServiceNameType.ANY_NAME,
            userEmail = "",
            subscribeToNewsletter = false
        )
    }

    data class Params(val noCache: Boolean)
}
