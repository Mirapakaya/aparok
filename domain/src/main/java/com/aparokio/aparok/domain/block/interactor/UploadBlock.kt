package com.aparokio.aparok.domain.block.interactor

import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.base.Either
import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.core_models.Payload

class UploadBlock(private val repo: BlockRepository) : BaseUseCase<Payload, UploadBlock.Params>() {

    override suspend fun run(params: Params): Either<Throwable, Payload> = safe {
        repo.uploadBlock(
            command = Command.UploadBlock(
                contextId = params.contextId,
                blockId = params.blockId,
                url = params.url,
                filePath = params.filePath
            )
        )
    }

    data class Params(
        val contextId: String,
        val blockId: String,
        val url: String,
        val filePath: String
    )
}