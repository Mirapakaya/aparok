package com.aparokio.aparok.domain.primitives

import com.aparokio.aparok.core_models.Command.ObjectTypeConflictingFields
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class GetObjectTypeConflictingFields @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<GetObjectTypeConflictingFields.Params, List<Id>>(dispatchers.io) {

    override suspend fun doWork(params: Params): List<Id> {
        val command = ObjectTypeConflictingFields(
            spaceId = params.spaceId,
            objectTypeId = params.objectTypeId
        )
        return repo.objectTypeListConflictingRelations(command = command)
    }

    data class Params(
        val spaceId: String,
        val objectTypeId: String
    )
}