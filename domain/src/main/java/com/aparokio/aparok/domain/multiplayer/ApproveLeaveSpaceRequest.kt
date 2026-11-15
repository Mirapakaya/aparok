package com.aparokio.aparok.domain.multiplayer

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class ApproveLeaveSpaceRequest @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<ApproveLeaveSpaceRequest.Params, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Params) {
        repo.approveSpaceLeaveRequest(
            command = Command.ApproveSpaceLeaveRequest(
                space = params.space,
                identities = params.identities
            )
        )
    }

    data class Params(
        val space: SpaceId,
        val identities: List<Id>
    )
}