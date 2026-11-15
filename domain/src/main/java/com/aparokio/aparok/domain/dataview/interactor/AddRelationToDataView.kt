package com.aparokio.aparok.domain.dataview.interactor

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Key
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.block.repo.BlockRepository

/**
 * Use-case for adding an existing relation to a data view.
 */
class AddRelationToDataView(
    private val repo: BlockRepository
) : BaseUseCase<Payload, AddRelationToDataView.Params>() {

    override suspend fun run(params: Params) = safe {
        repo.addRelationToDataView(
            ctx = params.ctx,
            dv = params.dv,
            relation = params.relation
        )
    }

    /**
     * @property [ctx] operation context
     * @property [dv] data view's block id
     * @property [relation] id or key of the existing relation
     */
    class Params(
        val ctx: Id,
        val dv: Id,
        val relation: Key
    )
}