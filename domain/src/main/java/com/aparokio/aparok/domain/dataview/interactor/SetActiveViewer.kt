package com.aparokio.aparok.domain.dataview.interactor

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository

class SetActiveViewer(private val repo: BlockRepository, dispatchers: AppCoroutineDispatchers) :
    ResultInteractor<SetActiveViewer.Params, Payload>(dispatchers.io) {

    override suspend fun doWork(params: Params): Payload {
        val command = Command.DataViewSetActiveView(
            ctx = params.ctx,
            viewerId = params.viewer,
            dataViewId = params.dv
        )
        return repo.dataViewSetActiveView(command)
    }

    data class Params(
        val ctx: Id,
        val viewer: Id,
        val dv: Id
    )
}