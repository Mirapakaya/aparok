package com.aparokio.aparok.domain.layout

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.ObjectType
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.block.repo.BlockRepository

class SetObjectLayout(private val repo: BlockRepository): BaseUseCase<Payload, SetObjectLayout.Params>() {

    override suspend fun run(params: Params) = safe {
        repo.setObjectLayout(
            ctx = params.ctx,
            layout = params.layout
        )
    }

    data class Params(
        val ctx: Id,
        val layout: ObjectType.Layout
    )
}