package com.aparokio.aparok.domain.cover

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.block.repo.BlockRepository

class RemoveDocCover(private val repo: BlockRepository) : BaseUseCase<Payload, RemoveDocCover.Params>() {
    override suspend fun run(params: Params) = safe {
        repo.removeDocumentCover(ctx = params.ctx)
    }

    class Params(val ctx: Id)
}