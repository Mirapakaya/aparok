package com.aparokio.aparok.domain.relations

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.base.Either
import com.aparokio.aparok.domain.block.repo.BlockRepository

class SetRelationKey(
    private val repo: BlockRepository
) : BaseUseCase<Payload, SetRelationKey.Params>() {

    override suspend fun run(params: Params): Either<Throwable, Payload> = safe {
        repo.setRelationKey(
            Command.SetRelationKey(
                contextId = params.contextId,
                blockId = params.blockId,
                key = params.key
            )
        )
    }

    class Params(
        val contextId: Id,
        val blockId: Id,
        val key: Id
    )
}