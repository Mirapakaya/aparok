package com.aparokio.aparok.domain.templates

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class CreateTemplateFromObject @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<CreateTemplateFromObject.Params, Id>(
    dispatchers.io
) {

    override suspend fun doWork(params: Params): Id {
        return repo.createTemplateFromObject(params.obj)
    }

    data class Params(val obj: Id)
}