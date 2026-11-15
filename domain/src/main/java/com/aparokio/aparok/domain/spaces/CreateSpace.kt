package com.aparokio.aparok.domain.spaces

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.SpaceCreationUseCase
import com.aparokio.aparok.core_models.Struct
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class CreateSpace @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<CreateSpace.Params, Command.CreateSpace.Result>(dispatchers.io) {

    override suspend fun doWork(params: Params) = repo.createWorkspace(
        command = Command.CreateSpace(
            details = params.details,
            useCase = params.useCase
        )
    )

    data class Params(
        val details: Struct,
        val useCase: SpaceCreationUseCase = SpaceCreationUseCase.GET_STARTED_MOBILE
    )
}