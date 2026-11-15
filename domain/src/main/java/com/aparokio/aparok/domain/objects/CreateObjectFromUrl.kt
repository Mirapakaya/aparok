package com.aparokio.aparok.domain.objects

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.ObjectWrapper
import com.aparokio.aparok.core_models.Url
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class CreateObjectFromUrl @Inject constructor(
    private val repository: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<CreateObjectFromUrl.Params, ObjectWrapper.Basic>(dispatchers.io) {

    override suspend fun doWork(params: Params): ObjectWrapper.Basic {
        return repository.createObjectFromUrl(
            space = params.space,
            url = params.url,
            createdInContext = params.createdInContext
        )
    }

    data class Params(
        val space: SpaceId,
        val url: Url,
        val createdInContext: Id? = null
    )
}