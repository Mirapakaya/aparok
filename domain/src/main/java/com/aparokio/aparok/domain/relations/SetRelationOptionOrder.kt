package com.aparokio.aparok.domain.relations

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Key
import com.aparokio.aparok.core_models.primitives.RelationKey
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class SetRelationOptionOrder @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<SetRelationOptionOrder.Params, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Params) {
        val command = Command.SetRelationOptionsOrder(
            space = params.spaceId,
            relationKey = params.relationKey,
            orderedIds = params.orderedIds
        )
        repo.setRelationOptionOrder(
            command
        )
    }

    data class Params(
        val spaceId: SpaceId,
        val relationKey: RelationKey,
        val orderedIds: List<Id>
    )
}
