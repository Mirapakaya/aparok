package com.aparokio.aparok.domain.dataview.interactor

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository

/**
 * Use-case for ordering views of the data view.
 */
class SetDataViewViewerPosition(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<SetDataViewViewerPosition.Params, Payload>(dispatchers.io) {

    override suspend fun doWork(params: Params): Payload = repo.setDataViewViewerPosition(
        ctx = params.ctx,
        view = params.viewer,
        dv = params.dv,
        pos = params.pos
    )

    /**
     * @property [ctx] set or collection id
     * @property [dv] data view block id
     * @property [viewer] view id, whose position is changed
     * @property [pos] new position of the [viewer]
     */
    data class Params(
        val ctx: Id,
        val dv: Id,
        val viewer: Id,
        val pos: Int
    )
}