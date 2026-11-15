package com.aparokio.aparok.domain.multiplayer

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class SendJoinSpaceRequest @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
): ResultInteractor<SendJoinSpaceRequest.Params, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Params) {
        repo.sendJoinSpaceRequest(
            Command.SendJoinSpaceRequest(
                space = params.space,
                network = params.network,
                inviteContentId = params.inviteContentId,
                inviteFileKey = params.inviteFileKey
            )
        )
    }

    /**
     * @property [network] provide network id in case of the self-hosted configuration.
     */
    data class Params(
        val space: SpaceId,
        val network: Id?,
        val inviteContentId: Id,
        val inviteFileKey: Id
    )
}