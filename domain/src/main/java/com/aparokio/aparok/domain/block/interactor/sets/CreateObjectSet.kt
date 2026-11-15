package com.aparokio.aparok.domain.block.interactor.sets

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_models.Struct
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.block.repo.BlockRepository

class CreateObjectSet(
    private val repo: BlockRepository
) : BaseUseCase<CreateObjectSet.Response, CreateObjectSet.Params>() {

    override suspend fun run(params: Params) = safe {
        repo.createSet(
            space = params.space,
            objectType = params.type,
            details = params.details
        )
    }

    data class Params(
        val space: Id,
        val type: Id? = null,
        val details: Struct? = null
    )

    /**
     * @property [target] id of the new set
     */
    data class Response(
        val target: Id,
        val payload: Payload,
        val details: Struct
    )
}