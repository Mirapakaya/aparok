package com.aparokio.aparok.domain.multiplayer

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Relations
import com.aparokio.aparok.core_models.multiplayer.SpaceMemberPermissions
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class ApproveJoinSpaceRequest @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<ApproveJoinSpaceRequest.Params, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Params) {
        repo.approveSpaceRequest(
            space = params.space,
            identity = params.identity,
            permissions = params.permissions
        )
    }

    /**
     * @property [identity] members identity
     * @see Relations.IDENTITY
     */
    data class Params(
        val space: SpaceId,
        val identity: Id,
        val permissions: SpaceMemberPermissions
    )
}