package com.aparokio.aparok.domain.`object`

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.block.repo.BlockRepository

// TODO rename to SetObjectDetails
class UpdateDetail(private val repo: BlockRepository): BaseUseCase<Payload, UpdateDetail.Params>() {

    override suspend fun run(params: Params) = safe {
        repo.setObjectDetail(
            ctx = params.target,
            key = params.key,
            value = params.value
        )
    }

    data class Params(
        val target: Id,
        val key: String,
        val value: Any?
    )
}