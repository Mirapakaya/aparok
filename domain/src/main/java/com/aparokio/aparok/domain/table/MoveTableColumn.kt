package com.aparokio.aparok.domain.table

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_models.Position
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.base.Either
import com.aparokio.aparok.domain.block.repo.BlockRepository

class MoveTableColumn(
    private val repo: BlockRepository
) : BaseUseCase<Payload, MoveTableColumn.Params>() {

    override suspend fun run(params: Params): Either<Throwable, Payload> = safe {
        repo.moveTableColumn(
            ctx = params.ctx,
            target = params.column,
            dropTarget = params.targetDrop,
            position = params.position
        )
    }

    /**
     * Params for moving a column
     * @param ctx - id of the context object
     * @param column - id of the column which we want to move
     * @param targetDrop - id of the column in relation to which the move is positioned
     * @param position - position of the moved [column], relative to [targetDrop]
     */
    data class Params(
        val ctx: Id,
        val column: Id,
        val targetDrop: Id,
        val position: Position
    )
}