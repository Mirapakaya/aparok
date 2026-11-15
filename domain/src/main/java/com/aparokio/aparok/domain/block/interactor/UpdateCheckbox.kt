package com.aparokio.aparok.domain.block.interactor

import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.core_models.Payload

/**
 * Use-case for updating checkbox state.
 */
open class UpdateCheckbox(
    private val repo: BlockRepository
) : BaseUseCase<Payload, UpdateCheckbox.Params>() {

    override suspend fun run(params: Params) = safe {
        repo.updateCheckbox(
            command = Command.UpdateCheckbox(
                context = params.context,
                target = params.target,
                isChecked = params.isChecked
            )
        )
    }

    /**
     * @property context context id
     * @property target checkbox block id
     * @property isChecked new checked/unchecked state for this target
     */
    data class Params(
        val context: String,
        val target: String,
        val isChecked: Boolean
    )
}