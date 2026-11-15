package com.aparokio.aparok.domain.icon

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.domain.block.repo.BlockRepository

/**
 * Use-case for setting emoji icon.
 */
class SetDocumentEmojiIcon(
    private val repo: BlockRepository
) : SetEmojiIcon<Id>() {

    override suspend fun run(params: SetEmojiIcon.Params<Id>) = safe {
        repo.setDocumentEmojiIcon(
            command = Command.SetDocumentEmojiIcon(
                context = params.target,
                emoji = params.emoji
            )
        )
    }
}