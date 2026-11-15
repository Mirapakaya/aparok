package com.aparokio.aparok.domain.page

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import javax.inject.Inject

/**
 * Use-case for un-doing latest changes in document.
 */
class Undo @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<Undo.Params, Undo.Result>(dispatchers.io) {

    override suspend fun doWork(params: Undo.Params): Undo.Result {
        return repo.undo(
            command = Command.Undo(
                context = params.context
            )
        )
    }

    /**
     * Params for un-doing latest changes in document.
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