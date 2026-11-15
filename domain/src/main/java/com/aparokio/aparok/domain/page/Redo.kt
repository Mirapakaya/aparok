package com.aparokio.aparok.domain.page

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

/**
 * Use-case for re-doing latest changes in document.
 */
class Redo @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<Redo.Params, Redo.Result>(dispatchers.io) {

    override suspend fun doWork(params: Params): Result {
        return repo.redo(
            command = Command.Redo(
                context = params.context
            )
        )
    }

    /**
     * Params for redoing latest changes in document.
     * @property context id of the context
     */
    data class Params(
        val context: Id
    )

    sealed class Result {
        data class Success(val payload: Payload) : Result()
        object Exhausted : Result()
    }
}