package com.aparokio.aparok.domain.multiplayer

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.multiplayer.SpaceMemberPermissions
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class ChangeSpaceMemberPermissions @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<ChangeSpaceMemberPermissions.Params, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Params) {
        repo.changeSpaceMemberPermissions(
            space = params.space,
            identity = params.identity,
            permission = params.permission
        )
    }

    data class Params(
        val space: SpaceId,
        val identity: Id,
        val permission: SpaceMemberPermissions
    )
}