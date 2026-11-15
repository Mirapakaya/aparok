package com.aparokio.aparok.domain.block.interactor

import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.core_models.Block
import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload

class UpdateFields(private val repo: BlockRepository) :
    BaseUseCase<Payload, UpdateFields.Params>() {

    override suspend fun run(params: Params) = safe {
        repo.setFields(
            command = Command.SetFields(
                context = params.context,
                fields = params.fields
            )
        )
    }

    data class Params(
        val context: Id,
        val fields: List<Pair<Id, Block.Fields>>
    )
}