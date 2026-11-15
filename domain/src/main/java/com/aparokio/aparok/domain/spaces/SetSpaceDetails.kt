package com.aparokio.aparok.domain.spaces

import com.aparokio.aparok.core_models.Struct
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class SetSpaceDetails @Inject constructor(
    dispatchers: AppCoroutineDispatchers,
    private val repo: BlockRepository
) : ResultInteractor<SetSpaceDetails.Params, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Params) {
        repo.setSpaceDetails(
            space = params.space,
            details = params.details
        )
    }

    class Params(val space: SpaceId, val details: Struct)
}