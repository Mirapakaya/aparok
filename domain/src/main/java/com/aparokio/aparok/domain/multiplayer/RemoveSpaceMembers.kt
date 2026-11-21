package com.aparokio.aparok.domain.multiplayer

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class RemoveSpaceMembers @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
): ResultInteractor<RemoveSpaceMembers.Params, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Params) {
        repo.removeSpaceMembers(
            space = params.space,
            identities = params.identities
        )
    }

    /**
     * @property [identities] identities of members of a given space
     */
    data class Params(val space: SpaceId, val identities: List<Id>)
}