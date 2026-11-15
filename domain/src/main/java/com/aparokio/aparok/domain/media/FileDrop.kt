package com.aparokio.aparok.domain.media

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.NO_VALUE
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_models.Position
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class FileDrop @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<FileDrop.Params, Payload>(dispatchers.io) {

    override suspend fun doWork(params: Params): Payload = repo.fileDrop(
        command = Command.FileDrop(
            ctx = params.ctx,
            space = params.space,
            dropTarget = params.dropTarget,
            blockPosition = params.blockPosition,
            localFilePaths = params.localFilePaths
        )
    )

    data class Params(
        val space: SpaceId,
        val ctx: Id,
        val dropTarget: Id = NO_VALUE,
        val blockPosition: Position = Position.NONE,
        val localFilePaths: List<String>
    )
}