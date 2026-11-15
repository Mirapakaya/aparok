package com.aparokio.aparok.domain.primitives

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.primitives.SetObjectTypeHeaderRecommendedFields.Params
import javax.inject.Inject

class SetObjectTypeHeaderRecommendedFields @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<Params, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Params) {
        val command = Command.ObjectTypeSetRecommendedHeaderFields(
            objectTypeId = params.objectTypeId,
            fields = params.fields
        )
        return repo.objectTypeSetRecommendedHeaderFields(command = command)
    }

    data class Params(
        val objectTypeId: String,
        val fields: List<Id>
    )
}