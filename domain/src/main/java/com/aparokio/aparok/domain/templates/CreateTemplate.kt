package com.aparokio.aparok.domain.templates

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.CreateObjectResult
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.ObjectTypeUniqueKeys
import com.aparokio.aparok.core_models.Relations
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.core_models.primitives.TypeKey
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class CreateTemplate @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<CreateTemplate.Params, CreateObjectResult>(dispatchers.io) {

    override suspend fun doWork(params: Params): CreateObjectResult {
        val command = Command.CreateObject(
            template = null,
            prefilled = mapOf(Relations.TARGET_OBJECT_TYPE to params.targetObjectTypeId),
            internalFlags = emptyList(),
            space = params.spaceId,
            typeKey = TypeKey(ObjectTypeUniqueKeys.TEMPLATE)
        )
        val result = repo.createObject(command)
        return result
    }

    data class Params(
        val targetObjectTypeId: Id,
        val spaceId: SpaceId
    )
}