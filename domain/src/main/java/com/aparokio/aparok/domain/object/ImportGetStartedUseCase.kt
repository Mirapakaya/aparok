package com.aparokio.aparok.domain.`object`

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository

class ImportGetStartedUseCase(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
): ResultInteractor<ImportGetStartedUseCase.Params, Command.ImportUseCase.Result>(dispatchers.io) {

    override suspend fun doWork(params: Params): Command.ImportUseCase.Result {
        return repo.importGetStartedUseCase(params.space)
    }

    data class Params(val space: Id)
}