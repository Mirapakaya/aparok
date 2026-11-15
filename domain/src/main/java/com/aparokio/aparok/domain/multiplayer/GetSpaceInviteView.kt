package com.aparokio.aparok.domain.multiplayer

import com.aparokio.aparok.core_models.multiplayer.SpaceInviteView
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class GetSpaceInviteView @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<GetSpaceInviteView.Params, SpaceInviteView>(dispatchers.io) {

    override suspend fun doWork(params: Params): SpaceInviteView {
        return repo.getSpaceInviteView(
            inviteFileKey = params.inviteFileKey,
            inviteContentId = params.inviteContentId
        )
    }

    data class Params(
        val inviteContentId: String,
        val inviteFileKey: String
    )
}