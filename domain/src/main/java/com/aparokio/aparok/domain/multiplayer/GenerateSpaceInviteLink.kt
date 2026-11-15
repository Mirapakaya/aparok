package com.aparokio.aparok.domain.multiplayer

import com.aparokio.aparok.core_models.multiplayer.InviteType
import com.aparokio.aparok.core_models.multiplayer.SpaceInviteLink
import com.aparokio.aparok.core_models.multiplayer.SpaceMemberPermissions
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class GenerateSpaceInviteLink @Inject constructor(
    dispatchers: AppCoroutineDispatchers,
    private val repo: BlockRepository
) : ResultInteractor<GenerateSpaceInviteLink.Params, SpaceInviteLink>(dispatchers.io) {

    override suspend fun doWork(params: Params): SpaceInviteLink {
        return repo.generateSpaceInviteLink(
            space = params.space,
            inviteType = params.inviteType,
            permissions = params.permissions,
            shareWithinSpace = params.shareWithinSpace
        )
    }

    data class Params(
        val space: SpaceId,
        val inviteType: InviteType?,
        val permissions: SpaceMemberPermissions?,
        val shareWithinSpace: Boolean = false
    )
}