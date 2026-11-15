package com.aparokio.aparok.domain.cover

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.block.repo.BlockRepository

class SetDocCoverColor(private val repo: BlockRepository) : BaseUseCase<Payload, SetDocCoverColor.Params>() {

    override suspend fun run(params: Params) = safe {
        repo.setDocumentCoverColor(
            ctx = params.ctx,
            color = params.color
        )
    }

    class Params(
        val ctx: Id,
        val color: String
    )
}