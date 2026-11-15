package com.aparokio.aparok.domain.table

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.base.Either
import com.aparokio.aparok.domain.block.repo.BlockRepository

class FillTableColumn(
    private val repo: BlockRepository
) : BaseUseCase<Payload, FillTableColumn.Params>() {

    override suspend fun run(params: Params): Either<Throwable, Payload> = safe {
        repo.fillTableColumn(
            ctx = params.ctx,
            blockIds = params.targetIds
        )
    }

    /**
     * @property [targetIds] the list of columns that need to be filled in
     */
    data class Params(
        val ctx: Id,
        val targetIds: List<Id>
    )
}