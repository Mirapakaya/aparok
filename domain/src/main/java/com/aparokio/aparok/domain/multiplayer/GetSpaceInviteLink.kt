package com.aparokio.aparok.domain.multiplayer

import com.aparokio.aparok.core_models.multiplayer.SpaceInviteError
import com.aparokio.aparok.core_models.multiplayer.SpaceInviteLink
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class GetSpaceInviteLink @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<SpaceId, SpaceInviteLink>(dispatchers.io) {

    override suspend fun doWork(params: SpaceId): SpaceInviteLink {
        val invite = repo.getSpaceInviteLink(spaceId = params)
        // A member's device gets a successful response with empty cid + key when
        // the invite is held by the owner. There is no link to hand out in that case.
        if (!invite.isLinkVisible) throw SpaceInviteError.InviteNotActive
        return invite
    }
}