package com.aparokio.aparok.domain.payments

import com.aparokio.aparok.core_models.membership.EmailVerificationStatus
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class GetMembershipEmailStatus @Inject constructor(
    dispatchers: AppCoroutineDispatchers,
    private val repo: BlockRepository
) : ResultInteractor<Unit, EmailVerificationStatus>(dispatchers.io) {

    override suspend fun doWork(params: Unit): EmailVerificationStatus {
        return repo.membershipGetVerificationEmailStatus()
    }
}