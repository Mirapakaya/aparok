package com.aparokio.aparok.domain.templates

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository

class ApplyTemplate(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers,
) : ResultInteractor<ApplyTemplate.Params, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Params) {
        val command = Command.ApplyTemplate(
            objectId = params.ctx,
            template = params.template
        )
        return repo.applyTemplate(command)
    }

    /**
     * @param ctx id of the object to apply template to
     * @param template id of the template to apply, could be null, in that case the blank template will be applied
     */
    data class Params(
        val ctx: Id,
        val template: Id?
    )
}