package com.aparokio.aparok.domain.relations

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.RelationListWithValueItem
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.relations.GetObjectRelationListById.Params
import javax.inject.Inject

class GetObjectRelationListById @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<Params, List<RelationListWithValueItem>>(dispatchers.io) {

    override suspend fun doWork(params: Params): List<RelationListWithValueItem> {
        val command = Command.RelationListWithValue(
            space = params.space,
            value = params.value
        )
        return repo.objectRelationListWithValue(command)
    }

    data class Params(val space: SpaceId, val value: Id)
}