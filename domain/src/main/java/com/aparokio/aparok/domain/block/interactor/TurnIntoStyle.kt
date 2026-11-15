package com.aparokio.aparok.domain.block.interactor

import com.aparokio.aparok.core_models.Block
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.block.repo.BlockRepository

class TurnIntoStyle(
    private val repo: BlockRepository
) : BaseUseCase<Payload, TurnIntoStyle.Params>() {

    override suspend fun run(
        params: Params
    ) = safe {
        repo.turnInto(
            context = params.context,
            targets = params.targets,
            style = params.style
        )
    }

    data class Params(
        val context: Id,
        val targets: List<Id>,
        val style: Block.Content.Text.Style
    )
}