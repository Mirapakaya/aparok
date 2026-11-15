package com.aparokio.aparok.domain.debugging

import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.base.Either
import com.aparokio.aparok.domain.block.repo.BlockRepository

class DebugLocalStore(private val repo: BlockRepository) : BaseUseCase<String, DebugLocalStore.Params>() {

    override suspend fun run(params: Params): Either<Throwable, String> = safe {
        repo.debugLocalStore(path = params.path)
    }

    class Params(val path: String)
}