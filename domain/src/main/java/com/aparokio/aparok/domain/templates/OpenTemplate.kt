package com.aparokio.aparok.domain.templates

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.Result
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.templates.OpenTemplate.Params
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Use-case for opening a template object.
 * @see Params
 */
class OpenTemplate(
    private val repo: BlockRepository,
    private val dispatchers: AppCoroutineDispatchers
) : ResultInteractor<Params, Result<Payload>>(dispatchers.io) {

    override suspend fun doWork(params: Params): Result<Payload> {
        return withContext(dispatchers.io) {
            repo.openObjectPreview(
                id = params.id,
                space = params.space
            )
        }
    }

    /**
     * @property [id] id of the template object.
     */
    class Params(val id: Id, val space: SpaceId)
}