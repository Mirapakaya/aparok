package com.aparokio.aparok.domain.block

import com.aparokio.aparok.core_models.Block
import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.core_models.Payload

/**
 * Use-case for updating a divider style
 */
open class UpdateDivider(
    private val repo: BlockRepository
) : BaseUseCase<Payload, UpdateDivider.Params>() {

    override suspend fun run(params: Params) = safe {
        repo.updateDivider(
            command = Command.UpdateDivider(
                style = params.style,
                context = params.context,
                targets = params.targets
            )
        )
    }

    /**
     * @property context context id
     * @property targets id of the target blocks, whose style we need to update.
     * @property style new style for the target blocks.
     */
    data class Params(
        val context: Id,
        val targets: List<Id>,
        val style: Block.Content.Divider.Style
    )
}