package com.aparokio.aparok.domain.workspace

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import kotlinx.coroutines.Dispatchers

class RemoveObjectsFromWorkspace(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<RemoveObjectsFromWorkspace.Params, List<Id>>(dispatchers.io) {

    /**
     * Removes objects from workspace by ids
     * @param params - objects to remove by ids
     * @return list of removed object ids
     */
    override suspend fun doWork(params: Params): List<Id> =
        repo.removeObjectFromWorkspace(objects = params.objects)

    data class Params(val objects: List<Id>)
}