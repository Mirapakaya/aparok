package com.aparokio.aparok.domain.objects

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Struct
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.objects.GetDateObjectByTimestamp.Params
import javax.inject.Inject

class GetDateObjectByTimestamp @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<Params, Struct?>(dispatchers.io) {

    override suspend fun doWork(params: Params): Struct? {
        val command = Command.ObjectDateByTimestamp(
            space = params.space,
            timeInSeconds = params.timestampInSeconds
        )
        return repo.objectDateByTimestamp(command)
    }

    data class Params(val space: SpaceId, val timestampInSeconds: Long)
}