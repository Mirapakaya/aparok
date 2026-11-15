package com.aparokio.aparok.domain.multiplayer

import com.aparokio.aparok.core_models.multiplayer.SpaceInviteLinkAccessLevel
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.invite.SpaceInviteLinkStore
import javax.inject.Inject

class RevokeSpaceInviteLink @Inject constructor(
    private val repo: BlockRepository,
    private val store: SpaceInviteLinkStore,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<SpaceId, Unit>(dispatchers.io) {
    override suspend fun doWork(params: SpaceId) {
        val result = repo.revokeSpaceInviteLink(space = params)

        // Update the store to reflect that the invite link is now disabled
        store.update(params, SpaceInviteLinkAccessLevel.LinkDisabled())

        return result
    }
}