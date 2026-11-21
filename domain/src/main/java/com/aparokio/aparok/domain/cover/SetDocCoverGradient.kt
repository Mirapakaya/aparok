package com.aparokio.aparok.domain.cover

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.block.repo.BlockRepository

class SetDocCoverGradient(private val repo: BlockRepository) : BaseUseCase<Payload, SetDocCoverGradient.Params>() {

    override suspend fun run(params: Params) = safe {
        repo.setDocumentCoverGradient(
            ctx = params.ctx,
            gradient = params.gradient
        )
    }

    class Params(
        val ctx: Id,
        val gradient: String
    )
}