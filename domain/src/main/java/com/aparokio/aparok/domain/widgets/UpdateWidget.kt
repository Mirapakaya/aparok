package com.aparokio.aparok.domain.widgets

import com.aparokio.aparok.core_models.Block
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.base.ResultatInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

/**
 * Use-case for updating widget block contained in widgets.
 */
class UpdateWidget @Inject constructor(
    private val repo: BlockRepository
) : ResultatInteractor<UpdateWidget.Params, Payload>() {

    override suspend fun execute(params: Params): Payload = repo.updateWidget(
        ctx = params.ctx,
        source = params.source,
        type = params.type,
        widget = params.widget
    )

    /**
     * [ctx] context of widgets — id of object containing widgets
     * [widget] widget block to update
     * [source] source for the given widget block
     * [type] layout type to update
     */
    data class Params(
        val ctx: Id,
        val widget: Id,
        val source: Id,
        val type: Block.Content.Widget.Layout
    )
}