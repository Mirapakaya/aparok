package com.aparokio.aparok.domain.clipboard

import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.core_models.Block
import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.core_models.Id

class Copy(
    private val repo: BlockRepository,
    private val clipboard: Clipboard
) : BaseUseCase<Unit, Copy.Params>() {

    override suspend fun run(params: Params) = safe {
        val result = repo.copy(
            command = Command.Copy(
                context = params.context,
                range = params.range,
                blocks = params.blocks
            )
        )
        clipboard.put(
            text = result.text,
            html = result.html,
            blocks = result.blocks
        )
    }

    /**
     * Params for clipboard paste operation.
     * @param context id of the context
     * @param range selected text range
     * @param blocks associated blocks
     */
    data class Params(
        val context: Id,
        val range: IntRange?,
        val blocks: List<Block>
    )
}