package com.aparokio.aparok.domain.block.interactor

import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.base.Either
import com.aparokio.aparok.core_models.Block
import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload

open class UpdateAlignment(
    private val repo: BlockRepository
) : BaseUseCase<Payload, UpdateAlignment.Params>() {

    override suspend fun run(params: Params) = try {
        repo.updateAlignment(
            command = Command.UpdateAlignment(
                context = params.context,
                targets = params.targets,
                alignment = params.alignment
            )
        ).let {
            Either.Right(it)
        }
    } catch (e: Exception) {
        Either.Left(e)
    }

    /**
     * Params for updating alignment of the whole block.
     * @property context context id
     * @property targets id of the target block, whose alignment we need to update.
     * @property alignment new alignment
     */
    data class Params(
        val context: Id,
        val targets: List<Id>,
        val alignment: Block.Align
    )
}