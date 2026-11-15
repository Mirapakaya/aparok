package com.aparokio.aparok.domain.multiplayer

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Relations
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class DeclineSpaceJoinRequest @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<DeclineSpaceJoinRequest.Params, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Params) {
        repo.declineSpaceRequest(
            space = params.space,
            identity = params.identity
        )
    }

    /**
     * @property [identity] members identity
     * @see Relations.IDENTITY
     */
    data class Params(
        val space: SpaceId,
        val identity: Id
    )
}