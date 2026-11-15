package com.aparokio.aparok.domain.page

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

/**
 * Use-case for closing a smart block by id.
 */
open class CloseObject @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<CloseObject.Params, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Params) {
        repo.closeObject(
            id = params.target,
            space = params.space
        )
    }

    data class Params(
        val target: Id,
        val space: SpaceId
    )
}