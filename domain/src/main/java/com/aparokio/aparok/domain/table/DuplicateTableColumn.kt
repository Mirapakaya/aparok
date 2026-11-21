package com.aparokio.aparok.domain.table

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_models.Position
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.base.Either
import com.aparokio.aparok.domain.block.repo.BlockRepository

class DuplicateTableColumn(
    private val repo: BlockRepository
) : BaseUseCase<Payload, DuplicateTableColumn.Params>() {

    override suspend fun run(params: Params): Either<Throwable, Payload> = safe {
        repo.duplicateTableColumn(
            ctx = params.ctx,
            targetId = params.targetDrop,
            blockId = params.column,
            position = params.position
        )
    }

    /**
     * @param ctx - id of the context object
     * @param column - column to duplicate
     * @param targetDrop - id of the column in relation to which the duplicate is positioned
     * @param position - position of the new column, relative to [targetDrop]
     */
    data class Params(
        val ctx: Id,
        val column: Id,
        val targetDrop: Id,
        val position: Position
    )
}