package com.aparokio.aparok.domain.block.interactor

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.base.Either
import com.aparokio.aparok.domain.block.repo.BlockRepository

/**
 * Use-case for unlinking blocks from its context.
 * Unlinking is a replacement for delete operations.
 */
open class UnlinkBlocks(private val repo: BlockRepository) :
    BaseUseCase<Payload, UnlinkBlocks.Params>() {

    override suspend fun run(params: Params) = try {
        repo.unlink(
            command = Command.Unlink(
                context = params.context,
                targets = params.targets
            )
        ).let {
            Either.Right(it)
        }
    } catch (t: Throwable) {
        Either.Left(t)
    }

    /**
     * Params for unlinking a set of blocks from its context (i.e. page)
     * @property context context id
     * @property targets ids of the blocks, which we need to unlink from the [context]
     */
    data class Params(
        val context: Id,
        val targets: List<Id>
    )
}