package com.aparokio.aparok.domain.media

import com.aparokio.aparok.core_models.Block
import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

/**
 * Preloads file for uploading.
 * @return id of the file
 * @see [Params]
 */
class PreloadFile @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<PreloadFile.Params, Id>(dispatchers.io) {

    override suspend fun doWork(params: Params) : Id = repo.preloadFile(
        command = Command.PreloadFile(
            path = params.path,
            type = params.type,
            space = params.space
        )
    )

    data class Params(
        val path: String,
        val space: SpaceId,
        val type: Block.Content.File.Type = Block.Content.File.Type.FILE
    )
}