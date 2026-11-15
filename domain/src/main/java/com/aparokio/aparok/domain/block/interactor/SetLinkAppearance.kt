package com.aparokio.aparok.domain.block.interactor

import com.aparokio.aparok.core_models.Block
import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.base.Either
import com.aparokio.aparok.domain.block.repo.BlockRepository

class SetLinkAppearance(
    private val repository: BlockRepository,
) : BaseUseCase<Payload, SetLinkAppearance.Params>() {

    data class Params(
        val contextId: String,
        val blockId: String,
        val content: Block.Content.Link
    )

    override suspend fun run(params: Params): Either<Throwable, Payload> = safe {
        repository.setLinkAppearance(
            Command.SetLinkAppearance(
                contextId = params.contextId,
                blockId = params.blockId,
                content = params.content
            )
        )
    }
}