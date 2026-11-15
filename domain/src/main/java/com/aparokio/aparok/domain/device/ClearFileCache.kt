package com.aparokio.aparok.domain.device

import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.base.Interactor
import com.aparokio.aparok.domain.block.repo.BlockRepository

class ClearFileCache(
    private val repo: BlockRepository
): Interactor<BaseUseCase.None>() {
    override suspend fun run(params: BaseUseCase.None) { repo.clearFileCache() }
}