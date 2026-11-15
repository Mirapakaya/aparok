package com.aparokio.aparok.domain.workspace

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

/**
 * Use-case for adding one or more object to the current workspace.
 * Returns of list of ids of object added to workspace.
 */
// TODO rename to "AddObjectListToSpace"
class AddObjectToWorkspace @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
): BaseUseCase<List<Id>, AddObjectToWorkspace.Params>(dispatchers.io) {

    override suspend fun run(params: Params) = safe {
        repo.addObjectListToSpace(
            objects = params.objects,
            space = params.space
        )
    }

    data class Params(
        val objects: List<Id>,
        val space: Id
    )
}