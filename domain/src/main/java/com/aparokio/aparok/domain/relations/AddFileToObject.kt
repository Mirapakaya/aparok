package com.aparokio.aparok.domain.relations

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_models.ext.addIds
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.base.Either
import com.aparokio.aparok.domain.block.repo.BlockRepository

class AddFileToObject(
    private val repo: BlockRepository
) : BaseUseCase<Payload, AddFileToObject.Params>() {

    override suspend fun run(params: Params): Either<Throwable, Payload> = safe {
        val file = repo.uploadFile(
            command = Command.UploadFile(
                path = params.path,
                type = null,
                space = params.space,
                createdInContext = params.ctx,
                createdInContextRef = params.relation
            )
        )
        val obj = params.obj
        val remaining = obj[params.relation].addIds(listOf(file.id))
        repo.setObjectDetail(
            ctx = params.ctx,
            key = params.relation,
            value = remaining
        )
    }

    class Params(
        val ctx: Id,
        val relation: Id,
        val obj: Map<String, Any?>,
        val path: String,
        val space: SpaceId
    )
}