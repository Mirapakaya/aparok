package com.aparokio.aparok.domain.block.interactor

import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.base.Either
import com.aparokio.aparok.core_models.Block
import com.aparokio.aparok.core_models.BlockSplitMode
import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_models.ext.content

/**
 * Use-case for splitting the target block into two blocks based on cursor position.
 */
class SplitBlock(
    private val repo: BlockRepository
) : BaseUseCase<Pair<Id, Payload>, SplitBlock.Params>() {

    override suspend fun run(params: Params) = try {

        val range = params.range
        val isToggle = params.block.content<Block.Content.Text>().isToggle()
        val isList = params.block.content<Block.Content.Text>().isList()
        val isOpen = params.isToggled
        val childrenIds = params.block.children
        val length = params.block.content<Block.Content.Text>().text.length

        var style = Block.Content.Text.Style.P
        var mode = BlockSplitMode.BOTTOM

        if (isList || (range.first != length || range.last != length)) {
            style = params.block.content<Block.Content.Text>().style
        }

        if (childrenIds.isNotEmpty()) {
            mode = BlockSplitMode.INNER
        }

        if (isToggle) {
            if (isOpen == true) {
                style = Block.Content.Text.Style.P
                mode = BlockSplitMode.INNER
            } else {
                style = Block.Content.Text.Style.TOGGLE
                mode = BlockSplitMode.BOTTOM
            }
        }

        repo.split(
            command = Command.Split(
                context = params.context,
                target = params.block.id,
                range = params.range,
                style = style,
                mode = mode
            )
        ).let {
            Either.Right(it)
        }
    } catch (t: Throwable) {
        Either.Left(t)
    }

    /**
     * Params for splitting one block into two blocks
     * @property context context id
     * @property block target block, which we need to split
     * @property range target block selection
     * @property isToggled check if toggle block is opened
     */
    data class Params(
        val context: Id,
        val block: Block,
        val range: IntRange,
        val isToggled: Boolean?
    )
}