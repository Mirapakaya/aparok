package com.aparokio.aparok.domain.block.interactor

import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.base.Either
import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload

/**
 * Use-case for updating the whole block's text color.
 */
open class UpdateBackgroundColor(
    private val repo: BlockRepository
) : BaseUseCase<Payload, UpdateBackgroundColor.Params>() {

    override suspend fun run(params: Params) = try {
        repo.updateBackgroundColor(
            command = Command.UpdateBackgroundColor(
                context = params.context,
                targets = params.targets,
                color = params.color
            )
        ).let {
            Either.Right(it)
        }
    } catch (t: Throwable) {
        Either.Left(t)
    }

    /**
     * Params for updating background color for the whole block.
     * @property context context id
     * @property targets id of the target block, whose background color we need to update.
     * @property color new color (hex)
     */
    data class Params(
        val context: Id,
        val targets: List<Id>,
        val color: String
    )
}