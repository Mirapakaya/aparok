package com.aparokio.aparok.domain.widgets

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_models.Position
import com.aparokio.aparok.core_models.WidgetLayout
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class CreateWidget @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<CreateWidget.Params, Payload>(dispatchers.io) {

    override suspend fun doWork(params: Params): Payload {
        return repo.createWidget(
            ctx = params.ctx,
            source = params.source,
            layout = params.type,
            target = params.target,
            position = params.position
        )
    }

    data class Params(
        val ctx: Id,
        val source: Id,
        val type: WidgetLayout,
        val target: Id? = null,
        val position: Position = Position.NONE
    )
}