package com.aparokio.aparok.domain.table

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.base.Either
import com.aparokio.aparok.domain.block.repo.BlockRepository

class SetTableRowHeader(
    private val repo: BlockRepository
) : BaseUseCase<Payload, SetTableRowHeader.Params>() {

    override suspend fun run(params: Params): Either<Throwable, Payload> = safe {
        repo.setTableRowHeader(
            ctx = params.ctx,
            targetId = params.row,
            isHeader = params.isHeader
        )
    }

    data class Params(
        val ctx: Id,
        val row: Id,
        val isHeader: Boolean
    )
}