package com.aparokio.aparok.domain.block.interactor

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.base.Either
import com.aparokio.aparok.domain.block.repo.BlockRepository

class ClearBlockContent(
    private val repository: BlockRepository,
) : BaseUseCase<Payload, ClearBlockContent.Params>() {

    data class Params(
        val ctx: Id,
        val blockIds: List<Id>
    )

    override suspend fun run(params: Params): Either<Throwable, Payload> = safe {
        repository.clearBlockContent(
            ctx = params.ctx,
            blockIds = params.blockIds
        )
    }
}