package com.aparokio.aparok.domain.icon

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.domain.block.repo.BlockRepository

class SetTextBlockEmoji(
    private val repo: BlockRepository
) : SetEmojiIcon<TextBlockTarget>() {

    override suspend fun run(
        params: Params<TextBlockTarget>
    ) = safe {
        repo.setTextIcon(
            command = Command.SetTextIcon(
                context = params.target.context,
                blockId = params.target.blockId,
                icon = Command.SetTextIcon.Icon.Emoji(params.emoji)
            )
        )
    }
}