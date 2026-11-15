package com.aparokio.aparok.domain.block.interactor

import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.base.Either
import com.aparokio.aparok.core_models.Block
import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.core_models.Id

open class UpdateText(
    private val repo: BlockRepository
) : BaseUseCase<Unit, UpdateText.Params>() {

    override suspend fun run(params: Params) = try {
        repo.updateText(
            command = Command.UpdateText(
                contextId = params.context,
                blockId = params.target,
                text = params.text,
                marks = params.marks
            )
        ).let {
            Either.Right(it)
        }
    } catch (t: Throwable) {
        Either.Left(t)
    }

    data class Params(
        val context: Id,
        val target: Id,
        val text: String,
        val marks: List<Block.Content.Text.Mark>
    )
}