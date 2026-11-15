package com.aparokio.aparok.domain.table

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.base.Either
import com.aparokio.aparok.domain.block.repo.BlockRepository

class DeleteTableRow(
    private val repo: BlockRepository
) : BaseUseCase<Payload, DeleteTableRow.Params>() {

    override suspend fun run(params: Params): Either<Throwable, Payload> = safe {
        repo.deleteTableRow(
            ctx = params.ctx,
            targetId = params.target
        )
    }

    /**
     * @param ctx - id of the context object
     * @param target - id of the row to delete
     */
    data class Params(
        val ctx: Id,
        val target: Id
    )
}