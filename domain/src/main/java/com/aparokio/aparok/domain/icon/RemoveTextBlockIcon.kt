package com.aparokio.aparok.domain.icon

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.domain.block.repo.BlockRepository

/**
 * Remove text block icon i.e. callout block
 */
class RemoveTextBlockIcon(
    private val repo: BlockRepository
) : RemoveIcon<TextBlockTarget>() {

    override suspend fun run(params: TextBlockTarget) = safe {
        repo.setTextIcon(
            Command.SetTextIcon(
                context = params.context,
                blockId = params.blockId,
                icon = Command.SetTextIcon.Icon.None
            )
        )
    }
}